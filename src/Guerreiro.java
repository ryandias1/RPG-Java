/// Classe Guerreiro
public class Guerreiro extends Personagem {

        // Construtor da classe Guerreiro
        public Guerreiro(
                String nome,
                short pontosVida,
                short ataque,
                short defesa,
                short nivel,
                Inventario inventario
        ) {
            // Chama o construtor da classe Personagem
            super(nome, pontosVida, ataque, defesa, nivel, inventario);

            // Adiciona um item ao inventário do Guerreiro
            Item facaInicial = new Item(
                    "Faca Inicial",
                    "Uma lâmina simples, mas confiável.",
                    "+5 Ataque",
                    1
            );
            this.inventario.adicionarItem(facaInicial);
        }
    }
