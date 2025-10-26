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
            System.out.println("Alta vida (120 HP), boa defesa (20), ataque equilibrado (18).");
            System.out.println("Ideal para combates longos e resistência física.\n");

            System.out.println("2 - Mago");
            System.out.println("Vida baixa (80 HP), defesa fraca (10), ataque alto (25).");
            System.out.println("Confiável em ataques poderosos e magias ofensivas.\n");

            System.out.println("3 - Arqueiro");
            System.out.println("Vida média (90 HP), defesa média (12), ataque ágil (20).");
            System.out.println("Especialista em ataques rápidos e precisão à distância.\n");

            System.out.print("Digite o número do personagem escolhido: ");

            // Lê o número digitado pelo jogador e converte o texto digitado (String) em número inteiro (int)
            int opcao = Integer.parseInt(br.readLine());

            if (opcao == 1) {
                // Cria um objeto do tipo Guerreiro com os seus atributos
                jogador = new Guerreiro("Guerreiro", (short)120, (short)18, (short)20, (short)1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Guerreiro!");
                escolhaClasse = false;
            }
            else if (opcao == 2) {
                // Cria um objeto do tipo Mago com os seus atributos
                jogador = new Mago("Mago", (short)80, (short)25, (short)10, (short)1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Mago!");
                escolhaClasse = false;
            }
            else if (opcao == 3) {
                // Cria um objeto do tipo Arqueiro com os seus atributos
                jogador = new Arqueiro("Arqueiro", (short)90, (short)20, (short)12, (short)1, inventarioInicial);
                System.out.println("\nVocê escolheu ser um Arqueiro!");
                escolhaClasse = false;
            }
            else {
                System.out.println("Opção inválida. Tente novamente!");
            }
        }

        // Mostra status iniciais do personagem
        System.out.println("\n=== Status do Personagem ===");
        System.out.println("Classe: " + jogador.getClass().getSimpleName()); // TODO
        System.out.println("Vida: " + jogador.pontosVida);
        System.out.println("Ataque: " + jogador.ataque);
        System.out.println("Defesa: " + jogador.defesa);
        System.out.println("Nível: " + jogador.nivel);
        System.out.println("============================");

        // Início direto da exploração (sem menu principal)
        System.out.println("\nSua jornada começa agora...");
        explorar(jogador, br);
    }

    // Método esplorar
    public static void explorar(Personagem jogador, BufferedReader br) throws IOException {
        boolean explorando = true;

        while (explorando == true) {
            System.out.println("\n=== Lugares para explorar ===");
            System.out.println("1 - Floresta Nebulosa");
            System.out.println("2 - Caverna das Sombras");
            System.out.println("3 - Vila Abandonada");
            System.out.println("4 - Montanha Sombria");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha para onde ir: ");

            int opcao = Integer.parseInt(br.readLine());

            if (opcao == 1) {
                System.out.println("\nVocê adentra a Floresta Nebulosa...");
                System.out.println("O ar é denso e cheio de névoa...");

                System.out.println("Durante a exploração, o que deseja fazer?");
                System.out.println("1 - Seguir pela trilha da esquerda");
                System.out.println("2 - Entrar mais fundo na floresta");
                 System.out.println("3 - Seguir pela trilha da direita");
                System.out.println("4 - Encerrar jogo");

                int escolha = Integer.parseInt(br.readLine());

                if (escolha == 1) {
                    System.out.println("Você pisa em um galho... e ativa uma armadilha!");
                    System.out.println("Você perde 10 pontos de vida!");
                    jogador.pontosVida -= 10;
                }
                else if (escolha == 2) {
                    System.out.println("Das sombras surge um zumbi!");
                    Inimigo zumbi = new Inimigo("Zumbi", (short)50, (short)10, (short)5, (short)1, new Inventario());
                    batalhar(jogador, zumbi, br);
                }
                else if (escolha == 3) {
                    System.out.println("\nVocê segue pela trilha da direita...");
                    System.out.println("O caminho é mais estreito e coberto por teias antigas.");
                    System.out.println("O ar fica mais frio — um pressentimento ruim toma conta de você.");

                    System.out.println("\nEnquanto avança, uma sombra se move entre as árvores...");
                    System.out.println("De repente, um rugido agudo ecoa pela floresta!");
                    System.out.println("Uma aranha gigante salta de um galho — seus olhos brilham em vermelho vivo!");

                    Inimigo aranha = new Inimigo("Aranha da Névoa", (short)60, (short)12, (short)6, (short)1, new Inventario());
                    batalhar(jogador, aranha, br);

                    if (jogador.estaVivo()) {
                        System.out.println("\nVocê respira ofegante enquanto o corpo da aranha se dissolve em névoa...");
                        System.out.println("Entre os restos, algo brilha — um casulo preso em teias.");
                        System.out.println("Dentro, você encontra um frasco misterioso.");

                        int sorte = (int) (Math.random() * 2); // 0 ou 1, define o tipo de frasco

                        if (sorte == 0) {
                            System.out.println("Você abre o frasco e sente uma energia suave percorrer seu corpo!");
                            jogador.pontosVida += 15;
                            System.out.println("💖 Você recuperou 15 pontos de vida!");
                            jogador.inventario.adicionarItem(new Item("Essência da Floresta", "Poção de cura natural", "+15 HP", 1));
                            System.out.println("Você recebeu o item: Essência da Floresta!");
                        } else {
                            System.out.println("Ao abrir o frasco, uma fumaça negra se espalha...");
                            System.out.println("Um veneno sutil atinge seu corpo!");
                            jogador.pontosVida -= 10;
                            System.out.println("☠️ Você perdeu 10 pontos de vida!");

                            if(!jogador.estaVivo()) {
                                System.out.println("\nVocê sente o veneno corroer suas forças...");
                                System.out.println("Suas pernas fraquejam, e a escuridão toma conta da visão.");
                                System.out.println("💀 O veneno foi forte demais... você sucumbe na floresta.");
                                System.out.println("Fim de jogo!");
                                System.exit(0);
                            }
                            System.out.println("Mas... entre as teias, encontra um dente afiado da criatura.");
                            jogador.inventario.adicionarItem(new Item("Dente da Aranha", "Parte da fera derrotada", "Pode ser usado em poções", 1));
                            System.out.println("Você recebeu o item: Dente da Aranha!");
                        }
                    } else {
                        System.out.println("\nVocê sente o veneno da aranha correr pelo corpo...");
                        System.out.println("Tudo escurece... a floresta consome mais uma alma.");
                        System.out.println("💀 Fim de jogo!");
                        System.exit(0);
                    }
                }
                else if (escolha == 5) {
                    System.out.println("\nEncerrando o jogo. Até logo, aventureiro!");
                    explorando = false;
                }
                else {
                    System.out.println("Você retorna com cuidado ao início da floresta.");
                }
            }

            else if (opcao == 2) {
                System.out.println("\nVocê entra na Caverna das Sombras...");
                System.out.println("Uma voz ecoa nas profundezas...");

                System.out.println("Você encontra um baú misterioso.");
                System.out.println("Deseja abri-lo? (1 - Sim / 2 - Não / 3 - Encerrar jogo)");
                int abrir = Integer.parseInt(br.readLine());

                if (abrir == 1) {
                    System.out.println("Dentro há uma Poção de Cura!");
                    // TODO: IMPLEMENTAR OS CALCULO PARA RESTAURAR A VIDA
                    jogador.inventario.adicionarItem(new Item("Poção de Cura", "Restaura parte da vida", "+20 HP", 1));
                } else if (abrir == 3) {
                    System.out.println("Encerrando o jogo. Até logo, aventureiro!");
                    explorando = false;
                } else {
                    System.out.println("Você ignora o baú e segue em frente.");
                }
            }

            else if (opcao == 3) {
                System.out.println("\nVocê chega à Vila Abandonada...");
                System.out.println("As casas estão em ruínas e há marcas de magia nas paredes...");
                System.out.println("De repente, um feiticeiro aparece!");
                Inimigo feiticeiro = new Inimigo("Feiticeiro", (short)70, (short)15, (short)8, (short)2, new Inventario());
                batalhar(jogador, feiticeiro, br);

                // Menu de ações durante a exploração
                boolean continuar = menuExploracao(jogador, br,"Vila Abandonada");
                if (!continuar) {
                    explorando = false;
                }
            }

            else if (opcao == 4) {
                System.out.println("\nVocê escala a Montanha Sombria...");
                System.out.println("O chão treme sob seus pés... o dragão Rex desperta!");
                System.out.println("Este é o combate final!");
                Inimigo dragao = new Inimigo("Rex", (short)150, (short)25, (short)15, (short)5, new Inventario());
                batalhar(jogador, dragao, br);

                // Menu de ações durante a exploração
                boolean continuar = menuExploracao(jogador, br,"Montanha Sombria");
                if (!continuar) {
                    explorando = false;
                }
            }

            else if (opcao == 5) {
                System.out.println("\nVocê retorna ao menu principal.");
                explorando = false;
            }

            else {
                System.out.println("Opção inválida. Escolha novamente!");
            }
        }
    }

    // Menu de ações durante a exploração
    public static boolean menuExploracao(Personagem jogador, BufferedReader br, String local) throws IOException {
        System.out.println("\nO que deseja fazer agora?");
        System.out.println("1 - Usar item do inventário");
        System.out.println("2 - Fugir");
        System.out.println("3 - Encerrar jogo");

        int escolha = Integer.parseInt(br.readLine());

        if (escolha == 1) {
            usarItem(jogador, br); // chama o método de usar item
        }
        else if (escolha == 2) {
            fugir(jogador, br,local); // chama o método de fuga
        }
        else if (escolha == 3) {
            System.out.println("Encerrando o jogo. Até logo, aventureiro!");
            return false; // retorna false para encerrar o jogo
        }
        else {
            System.out.println("Opção inválida! Tente novamente.");
        }

        return true; // true = continua o jogo
    }

    public static void batalhar(Personagem jogador, Inimigo inimigo, BufferedReader br) throws IOException {
        System.out.println("\n=== ⚔️ BATALHA CONTRA " + inimigo.nome.toUpperCase() + " ===");

        jogador.batalhar(inimigo); // usa o método da classe Personagem

        if (!jogador.estaVivo()) {
            System.out.println("\n💀 Você foi derrotado... o Reino de Aurora cai nas sombras.");
            System.out.println("Fim de jogo!");
            System.exit(0); // encerra o jogo
        } else {
            System.out.println("\n✨ Você venceu a batalha contra " + inimigo.nome + "!");
            System.out.println("Você encontra alguns itens entre os restos do inimigo...");

            // Exemplo: o inimigo "solta" um item aleatório
            jogador.inventario.adicionarItem(new Item("Poção de Cura", "Restaura parte da vida", "+20 HP", 1));
            System.out.println("Você recebeu uma Poção de Cura!");
        }
    }

    // TODO: MÉTODOS PARA IMPLEMENTAR DEPOIS
    public static void usarItem(Personagem jogador, BufferedReader br) throws IOException {}
    // TODO: NA CLASSE DEIXAR ALGUNS ITENS ADICIONADOS E FAZER A LÓGICA DE QUE QUANDO FOR USAR ELE VÁ DIMINUINDO

    public static void fugir(Personagem jogador, BufferedReader br, String local) throws IOException {
        System.out.println("\n🏃 Você tenta fugir...");

        Random random = new Random();
        int rolagem = random.nextInt(6) + 1; // dado de 6 lados (1 a 6)

        if (rolagem % 2 == 0) {
            // Sucesso
            System.out.println("Você consegue escapar com sucesso!");
            System.out.println("Você respira aliviado e continua sua jornada.");
        } else {
            //  Falha
            System.out.println("Você tropeça ao tentar fugir!");
            System.out.println("O inimigo te alcança e acerta um golpe antes de você escapar!");

            short dano = 10;
            jogador.receberDano(dano);
            System.out.println("☠️ Você perdeu " + dano + " pontos de vida. HP atual: " + jogador.pontosVida);

            if (!jogador.estaVivo()) {
                System.out.println("\n💀 Você não resistiu ao golpe durante a fuga...");
                System.out.println("Fim de jogo!");
                System.exit(0);
            } else {
                System.out.println("Mesmo ferido, você consegue se afastar do perigo.");
            }
        }

        // Texto baseado no local
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

        System.out.println("\nVocê se esconde " + esconderijo + " e retoma a exploração.");
    }

}
