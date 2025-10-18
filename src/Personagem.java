abstract class Personagem {
    protected String nome;
    protected short pontosVida;
    protected short ataque;
    protected short defesa;
    protected short nivel;
    //protected Inventario inventario;

    public Personagem(String nome, short pontosVida, short ataque, short defesa, short nivel, short inventario) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        //this.inventario = inventario
    }
}
