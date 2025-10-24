// Classe Mago (herda de Personagem)
public class Mago extends Personagem {

    // Construtor da classe Mago
    public Mago(
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
