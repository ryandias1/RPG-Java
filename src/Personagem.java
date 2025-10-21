// Classe Personagem (molde base para criar todos os personagens)
abstract class Personagem {

    // Atributos que todos os personagens tem
    protected String nome;
    protected short pontosVida;
    protected short ataque;
    protected short defesa;
    protected short nivel;
    protected Inventario inventario;

    // Construtor da Classe Personagem
    public Personagem(String nome, short pontosVida, short ataque, short defesa, short nivel, Inventario inventario) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = inventario;
    }
}
