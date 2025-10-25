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

    public void batalhar(Inimigo inimigo) {
        System.out.println("\n⚔️ Início da batalha entre " + nome + " e " + inimigo.nome + "!\n");

        while (this.estaVivo() && inimigo.estaVivo()) {
            // turno do jogador
            this.atacar(inimigo);
            if (!inimigo.estaVivo()) break;

            // turno do inimigo
            inimigo.atacar(this);
        }

        if (this.estaVivo()) {
            System.out.println("\n🏆 " + nome + " venceu a batalha!");
        } else {
            System.out.println("\n💀 " + nome + " foi derrotado por " + inimigo.nome + "...");
        }
    }

    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida + ", Atk=" + ataque + ", Def=" + defesa + ", Nível=" + nivel + "]";
    }
}
