// Classe Item
public class Item implements Comparable<Item> {

    // Atributos
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    // Construtor da classe Itens
    public Item (String nome, String descricao, String efeito, int quantidade){
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    // Getters e Setters
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

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Define como o texto será exibido
    @Override
    public String toString(){
        return "Nome: "+nome+", Descrição: "+descricao+", Efeito: "+efeito+", Quantidade: "+quantidade;
    }

    // Para saber se dois itens são iguais
    @Override
    public boolean equals(Object obj){
        if (this==obj) return true;

        if(obj==null) return false;

        if(this.getClass()!= obj.getClass()) return false;

        Item outro = (Item) obj;
        if(this.nome.equalsIgnoreCase(outro.nome)) return false;
        if(this.descricao.equalsIgnoreCase(outro.descricao)) return false;
        if(this.efeito.equalsIgnoreCase(outro.efeito)) return false;

        return true;
    }

    // Comparar dois itens pelo nome, em ordem alfabética
    @Override
    public int compareTo(Item i){
        return this.nome.compareTo(i.nome);
    }
}


