import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

// Classe Personagem (molde base para criar todos os personagens)
abstract class Personagem {
    protected String nome;
    protected short pontosVida;
    protected short ataque;
    protected short defesa;
    protected short nivel;
    protected Inventario inventario;

    public Personagem(String nome, short pontosVida, short ataque, short defesa, short nivel, Inventario inventario) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = inventario;
    }

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    public void receberDano(short dano) {
        pontosVida -= dano;
        if (pontosVida < 0) pontosVida = 0;
    }

    public void atacar(Personagem alvo) {
        Random random = new Random();
        short rolagem = (short) (random.nextInt(6) + 1); // dado de 6 lados (1 a 6)
        short poderTotal = (short) (ataque + rolagem);

        System.out.println(nome + " ataca " + alvo.nome + " (rolagem: " + rolagem + ", poder total: " + poderTotal + ")");

        if (poderTotal > alvo.defesa) {
            short dano = (short) (poderTotal - alvo.defesa);
            alvo.receberDano(dano);
            System.out.println("🗡️ Dano causado: " + dano + " | HP de " + alvo.nome + ": " + alvo.pontosVida);
        } else {
            System.out.println("🛡️ " + alvo.nome + " defendeu o ataque!");
        }
    }

    public boolean batalhar(Inimigo inimigo, BufferedReader br, String local) throws IOException {
        System.out.println("\n⚔️ Início da batalha entre " + nome + " e " + inimigo.nome + "!");
        System.out.println("Local: " + local + "\n");

        while (this.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n=== Seu turno ===");
            System.out.println("1 - Atacar");
            System.out.println("2 - Tentar fugir");
            System.out.println("3 - Usar item");
            System.out.print("Escolha uma ação: ");

            int escolha = Integer.parseInt(br.readLine());

            if (escolha == 1) {
                this.atacar(inimigo);
            }
            else if (escolha == 2) {
                Jogo.fugir(this, br, local);

                // Se o jogador está vivo após tentar fugir, encerra batalha (fugiu com sucesso)
                if (this.estaVivo()) {
                    System.out.println("\nVocê escapou da batalha e deixa o inimigo para trás...");
                    return false; // fugiu, não venceu
                } else {
                    // morreu tentando fugir
                    return false;
                }
            }
            else if (escolha == 3) {
                Jogo.usarItem(this, br); // todo
            }
            else {
                System.out.println("Opção inválida! Escolha novamente.");
                continue;
            }

            // se o inimigo morreu, sai do loop
            if (!inimigo.estaVivo()) break;

            // turno do inimigo
            System.out.println("\n=== Turno do inimigo ===");
            inimigo.atacar(this);

            if (!this.estaVivo()) {
                System.out.println("\n💀 Você foi derrotado pelo " + inimigo.nome + "...");
                System.out.println("Fim de jogo!");
                System.exit(0);
            }
        }

        // Se o inimigo morreu + vitória
        if (this.estaVivo() && !inimigo.estaVivo()) {
            return true;
        }

        // Caso contrário (fuga ou morte)
        return false;
    }


    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida + ", Atk=" + ataque + ", Def=" + defesa + ", Nível=" + nivel + "]";
    }
}
