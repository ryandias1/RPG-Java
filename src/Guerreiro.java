// Classe Guerreiro (herda de Personagem)
public class Guerreiro extends Personagem {

    // Construtor da classe Guerreiro
    public Guerreiro(
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
