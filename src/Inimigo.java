import java.util.Random;

/// Classe Inimigo
public class Inimigo extends Personagem {

    // Construtor da classe Inimigo
    public Inimigo(
            String nome,
            short pontosVida,
            short ataque,
            short defesa,
            short nivel,
            Inventario inventario
    ) {
        super(nome, pontosVida, ataque, defesa, nivel, inventario);

        Random random = new Random();
        Item itemInimigo;
        int sorteio = random.nextInt(4) + 1; // sorteia entre 1 e 4

        switch (sorteio) {
            case 1:
                itemInimigo = new Item("Espada do Crepúsculo", "Uma lâmina lendária forjada na fronteira entre luz e trevas. Aumenta muito o poder de ataque.", "+30 Ataque", 1);break;

            case 2:
                itemInimigo = new Item("Armadura do Guardião Ancestral", "Uma armadura antiga encantada por espíritos protetores. Sua defesa é quase impenetrável.", "+25 Defesa", 1);break;

            case 3:
                itemInimigo = new Item("Anel da Eternidade", "Um anel mágico que aumenta a vida máxima de quem o usa.", "+100 HP", 1);break;

            case 4:
                itemInimigo = new Item("Orbe das Almas Perdidas", "Um artefato sombrio que canaliza o poder dos inimigos derrotados, amplificando seu ataque e defesa.", "+15 Ataque / +10 Defesa", 1);break;

            default:
                itemInimigo = new Item("Relíquia Desconhecida", "Um artefato estranho de origem misteriosa.", "???", 1);
        }

        // Adiciona o item ao inventário do inimigo
        this.inventario.adicionarItem(itemInimigo);
    }
}

