import java.util.ArrayList;

// Classe Inventário
public class Inventario implements Cloneable {

    // Criando uma lista chamada itens que vai guardar os objetos da classe Item
    private ArrayList<Item> itens;

    // Construtor da classe (cria um inventário (lista) vazio)
    // Chamado toda vez que crio um novo inventário
    public Inventario() {
        this.itens = new ArrayList<>();
    }

    // Método para adicionar um item ao inventário
    public void adicionarItem(Item novoItem) {
        // Percorrendo cada item que já existe na lista
        for (Item item : itens) {

            // Comparo (equals) se o nome do item novo é igual ao de algum que já está na lista
            // Transformo o nome antigo e o novo para minúsculo antes de comparar
            if (item.getNome().toLowerCase().equals(novoItem.getNome().toLowerCase())) {
                // Se o nome for igual fazemos as somas das quantidade (antiga + nova)
                item.setQuantidade(item.getQuantidade() + novoItem.getQuantidade());
                return;
            }
        }
        // Adicionado normalmente a lista
        itens.add(novoItem);
    }

    /// Remover um item do inventário
    public void removerItem(String nomeItem, int quantidade) {
        // Remove espaços extras antes e depois do nome digitado
        nomeItem = nomeItem.trim();

        // Percorre a lista de acordo com o tamanho dela (itens.size())
        for (int i = 0; i < itens.size(); i++) {
            // Pega o item atual da lista na posição i
            Item item = itens.get(i);

            // Verifica se o nome do item atual é igual ao nome passado como parâmetro
            if (item.getNome().toLowerCase().equals(nomeItem.toLowerCase())) {
                // Calcula quanto vai sobrar do item depois da remoção
                int novaQtd = item.getQuantidade() - quantidade;
                // Se ainda sobrar atualiza a quantidade
                if (novaQtd > 0) {
                    item.setQuantidade(novaQtd);
                } else {
                    // Se zerar ou ficar nagativo romove o item completamente da lista
                    itens.remove(i);
                }
                return;
            }
        }
        System.out.println("Item não encontrado no inventário.");
    }

    /// Listar todos os itens do inventário
    public boolean listarItens() {
        if (itens.isEmpty()) {
            System.out.println("\nSeu inventário está vazio.");
            return false; // retorna falso se estiver vazio
        }

        System.out.println("\n=== ITENS NO INVENTÁRIO ===");
        for (Item item : itens) {
            System.out.println(item);
        }
        System.out.println("==============================");
        return true; // retorna true se tiver itens
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }


    // Verifica se um item com o nome informado existe no inventário
    public boolean temItem(String nomeItem) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nomeItem.trim())) {
                return true;
            }
        }
        return false;
    }

    // Método para criar uma cópia independente do inventário atual
    @Override
    public Object clone() {
        Inventario retorno = null;

        try
        {
            // Cria um novo inventário usando o construtor de cópia
            retorno = new Inventario(this);
        }
        catch (Exception erro)
        {} // sei que o this nunca será null, então o único erro possível é improvável

        return retorno;
    }

    // Construtor de cópia: cria um inventário igual ao atual (outro)
    public Inventario(Inventario modelo) throws Exception {
        // Verifica se o modelo passado existe
        if (modelo == null)
            throw new Exception("Modelo ausente");

        // Cria uma nova lista de itens (independente da original)
        this.itens = new ArrayList<>();

        // Clonamos os itens um por um, pois Item é um objeto
        // e queremos uma cópia independente (deep copy)
        for (Item item : modelo.itens)
            this.itens.add((Item)item.clone());
    }

}
