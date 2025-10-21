// Classe Arqueiro (herda de Personagem)
public class Arqueiro extends Personagem {

    // Construtor da classe Arqueiro
    public Arqueiro(
            String nome,
            short pontosVida,
            short ataque,
            short defesa,
            short nivel,
            short inventario
    ) {
        // Chama o construtor da classe Personagem
        super(nome, pontosVida, ataque, defesa, nivel, inventario);
    }
}
