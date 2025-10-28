import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

// Classe principal do Jogo
public class Jogo {

    public static void main(String[] args) throws IOException {

        // Cria um objeto chamado br que serve para ler o que o jogador digita no console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Exibe a história introdutória e o objetivo principal do jogo
        System.out.println("🗡Bem-vindo ao Reino de Aurora!");
        System.out.println("Há séculos, o reino viveu em paz... até que o temido dragão Rex despertou.");
        System.out.println("Com seu poder, vilas foram destruídas e criaturas sombrias voltaram a andar pela terra.");
        System.out.println("\nSeu objetivo: descobrir onde o dragão está escondido e derrotá-lo para restaurar a paz em Aurora!");
        System.out.println("Mas cuidado... o caminho até ele é cheio de armadilhas e inimigos poderosos.\n");

        Inventario inventarioInicial = new Inventario(); // Cria um inventário vazio
        Personagem jogador = null; // Cria uma variável jogador, que será definida depois (quando o jogador escolher a classe)
        boolean escolhaClasse = true;

        // Escolha do Pesonagem
        while (escolhaClasse == true) {

            // Personagens e suas características
            System.out.println("\nEscolha seu personagem");
            System.out.println("1 - Guerreiro");
            System.out.println("Alta vida (60 HP), boa defesa (10), ataque equilibrado (12).");
            System.out.println("Ideal para combates longos e resistência física.\n");

            System.out.println("2 - Mago");
            System.out.println("Vida baixa (45 HP), defesa fraca (7), ataque alto (15).");
            System.out.println("Confiável em ataques poderosos e magias ofensivas.\n");

            System.out.println("3 - Arqueiro");
            System.out.println("Vida média (50 HP), defesa média (8), ataque ágil (13).");
            System.out.println("Especialista em ataques rápidos e precisão à distância.\n");

            System.out.print("Digite o número do personagem escolhido: ");

            // Lê o número digitado pelo jogador e converte o texto digitado (String) em número inteiro (int)
            int opcao = Integer.parseInt(br.readLine());

            if (opcao == 1) {
                // Cria um objeto do tipo Guerreiro com os seus atributos
                jogador = new Guerreiro("Guerreiro", (short)60, (short)12, (short)10, (short)1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Guerreiro!");
                escolhaClasse = false;
            }
            else if (opcao == 2) {
                // Cria um objeto do tipo Mago com os seus atributos
                jogador = new Mago("Mago", (short)45, (short)15, (short)7, (short)1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Mago!");
                escolhaClasse = false;
            }
            else if (opcao == 3) {
                // Cria um objeto do tipo Arqueiro com os seus atributos
                jogador = new Arqueiro("Arqueiro", (short)50, (short)13, (short)8, (short)1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Arqueiro!");
                escolhaClasse = false;
            }
            else {
                System.out.println("Opção inválida. Tente novamente!");
            }
        }

        // Mostra status iniciais do personagem
        System.out.println("\n=== Status do Personagem ===");
        System.out.println("Classe: " + jogador.getClass().getSimpleName());
        System.out.println("Vida: " + jogador.pontosVida);
        System.out.println("Ataque: " + jogador.ataque);
        System.out.println("Defesa: " + jogador.defesa);
        System.out.println("Nível: " + jogador.nivel);
        System.out.println("============================");

        // Início direto da exploração (sem menu principal)
        System.out.println("\nSua jornada começa agora...");
        explorar(jogador, br);
    }

    /// EXPLORAR UM LUGAR
    public static void explorar(Personagem jogador, BufferedReader br) throws IOException {
        boolean explorando = true;

        while (explorando) {
            System.out.println("\n=== Lugares para explorar ===");
            System.out.println("1 - Floresta Nebulosa");
            System.out.println("2 - Caverna das Sombras");
            System.out.println("3 - Vila Abandonada");
            System.out.println("4 - Montanha Sombria");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha para onde ir: ");

            int opcao = Integer.parseInt(br.readLine());

            // FLORESTA NEBULOSA
            if (opcao == 1) {
                System.out.println("\nVocê entrou na Floresta Nebulosa.");
                System.out.println("O ar é denso e cheio de névoa... algo se move entre as árvores.\n");

                System.out.println("1 - Seguir pela trilha da esquerda");
                System.out.println("2 - Seguir pela trilha central");
                System.out.println("3 - Seguir pela trilha da direita");
                System.out.println("4 - Encerrar jogo");
                System.out.print("Escolha o caminho: ");

                int escolha = Integer.parseInt(br.readLine());

                // TRILHA ESQUERDA
                if (escolha == 1) {
                    System.out.println("\nVocê segue pela trilha da esquerda...");
                    System.out.println("Das sombras surge um zumbi!");
                    Inimigo zumbi = new Inimigo("Zumbi", (short)20, (short)8, (short)3, (short)1, new Inventario());
                    batalhar(jogador, zumbi, br, "Floresta Nebulosa");
                }

                // TRILHA CENTRAL
                else if (escolha == 2) {
                    System.out.println("\nVocê encontra um pequeno altar coberto por musgo e inscrições antigas...");
                    System.out.println("Um estranho brilho verde emana das pedras... parece reagir à sua presença.");

                    System.out.println("Você se aproxima e toca o altar...");
                    System.out.println("De repente, uma luz intensa envolve sua mão e um objeto se materializa diante de você!");

                    // Cria um novo item e adiciona ao inventário do jogador
                    Item amuleto = new Item("Amuleto da Floresta", "Um amuleto antigo que pulsa com energia natural", "+10 HP Máximo", 1);
                    jogador.inventario.adicionarItem(amuleto);

                    System.out.println("Você recebeu o item: " + amuleto.getNome() + "!");
                    System.out.println("O amuleto brilha levemente e você sente suas forças revigorarem...");

                    // Efeito direto: aumenta um pouco a vida máxima (simplesmente curando um pouco)
                    jogador.pontosVida += 10;
                    System.out.println("Sua energia aumenta! HP atual: " + jogador.pontosVida);
                }

                // TRILHA DIREITA
                else if (escolha == 3) {
                    System.out.println("\nVocê tenta sair rapidamente da trilha...");
                    System.out.println("Mas uma fera te avista e ataca!");
                    Inimigo lobo = new Inimigo("Lobo Sombrio", (short)45, (short)10, (short)4, (short)1, new Inventario());
                    batalhar(jogador, lobo, br, "Floresta Nebulosa");
                }

                else if (escolha == 4) {
                    System.out.println("Encerrando o jogo. Até logo, aventureiro!");
                    explorando = false;
                }

                else {
                    System.out.println("Opção inválida.");
                }
            }

            // CAVERNA DAS SOMBRAS
            else if (opcao == 2) {
                System.out.println("\nVocê entrou na Caverna das Sombras.");
                System.out.println("1 - Explorar o interior da caverna");
                System.out.println("2 - Sair da caverna");
                System.out.print("Escolha: ");
                int escolha = Integer.parseInt(br.readLine());

                // Explorar o interior da caverna
                if (escolha == 1) {
                    Inimigo troll = new Inimigo("Troll das Sombras", (short)30, (short)12, (short)6, (short)2, new Inventario());
                    batalhar(jogador, troll, br, "Caverna das Sombras");
                }
                else if (escolha == 2) {
                    System.out.println("Você sai da caverna.");
                }
                else {
                    System.out.println("Opção inválida.");
                }
            }

            // VILA ABANDONADA
            else if (opcao == 3) {
                System.out.println("\nVocê chega à Vila Abandonada...");
                System.out.println("As casas estão em ruínas e há marcas de magia nas paredes...");
                System.out.println("Um frio percorre sua espinha... a energia sombria ainda paira no ar.");
                System.out.println("De repente, uma explosão mágica atinge o chão perto de você!");
                System.out.println("Você é lançado para trás e sente uma forte dor no peito...");

                jogador.pontosVida -= 15; // reduzido o dano também

                if (jogador.pontosVida < 0) {
                    jogador.pontosVida = 0;
                }

                System.out.println("Você perdeu 15 pontos de vida! HP atual: " + jogador.pontosVida);

                // Se o jogador morrer com o golpe
                if (!jogador.estaVivo()) {
                    System.out.println("\nA energia maligna consome suas forças...");
                    System.out.println("Você cai de joelhos e tudo escurece...");
                    System.out.println("Fim de jogo!");
                    System.exit(0);
                }

                System.out.println("Mesmo ferido, você consegue se arrastar até uma parede quebrada e respirar por um instante...");
            }

            // MONTANHA SOMBRIA
            else if (opcao == 4) {
                System.out.println("\nVocê chegou à Montanha Sombria. O dragão Rex te aguarda!");
                Inimigo dragao = new Inimigo("Rex", (short)70, (short)20, (short)10, (short)5, new Inventario());
                batalhar(jogador, dragao, br, "Montanha Sombria");
            }

            // SAIR
            else if (opcao == 5) {
                System.out.println("\nVocê retorna ao menu principal.");
                explorando = false;
            }

            else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // BATALHAR CONTRA UM INIMIGO
    public static boolean batalhar(Personagem jogador, Inimigo inimigo, BufferedReader br, String local) throws IOException {
        System.out.println("\n===BATALHA CONTRA " + inimigo.nome.toUpperCase() + " ===");

        boolean venceu = jogador.batalhar(inimigo, br, local);

        // Verifica se o jogador ficou sem vida após a luta
        if (!jogador.estaVivo()) {
            System.out.println("\nVocê foi derrotado... o Reino de Aurora cai nas sombras.");
            System.out.println("Fim de jogo!");
            System.exit(0);
        }

        if (venceu) {
            System.out.println("\nVocê venceu a batalha contra " + inimigo.nome + "!");
            System.out.println("Você vasculha o corpo do inimigo em busca de algo útil...");


            // Sistema de Drop Aleatório (sistema de sorteio para gerar números ou resultados aleatórios)
            Random random = new Random();
            boolean dropou = random.nextBoolean(); // Rola o dado para ver se encontra algo (50% chance)

            // Se tiver sorte de encontrar algo
            if (dropou) {
                int sorteio = random.nextInt(8) + 1; // Sorteia um número aleatória de 1 a 8
                Item itemDropado; // Cria uma variável do tipo Item que vai guardar o item que o jogador ganhar

                // Menu de possibilidade
                switch (sorteio) {
                    case 1: itemDropado = new Item("Poção de Cura", "Recupera vida", "+20 HP", 1); break;
                    case 2: itemDropado = new Item("Raiz de Mirtilha", "Recupera vida", "+8 HP", 1); break;
                    case 3: itemDropado = new Item("Amuleto Guardião", "Fornece alta proteção", "+20 defesa", 1); break;
                    case 4: itemDropado = new Item("Escudo Velho", "Fornece leve proteção", "+5 defesa", 1); break;
                    case 5: itemDropado = new Item("Orbe do Desespero", "Libera uma onda de energia que causa dano aos inimigos", "+30 ataque", 1); break;
                    case 6: itemDropado = new Item("Poção de Fúria", "Uma poção que aumenta a força", "+12 ataque", 1); break;
                    default: itemDropado = new Item("Item Desconhecido", "Você não sabe o que é isso", "???", 1);
                }

                System.out.println("O inimigo deixou cair: " + itemDropado.getNome() + "!");
                jogador.inventario.adicionarItem(itemDropado);
            } else {
                System.out.println("O inimigo não deixou nenhum item para trás.");
            }

            if (inimigo.nome.contains("Rex")){
                jogador.nivel += 5;
                System.out.println("\nVocê subiu 5 níveis! ⬆️");
                } else if(inimigo.nome.contains("Zumbi")){
                jogador.nivel += 1;
                System.out.println("\nVocê subiu 1 nível! ⬆️");
            } else if(inimigo.nome.contains("Troll das Sombras")){
                jogador.nivel += 2;
                System.out.println("\nVocê subiu 2 níveis! ⬆️");
            } else if(inimigo.nome.contains("Lobo Sombrio")){
                jogador.nivel += 3;
                System.out.println("\nVocê subiu 3 níveis! ⬆️");
            } else{
                jogador.nivel += 1;
                System.out.println("\n⬆Você subiu 1 nível! ⬆️");
            }
        }
        return venceu;
    }

    public static void usarItem(Personagem jogador, BufferedReader br) throws IOException {
        System.out.println("\n=== SEU INVENTÁRIO ===");

        if (jogador.inventario.estaVazio()) {
            System.out.println("Seu inventário está vazio!");
            System.out.println("Você não pode usar nenhum item agora.");
            System.out.println("=======================");
            return;
        }

        jogador.inventario.listarItens();
        System.out.println("=======================");
        System.out.print("Digite o nome do item que deseja usar: ");
        String nomeItem = br.readLine().trim();

        Random random = new Random();
        int dado = random.nextInt(6) + 1;
        boolean sucesso = dado >= 4;

        if (sucesso) {
            System.out.println("\nVocê usa o item " + nomeItem + " com sucesso!");

            if (nomeItem.equalsIgnoreCase("Poção de Cura")) {
                jogador.pontosVida += 20;
                System.out.println("Você recuperou 20 pontos de vida! HP atual: " + jogador.pontosVida);
            }
            else if (nomeItem.equalsIgnoreCase("Elixir de Energia")) {
                jogador.ataque += 5;
                System.out.println("Você sente sua força aumentar temporariamente! (+5 ataque)");
            }
            else {
                System.out.println("O item emite um brilho misterioso, mas nada parece acontecer...");
            }

            jogador.inventario.removerItem(nomeItem, 1);
        }
        else {
            System.out.println("\nVocê tenta usar o item " + nomeItem + ", mas nada acontece...");
            jogador.inventario.removerItem(nomeItem, 1);
        }
    }

    // FUGIR DE UMA BATALHA
    public static void fugir(Personagem jogador, BufferedReader br, String local) throws IOException {
        System.out.println("\nVocê tenta fugir...");

        Random random = new Random();
        int rolagem = random.nextInt(6) + 1;

        if (rolagem % 2 == 0) {
            System.out.println("Você consegue escapar com sucesso!");
            System.out.println("Você respira aliviado e continua sua jornada.");
        } else {
            System.out.println("Você tropeça ao tentar fugir!");
            System.out.println("O inimigo te alcança e acerta um golpe antes de você escapar!");

            short dano = 8; // dano ligeiramente menor para equilibrar
            jogador.receberDano(dano);
            System.out.println("Você perdeu " + dano + " pontos de vida. HP atual: " + jogador.pontosVida);

            if (!jogador.estaVivo()) {
                System.out.println("\nVocê não resistiu ao golpe durante a fuga...");
                System.out.println("Fim de jogo!");
                System.exit(0);
            } else {
                System.out.println("Mesmo ferido, você consegue se afastar do perigo.");
            }
        }

        String esconderijo;

        if (local.contains("Floresta")) {
            esconderijo = "entre as árvores";
        } else if (local.contains("Caverna")) {
            esconderijo = "atrás de uma pedra";
        } else if (local.contains("Vila")) {
            esconderijo = "atrás de uma parede quebrada";
        } else if (local.contains("Montanha")) {
            esconderijo = "em uma fenda na rocha";
        } else {
            esconderijo = "em um canto seguro";
        }

        System.out.println("\nVocê se esconde " + esconderijo + " por um certo tempo e logo retoma a exploração.");
    }
}
