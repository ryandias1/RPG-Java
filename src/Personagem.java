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

    ///  Verifica se está vivo
    public boolean estaVivo() {
        return pontosVida > 0;
    }

    ///  Recebe dano
    public void receberDano(short dano) {
        pontosVida -= dano; // Diminui a vida

        // Garante que a vida não fique negativo, ela zera em vez disso
        if (pontosVida < 0) {
            pontosVida = 0;
        }
    }

    /// Atacar (Personagem -> Inimigo)
    public void atacar(Personagem alvo) {

        // Objeto Random que gera números aleatórios
        Random random = new Random();
        short rolagem = (short) (random.nextInt(6) + 1); // Dado de 6 lados
        short poderTotal = (short) (ataque + rolagem); // Soma o poder do personagem com o valor da rolagem

        System.out.println(nome + " ataca " + alvo.nome + " (rolagem: " + rolagem + ", poder total: " + poderTotal + ")");

        // Se o poder do personagem for maior que a defesa do inimigo
        if (poderTotal > alvo.defesa) {
            short dano = (short) (poderTotal - alvo.defesa); // Calcula o dano causado
            alvo.receberDano(dano); // Reduz o HP do inimigo

            System.out.println("O ataque acerta em cheio! " + alvo.nome + " perde " + dano + " de HP.");
            System.out.println("Vida restante de " + alvo.nome + ": " + alvo.pontosVida);
        } else {
            System.out.println(" " + alvo.nome + " defendeu o ataque!");
        }
    }

    ///  Batalhar
    public boolean batalhar(Inimigo inimigo, BufferedReader br, String local) throws IOException {
        System.out.println("\nInício da batalha entre " + nome + " e " + inimigo.nome + "!");
        System.out.println("Local: " + local + "\n");

        Random random = new Random();

        // Enquanto os dois estiverem vivos
        while (this.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n=== Seu turno ===");
            System.out.println("1 - Atacar");
            System.out.println("2 - Tentar fugir");
            System.out.print("Escolha uma ação: ");

            int escolha = Integer.parseInt(br.readLine());

            // ATACAR
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

                // Depois de decidir sobre o item, realiza o ataque
                this.atacar(inimigo);
            }

            // FUGIR
            else if (escolha == 2) {
                Jogo.fugir(this, br, local);

                if (this.estaVivo()) {
                    System.out.println("\nVocê escapou da batalha e deixa o inimigo para trás...");
                    return false;
                } else {
                    return false;
                }
            }

            // OPÇÃO INVÁLIDA
            else {
                System.out.println("Opção inválida! Escolha novamente.");
                continue;
            }

            // TURNO DO INIMIGO
            if (inimigo.estaVivo()) {
                System.out.println("\n=== Turno do inimigo ===");
                if(inimigo.estaCongelado()){
                    System.out.println(inimigo.nome + " está congelado e não pode agir neste turno!");
                    inimigo.reduzirTurnoCongelado(); // reduz o contador
                }
                else{inimigo.atacar(this);}


                if (!this.estaVivo()) {
                    System.out.println("\nVocê foi derrotado pelo " + inimigo.nome + "...");
                    System.out.println("Fim de jogo!");
                    System.exit(0);
                }
            }

            // Mensagem se ambos ainda estiverem vivos
            if (this.estaVivo() && inimigo.estaVivo()) {
                System.out.println("\nNinguém foi derrotado ainda... a batalha continua!");
            }

        }

        // VITÓRIA
        if (this.estaVivo() && !inimigo.estaVivo()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida + ", Atk=" + ataque + ", Def=" + defesa + ", Nível=" + nivel + "]";
    }


    public void congelar(int turnos) {
        this.turnosCongelado = turnos;
    }

    public boolean estaCongelado() {
        return this.turnosCongelado > 0;
    }

    public void reduzirTurnoCongelado() {
        if (this.turnosCongelado > 0) this.turnosCongelado--;
    }
}
