/// Classe Item (obrigatório tem um compareTo())
public class Item implements Comparable<Item>, Cloneable {
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

    // Construtor de cópia (cria um novo objeto Item idêntico ao modelo passado)
    public Item(Item modelo) throws Exception
    {
        // Verifica se o modelo existe (evita copiar algo nulo)
        if (modelo == null)
            throw new Exception("Modelo ausente");

        // Copiamos cada atributo do modelo original para o novo objeto
        // (isso garante que o novo Item tem os mesmos valores, mas é um objeto independente)
        this.nome       = modelo.nome;
        this.descricao  = modelo.descricao;
        this.efeito     = modelo.efeito;
        this.quantidade = modelo.quantidade;
    }



    // Método clone() — cria e retorna uma cópia independente do item atual
    @Override
    public Object clone()
    {
        Item retorno = null; // variável que vai guardar a cópia criada

        try
        {
            // Chama o construtor de cópia, passando o próprio objeto atual (this)
            // Isso cria um novo Item com os mesmos dados do item original
            retorno = new Item(this);
        }
        catch (Exception erro)
        {
            // Esse erro praticamente nunca acontece,
            // pois o "this" (objeto atual) nunca é nulo
        }

        // Retorna o novo Item criado (cópia independente do original)
        return retorno;
    }

}


