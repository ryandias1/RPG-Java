/// Classe Inimigo
public class Inimigo extends Personagem{

    // Construtor da classe Inimigo
    public Inimigo(
            String nome,
            short pontosVida,
            short ataque,
            short defesa,
            short nivel,
            Inventario inventario
    ) {
        // Chama o construtor da classe Personagem
        super(nome, pontosVida, ataque, defesa, nivel, inventario);
    }
}
