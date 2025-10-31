import java.util.ArrayList;
import java.util.Collections;

/// Classe Inventário
public class Inventario implements Cloneable {

    // Criando uma lista chamada itens que vai guardar os objetos da classe Item
    private ArrayList<Item> itens;

    /// Construtor da padrão
    // È chamado toda vez que eu crio um novo inventário, inicialmente vazio
    public Inventario() {
        this.itens = new ArrayList<>();
    }

    /// Adicionar item ao inventário
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
        nomeItem = nomeItem.trim(); // Remove espaços extras antes e depois do nome digitado

        // Percorre a lista de acordo com o tamanho dela (itens.size())
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i); // Pega o item atual da lista na posição i

            // Verifica se o texto digitado pelo jogador corresponde (mesmo parcialmente - contains) ao nome do item
            if (item.getNome().toLowerCase().contains(nomeItem.toLowerCase())) {
                int novaQtd = item.getQuantidade() - quantidade; // Calcula quanto vai sobrar do item depois da remoção

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
        // Se não tiver itens
        if (itens.isEmpty()) {
            System.out.println("\nSeu inventário está vazio.");
            return false;
        }

        // Ordena os itens da nossa lista em ordem alfabética pelo nome
        // Usamos o compareTo da classe itens
        Collections.sort(itens);

        // Percorre a lista intera e imprime os itens
        System.out.println("\n=== ITENS NO INVENTÁRIO ===");
        for (Item item : itens) {
            System.out.println(item);
        }
        System.out.println("==============================");
        return true; // Retorna true se tiver itens
    }

    /// Verificar se está vazio
    public boolean estaVazio() {
        return itens.isEmpty(); // Retorna true se a lista estiver vazia
    }


    /// Verifica se um item com o nome informado existe no inventário
    public boolean temItem(String nomeItem) {
        // Pega o texto que o usuário digitou, remove os espaços extras e passa tudo para minúsculo
        nomeItem = nomeItem.trim().toLowerCase();

        // Percorre cada objeto Item dentro da lista itens
        for (Item item : itens) {
            // Pega o nome do item atual
            // Transforma em minúsculo
            // Verifica se o nome do item contém o texto digitado
            if (item.getNome().toLowerCase().contains(nomeItem)) {
                return true;
            }
        }
        return false;
    }

    // Retorna a lista de itens (usado apenas internamente para clonar ou saquear)
    public ArrayList<Item> getItens() {
        return this.itens;
    }

    /// clone()
    // Cria uma cópia independente do inventário atual
    @Override
    public Object clone() {
        Inventario retorno = null; // Variável que vai guardar a cópia criada

        try
        {
            // Chama o construtor de cópia do inventário, passando o próprio objeto atual (this)
            // Isso cria um novo Inventário com os mesmos dados do Inventário original
            retorno = new Inventario(this);
        }

        // Esse erro praticamente nunca acontece, pois o "this" (objeto atual) nunca é nulo
        catch (Exception erro) {}

        // Retorna o novo Inventário criado (cópia independente do original)
        return retorno;
    }

    /// Construtor de cópia
    // Cria um outro inventário igual ao atual
    public Inventario(Inventario modelo) throws Exception {
        // Verifica se o invantário passado existe
        if (modelo == null)
            throw new Exception("Modelo ausente");

        // Cria uma nova lista de itens (independente da original)
        this.itens = new ArrayList<>();

        // Percorre todos os itens do inventário original, e adiciona clones deles ao novo inventário
        for (Item item : modelo.itens)
            this.itens.add((Item)item.clone());
    }

    @Override
    public String toString() {
        return "Inventário com " + itens.size() + " itens.";
    }


}
