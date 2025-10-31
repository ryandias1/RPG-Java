import java.util.ArrayList;
import java.util.Collections;

/// Classe Inventário
public class Inventario implements Cloneable {

    // Cria uma lista chamada itens que vai guardar os objetos da classe Item
    private ArrayList<Item> itens;

    /// Construtor padrão
    // È chamado toda vez que eu crio um novo inventário, inicialmente vazio
    public Inventario() {
        this.itens = new ArrayList<>();
    }

    /// Adicionar item ao inventário
    public void adicionarItem(Item novoItem) { // Recebe um parâmetro do tipo Item, chamado novoItem
        // Percorrendo cada item que já existe na lista "itens"
        for (Item item : itens) {

            // Pego o nome do item atual da lista e o que está sendo adicionado
            // Comparo (equals) se o nome do item novo é igual ao de algum que já está na lista
            // Transformo o nome antigo e o novo para minúsculo antes de comparar
            if (item.getNome().toLowerCase().equals(novoItem.getNome().toLowerCase())) {

                // Se o nome for igual fazemos as somas das quantidade (antiga + nova) e atualizamos o valor somado
                item.setQuantidade(item.getQuantidade() + novoItem.getQuantidade());
                return;
            }
        }
        // Adicionado normalmente a lista
        itens.add(novoItem);
    }

    /// Remover um item do inventário
    public void removerItem(String nomeItem, int quantidade) {
        // Remove espaços em branco no início e no fim do texto (evita erro na comparação)
        nomeItem = nomeItem.trim();

        // Percorre a lista de itens no inventário de acordo com o tamanho dela
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i); // Pega o item atual da lista na posição i

            // Verifica se o item digitado pelo jogador existe no inventário
            // Pega o nome do item armazenado (classe Item) e o nome digitado pelo jogador, convertendo ambos para minúsculas
            // Depois, verifica se o nome do item do inventário contém o texto digitado pelo jogador
            if (item.getNome().toLowerCase().contains(nomeItem.toLowerCase())) {
                int novaQtd = item.getQuantidade() - quantidade; // Calcula o novo total de itens depois da remoção

                // Se ainda sobrar atualiza a quantidade
                if (novaQtd > 0) {
                    item.setQuantidade(novaQtd);
                } else {
                    // Caso a nova quantidade seja zero ou negativa, o código remove o item inteiro do inventário
                    itens.remove(i);
                }
                return;
            }
        }
        System.out.println("Item não encontrado no inventário.");
    }

    /// Listar todos os itens do inventário
    public boolean listarItens() {
        // Verifica se a lista itens está vazia
        if (itens.isEmpty()) {
            System.out.println("\nSeu inventário está vazio.");
            return false;
        }

        // Ordena os itens da lista em ordem alfabética (sort)
        // O Collections é uma classe do Java usada para manipular listas (como ArrayList)
        // Aqui, ele chama automaticamente o compareTo() da classe Item, que define a comparação pelo nome do item
        Collections.sort(itens);

        // Percorre a lista inteira e imprime os itens
        System.out.println("\n=== ITENS NO INVENTÁRIO ===");
        for (Item item : itens) {
            System.out.println(item);
        }
        System.out.println("==============================");
        return true; // Retorna true se tiver itens
    }

    /// Verificar se está o inventário está vazio
    public boolean estaVazio() {
        return itens.isEmpty(); // Retorna true se a lista não tiver itens, false caso contrário
    }

    /// Verifica se o jogador tem um item específico no inventário
    public boolean temItem(String nomeItem) {
        // Pega o nome digitado pelo jogador, remove espaços extras e converte tudo para minúsculas (evita erros)
        nomeItem = nomeItem.trim().toLowerCase();

        // Percorre cada objeto da classe Item dentro da lista de itens
        for (Item item : itens) {
            // Pega o nome do item atual, transforma em minúsculas
            // e verifica se o texto digitado pelo jogador está contido dentro desse nome
            if (item.getNome().toLowerCase().contains(nomeItem)) {
                return true;
            }
        }
        return false;
    }

    // Retorna a lista completa de itens do inventário
    // Ele é usado internamente em situações como clonagem ou saque de itens de um inimigo
    public ArrayList<Item> getItens() {
        return this.itens; // Retorna a lista armazenada dentro do inventário
    }

    /// Clone()
    // Cria um novo objeto com os mesmos dados, mas em outro espaço da memória (cópias independentes)
    // Chamamos dentro do clone o construtor de cópia do inventário, e dentro dele é que cada item é clonado individualmente
    @Override
    public Object clone() {
        Inventario retorno = null; // Variável que vai guardar a cópia criada

        try
        {
            // Chamo o construtor de cópia, passando o próprio inventário atual (this)
            // Assim, é criado um novo objeto Inventario com os mesmos itens do original, mas de forma independente (alterar um não afeta o outro)
            retorno = new Inventario(this);
        }

        // Esse erro quase nunca ocorre, pois o objeto atual (this) dificilmente é nulo
        catch (Exception erro) {}

        // Retorna o novo Inventário criado (a cópia independente do original)
        return retorno;
    }

    /// Construtor de cópia
    // É chamado quando você quer criar um novo inventário igual a outro, mas de forma independente (ou seja, alterar um não muda o outro).
    public Inventario(Inventario modelo) throws Exception {

        // Verifica se o inventário passado existe
        if (modelo == null)
            throw new Exception("Modelo ausente");

        // Cria uma nova lista de itens vazia (independente da original)
        this.itens = new ArrayList<>();

        // Percorre todos os itens do inventário original, e adiciona clones (chama o clone() de cada item) deles ao novo inventário
        for (Item item : modelo.itens)
            this.itens.add((Item)item.clone());
    }

    /// ToString do inventário
    @Override
    public String toString() {
        return "Inventário com " + itens.size() + " itens.";
    }
}
