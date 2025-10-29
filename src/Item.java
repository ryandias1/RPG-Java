/// Classe Item (obrigatório tem um compareTo())
public class Item implements Comparable<Item> {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    // Construtor da classe Itens (chamado quando você cria um item)
    public Item (String nome, String descricao, String efeito, int quantidade){
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    // Getters (acessar os dados)
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEfeito() {
        return efeito;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // Setter (alterar os dados)
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // toString do Item
    @Override
    public String toString(){
        return "Nome: "+nome+", Descrição: "+descricao+", Efeito: "+efeito+", Quantidade: "+quantidade;
    }

    // Para saber se dois itens são iguais
    @Override
    public boolean equals(Object obj) {

        // Verifica se são exatamente o mesmo na memória
        if (this == obj)
            return true; // Retorna true e não compara (é o mesmo objeto)

        // Verifica se obj é null e se as classe são diferentes
        if (obj == null || getClass() != obj.getClass())
            return false; // Não são iguais (classes diferentes)

        // Garante que agora pode tratar obj como um Item
        Item outro = (Item) obj;

        // Compara nome, descrição e efeito ignorando maiúsculas
        return nome.equalsIgnoreCase(outro.nome)
                && descricao.equalsIgnoreCase(outro.descricao)
                && efeito.equalsIgnoreCase(outro.efeito);
    }

    // Comparar dois itens pelo nome, em ordem alfabética
    @Override
    public int compareTo(Item i){
        return this.nome.compareTo(i.nome);
    }
}


