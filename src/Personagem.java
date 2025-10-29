import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

/// Classe Personagem (molde base para criar todos os personagens)
abstract class Personagem {
    protected String nome;
    protected short pontosVida;
    protected short ataque;
    protected short defesa;
    protected short nivel;
    protected Inventario inventario;
    protected int turnosCongelado = 0;

    // Construtor da classe Personagem
    public Personagem(String nome, short pontosVida, short ataque, short defesa, short nivel, Inventario inventario) {
        this.nome = nome;
        this.pontosVida = pontosVida;
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

    /// Atacar personagem
    public void atacar(Personagem alvo) {

        // Objeto Random que gera números aleatórios
        Random random = new Random();
        short rolagem = (short) (random.nextInt(6) + 1); // Dado de 6 lados (5 + 1)
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
            System.out.println("\n" + alvo.nome + " bloqueia o ataque de " + nome + "!");
        }
    }

    /// Batalhar contra outro personagem
    public boolean batalhar(Inimigo inimigo, BufferedReader br, String local) throws IOException {

        // Início da batalha
        System.out.println("\nInício da batalha entre " + nome + " e " + inimigo.nome + "!");
        System.out.println("Local: " + local + "\n");

        Random random = new Random();

        // Enquanto os dois estiverem vivos
        while (this.estaVivo() && inimigo.estaVivo()) {

            // Turno do jogador
            System.out.println("\n=== È o seu turno, " + nome + "!");
            System.out.println("1 - Atacar");
            System.out.println("2 - Tentar fugir");
            System.out.print("Escolha uma ação: ");

            int escolha = Integer.parseInt(br.readLine());

            // Atacar inimigo
            if (escolha == 1) {
                System.out.println("\nDeseja usar um item antes de atacar?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                System.out.print("Escolha: ");
                int usarItem = Integer.parseInt(br.readLine());

                // Jogador quer usar item
                if (usarItem == 1) {
                    Jogo.usarItem(this,inimigo, br);
                }

                // Depois que escolher o item, realiza o ataque
                this.atacar(inimigo);
            }

            // Fugir do inimigo
            else if (escolha == 2) {
                Jogo.fugir(this, br, local);

                // Se o jogador continuar vivo
                if (this.estaVivo()) {
                    System.out.println("\nVocê consegue escapar da batalha e deixa " + inimigo.nome + " para trás!");
                    return false;
                } else {
                    return false; // Luta termina
                }
            }

            // Se o jogador digita algo inválido
            else {
                System.out.println("Opção inválida! Escolha novamente.");
                continue;
            }

            // Segundo: turno do inimigo (se continuar vivo após o ataque do jogador)
            if (inimigo.estaVivo()) {
                System.out.println("\nTurno do inimigo " + inimigo.nome + "!");

                // Se o inimigo estiver congelado, ele perde um turno
                if(inimigo.estaCongelado()){
                    System.out.println(inimigo.nome + " está congelado e não pode agir neste turno!");
                    inimigo.reduzirTurnoCongelado(); // Reduz o contador
                }
                else{inimigo.atacar(this);}

                // Se o jogador morrer
                if (!this.estaVivo()) {
                    System.out.println("\nVocê foi derrotado pelo " + inimigo.nome + "...");
                    System.out.println("O Reino de Aurora cai nas sombras...");
                    System.out.println("Fim de jogo!");
                    System.exit(0);
                }
            }

            // Mensagem entre os turnos (se ambos ainda estiverem vivos)
            if (this.estaVivo() && inimigo.estaVivo()) {
                System.out.println("\nNinguém foi derrotado ainda... a batalha continua!");
            }
        }

        // Fim da luta
        if (this.estaVivo() && !inimigo.estaVivo()) {
            return true; // Vitória
        }
        return false; // Morreu ou fugiu
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

    // toString
    @Override
    public String toString() {
        return "\nNome: " + nome +
                "\nHP: " + pontosVida +
                "\nAtaque: " + ataque +
                "\nDefesa: " + defesa +
                "\nNível: " + nivel +
                "\n";
    }

    /*
    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida + ", Atk=" + ataque + ", Def=" + defesa + ", Nível=" + nivel + "]";
    }
    */
}
