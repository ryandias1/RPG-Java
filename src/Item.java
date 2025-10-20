public class Item {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    public Item (String nome, String descricao, String efeito, int quantidade){
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    @Override
    public String toString(){
        return "Nome: "+nome+", Descrição: "+descricao+", Efeito: "+efeito+", Quantidade: "+quantidade;
    }

    @Override
    public boolean equals(Object obj){
        if (this==obj) return true;

        if(obj==null) return false;

        if(this.getClass()!= obj.getClass()) return false;

        Item outro = (Item) obj;
        if(this.nome!= outro.nome) return false;
        if(this.descricao!= outro.descricao) return false;
        if(this.efeito!= outro.efeito) return false;
        if(this.quantidade!= outro.quantidade) return false;

        return true;
    }

    /*@Override
    public int compareTo(Item i){
        if(this.nome)
    }*/
}


