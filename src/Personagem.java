import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

/// Classe Personagem (molde base para criar todos os personagens)
abstract class Personagem {
    protected String nome;
    protected short pontosVida;
    protected short vidaMax;
    protected short ataque;
    protected short defesa;
    protected short nivel;
    protected Inventario inventario;
    protected int turnosCongelado = 0;

    // Construtor da classe Personagem
    public Personagem(String nome, short pontosVida, short ataque, short defesa, short nivel, Inventario inventario) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.vidaMax = pontosVida; // Define a vida máxima inicial
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = inventario;
    }

    // Verifica se está vivo
    public boolean estaVivo() {
        return pontosVida > 0; // Retorna true
    }

    // Recebe dano
    public void receberDano(short dano) {
        pontosVida -= dano; // Diminui a vida

        // Garante que a vida não fique negativo, ela zera em vez disso
        if (pontosVida < 0) {
            pontosVida = 0;
        }
    }

    // Cura o personagem (sem ultrapassar o máximo)
    public void curar(short quantidade) {
        // Se a quantidade for zero ou negativa, não faz nada.
        if (quantidade <= 0) return;

        // Quanto falta para encher a vida até o máximo
        int falta = vidaMax - pontosVida;

        // Quantidade REAL que poderá ser curada
        int curada = Math.max(0, Math.min(falta, quantidade));

        // Se não há nada para curar, avisa e encerra
        if (curada == 0) {
            System.out.println(nome + " já está com HP cheio. (" + pontosVida + "/" + vidaMax + ")");
            return;
        }

        // Aplica a cura de fato
        pontosVida += curada;
        System.out.println(nome + " recuperou " + curada + " pontos de vida. HP atual: " + pontosVida + "/" + vidaMax);
    }


    // Subir de nível
    public void subirNivel(int quantidade) {
        this.nivel += quantidade;

        short aumentoVidaMax = (short) (5 * quantidade);
        short aumentoAtaque = (short) (2 * quantidade);
        short aumentoDefesa = (short) (1 * quantidade);

        if (this instanceof Guerreiro) {
            aumentoVidaMax += (short) (3 * quantidade);
            aumentoDefesa += (short) (2 * quantidade);
        } else if (this instanceof Mago) {
            aumentoAtaque += (short) (3 * quantidade);
        } else if (this instanceof Arqueiro) {
            aumentoAtaque += (short) (2 * quantidade);
            aumentoDefesa += (short) (1 * quantidade);
        }

        this.vidaMax += aumentoVidaMax;
        this.ataque += aumentoAtaque;
        this.defesa += aumentoDefesa;

        System.out.println("\n=== SUBIU DE NÍVEL! ===");
        System.out.println("Novo nível: " + this.nivel);
        System.out.println("Ganhou +" + aumentoVidaMax + " Vida Máxima, +" + aumentoAtaque + " Ataque, +" + aumentoDefesa + " Defesa!");

        // cura e mostra o resultado final
        curar((short)(aumentoVidaMax / 2));

        System.out.println("HP: " + pontosVida + "/" + vidaMax);
    }



    /// Atacar personagem
    public void atacar(Personagem alvo) {

        // Objeto Random que gera números aleatórios
        Random random = new Random();
        short rolagem = (short) (random.nextInt(6) + 1); // Dado de 6 lados
        short poderTotal = (short) (ataque + rolagem); // Soma o ataque do personagem + rolagem do dado resultando o poder total dele

        System.out.println("\n" + nome + " parte para o ataque contra " + alvo.nome + "!");

        // Mostra a rolagem e o cálculo detalhado
        System.out.println("\n" + nome + " rola o dado e tira: " + rolagem);
        System.out.println("Ataque base de " + nome + ": " + ataque);
        System.out.println("(Rolagem do dado " + rolagem + " + Ataque " + ataque + ") = Poder total de " + nome + ": " + poderTotal);

        // Se o poder do personagem for maior que a defesa do inimigo
        if (poderTotal > alvo.defesa) {
            short dano = (short) (poderTotal - alvo.defesa); // Calcula o dano causado
            alvo.receberDano(dano); // Reduz o HP do inimigo

            System.out.println("O golpe de " + nome + " acerta " + alvo.nome + " e causa " + dano + " de dano!");
            System.out.println("Vida restante de " + alvo.nome + ": " + alvo.pontosVida);
        } else {
            System.out.println("\n" + nome + " ataca, mas " + alvo.nome + " bloqueia o golpe!");
            System.out.println("(Poder total do ataque de " + nome + ": " + poderTotal + " | Defesa de " + alvo.nome + ": " + alvo.defesa + ")");
            System.out.println("O ataque não foi forte o suficiente para causar dano!");
        }
    }

    /// Batalhar contra outro personagem
    public boolean batalhar(Inimigo inimigo, BufferedReader br, String local) throws IOException {

        System.out.println("\n================================================================");
        System.out.println("                 INÍCIO DA BATALHA");
        System.out.println("================================================================");
        System.out.println("Local: " + local);
        System.out.println("Você: " + this.nome + "  VS  Inimigo: " + inimigo.nome);
        System.out.println("================================================================");

        Random random = new Random();
        int turno = 1;

        while (this.estaVivo() && inimigo.estaVivo()) {

            System.out.println("\n----------------------------------------------------------------");
            System.out.println("                       TURNO " + turno);
            System.out.println("----------------------------------------------------------------");

            // TURNO DO JOGADOR
            int escolha;

            while (true) {
                System.out.println("\nÉ o seu turno, " + nome + "!");
                System.out.println("1 - Atacar");
                System.out.println("2 - Tentar fugir");
                System.out.print("Escolha um número: ");
                String entrada = br.readLine().trim();

                if (entrada.equals("1") || entrada.equals("2")) {
                    escolha = Integer.parseInt(entrada);
                    break;
                } else {
                    System.out.println("\nOpção inválida! Digite apenas 1 ou 2.\n");
                }
            }

            if (escolha == 1) {

                int usarItem;

                while (true) {
                    System.out.println("\nDeseja usar um item antes de atacar?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    System.out.print("Escolha um número: ");
                    String entradaItem = br.readLine().trim();

                    if (entradaItem.equals("1") || entradaItem.equals("2")) {
                        usarItem = Integer.parseInt(entradaItem);
                        break;
                    } else {
                        System.out.println("\nOpção inválida! Digite apenas 1 ou 2.\n");
                    }
                }

                if (usarItem == 1) {
                    Jogo.usarItem(this, inimigo, br);

                    // Mostra status completo após usar o item
                    System.out.println("\n=== STATUS APÓS USAR ITEM ===");

                    System.out.println(this.nome + " (JOGADOR)");
                    System.out.println("Vida Atual: " + this.pontosVida);
                    System.out.println("Vida Máxima: " + this.vidaMax);
                    System.out.println("Ataque: " + this.ataque);
                    System.out.println("Defesa: " + this.defesa);
                    System.out.println("Nível: " + this.nivel);


                    System.out.println("\n" + inimigo.nome + " (INIMIGO)");
                    System.out.println("Vida Atual: " + inimigo.pontosVida);
                    System.out.println("Vida Máxima: " + inimigo.vidaMax);
                    System.out.println("Ataque: " + inimigo.ataque);
                    System.out.println("Defesa: " + inimigo.defesa);
                    System.out.println("Nível: " + inimigo.nivel);


                    System.out.println("=======================================");
                }

                this.atacar(inimigo);
            }

            else if (escolha == 2) {
                Jogo.fugir(this, br, local);
                return false; // já imprime tudo dentro de fugir()
            }

            else {
                System.out.println("Opção inválida! Escolha novamente.");
                continue;
            }

            // TURNO DO INIMIGO
            if (inimigo.estaVivo()) {
                System.out.println("\n----------------------------------------------------------------");
                System.out.println("                   TURNO DO INIMIGO: " + inimigo.nome);
                System.out.println("----------------------------------------------------------------");

                if (inimigo.estaCongelado()) {
                    System.out.println(inimigo.nome + " está congelado e não pode agir neste turno!");
                    inimigo.reduzirTurnoCongelado();
                } else {
                    inimigo.atacar(this);
                }

                if (!this.estaVivo()) {
                    System.out.println("\nVocê foi derrotado por " + inimigo.nome + "...");
                    System.out.println("O Reino de Aurora cai nas sombras.");
                    System.out.println("Fim de jogo!");
                    System.exit(0);
                }
            }

            // STATUS APÓS O TURNO
            System.out.println("\n=================== STATUS APÓS O TURNO ===================");
            System.out.println(this.nome + " → HP: " + this.pontosVida + " | Ataque: " + this.ataque + " | Defesa: " + this.defesa);
            System.out.println(inimigo.nome + " → HP: " + inimigo.pontosVida + " | Ataque: " + inimigo.ataque + " | Defesa: " + inimigo.defesa);
            System.out.println("================================================================");

            if (this.estaVivo() && inimigo.estaVivo()) {
                System.out.println("\nA batalha continua...");
            }

            turno++;
        }

        // RESULTADO FINAL
        System.out.println("\n================================================================");
        if (this.estaVivo() && !inimigo.estaVivo()) {
            System.out.println("VITÓRIA! " + this.nome + " derrotou " + inimigo.nome + "!");
            System.out.println("Batalha encerrada em " + (turno - 1) + " turnos.");
            System.out.println("================================================================");
            return true;
        } else {
            System.out.println("DERROTA... " + this.nome + " caiu em combate.");
            System.out.println("================================================================");
            return false;
        }
    }

    // Congela o personagem por uma certa quantidade de turnos (inimigo.congelar(2);)
    public void congelar(int turnos) {
        this.turnosCongelado = turnos;
    }

    // Verifica se o personagem ainda está congelado
    public boolean estaCongelado() {
        return this.turnosCongelado > 0; // True (está congelado)
    }

    // Diminui em 1 o número de turnos que o personagem ainda vai ficar congelado
    public void reduzirTurnoCongelado() {
        // Verifica se ainda tem turnos para o personagem ficar congelado
        if (this.turnosCongelado > 0)
            this.turnosCongelado--;
    }

    // toString do personagem
    @Override
    public String toString() {
        return "\n===== STATUS DO PERSONAGEM =====" +
                "\nNome: " + nome +
                "\nVida Atual: " + pontosVida +
                "\nVida Máxima: " + vidaMax +
                "\nAtaque: " + ataque +
                "\nDefesa: " + defesa +
                "\nNível: " + nivel;
    }

}
