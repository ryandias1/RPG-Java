// Classe Arqueiro (herda de Personagem)
public class Arqueiro extends Personagem {

    // Construtor da classe Arqueiro
    public Arqueiro(
            String nome,
            short pontosVida,
            short ataque,
            short defesa,
            short nivel,
            Inventario inventario
    ) {
        // Chama o construtor da classe Personagem
        super(nome, pontosVida, ataque, defesa, nivel, inventario);

        // No inventário de todo arqueiro
        Item flechaInicial = new Item("Flecha Envenenada", "Uma flecha mergulhada em toxina de serpente.", "Causa +5 de dano no Inimigo ao qual for acertada.", 1);
        this.inventario.adicionarItem(flechaInicial);
    }
}
