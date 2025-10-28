import java.util.ArrayList;

// Classe Inventário
public class Inventario {

    // Criando uma lista chamada itens que vai guardar os objetos da classe Item
    private ArrayList<Item> itens;

    // Construtor da classe (cria um inventário (lista) vazio)
    // Chamado toda vez que crio um novo inventário
    public Inventario() {
        this.itens = new ArrayList<>();

        // Faca no inventário de todos os jogadores
        Item facaInicial = new Item(
                "Faca Inicial",
                "Uma lâmina simples, mas confiável.",
                "+5 ATQ",
                1
        );
        this.itens.add(facaInicial);
    }

    // Construtor de cópia: cria um inventário igual ao atual (outro)
    public Inventario(Inventario outro) {

        // Criamos uma variável chamada listaNormal que aponta para lista de itens do inventário original
        ArrayList<Item> listaNormal = outro.itens;

        // Criamos uma lista vazia que vai guardar os itens copiados
        ArrayList<Item> listaCopia = new ArrayList<>();

        // Percorre cada item da listaNormal (lista original)
        for (Item item : listaNormal) {
            // Cria um novo objeto Item com os mesmos valores do item original (cópia independente)
            Item novoItem = new Item(
                    item.getNome(),
                    item.getDescricao(),
                    item.getEfeito(),
                    item.getQuantidade()
            );

            // Adiciono o item que acabou de copiar (novoItem) dentro da lista listaCopia
            listaCopia.add(novoItem);
        }

        // Define que a lista oficial do novo inventário será a listaCopia
        this.itens = listaCopia;
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

    // Método para criar uma cópia independente do inventário atual
    @Override
    public Object clone() {

        // Variável que vai guardar o novo inventário criado (definido como vazio)
        Inventario inventarioCopiado = null;

        try {
            // Crie um novo inventário do zero, usando o construtor copia da classe inventário e adicione a inventarioCopiado
            inventarioCopiado = new Inventario(this);
        }
        catch (Exception erro) {
            System.out.println("Erro ao clonar inventário!");
        }

        return inventarioCopiado;
    }
}
