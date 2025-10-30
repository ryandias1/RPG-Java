/// Classe Item (obrigatório tem um compareTo())
public class Item implements Comparable<Item>, Cloneable {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    /// Construtor da classe Itens
    // Chamado quando você cria um item
    public Item (String nome, String descricao, String efeito, int quantidade){
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;
    }

    /// Getters (acessar os dados)
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

    /// Setter (alterar os dados)
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /// toString() do Item
    @Override
    public String toString(){
        return "Nome: "+nome+", Descrição: "+descricao+", Efeito: "+efeito+", Quantidade: "+quantidade;
    }

    /// equals()
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

    /// compareTo()
    // Comparar dois itens pelo nome, em ordem alfabética
    @Override
    public int compareTo(Item i){
        return this.nome.compareToIgnoreCase(i.nome);
    }

    /// Construtor de cópia
    // Cria um novo objeto Item idêntico ao modelo passado (irá ser usado dentro do clone())
    public Item(Item modelo) throws Exception
    {
        // Verifica se o modelo existe (evita copiar algo nulo)
        if (modelo == null)
            throw new Exception("Modelo ausente");

        // Copiamos cada atributo do modelo original para o novo objeto
        // Isso garante que o novo Item tem os mesmos valores, mas é um objeto independente
        this.nome       = modelo.nome;
        this.descricao  = modelo.descricao;
        this.efeito     = modelo.efeito;
        this.quantidade = modelo.quantidade;
    }

    /// clone()
    // Cria e retorna uma cópia independente do item atual (chamamos o construtor copia de itens aqui)
    @Override
    public Object clone()
    {
        Item retorno = null; // Variável que vai guardar a cópia criada

        try
        {
            // Chama o construtor de cópia do item, passando o próprio objeto atual (this)
            // Isso cria um novo Item com os mesmos dados do item original
            retorno = new Item(this);
        }

        // Esse erro praticamente nunca acontece, pois o "this" (objeto atual) nunca é nulo
        catch (Exception erro) { }

        // Retorna o novo Item criado (cópia independente do original)
        return retorno;
    }

    @Override
    public int hashCode() {
        int ret = 1;
        //case-insensitive pois equals é
        ret = 2 * ret + (nome == null ? 0 : nome.toLowerCase().hashCode());
        ret = 2 * ret + (descricao == null ? 0 : descricao.toLowerCase().hashCode());
        ret = 2 * ret + (efeito == null ? 0 : efeito.toLowerCase().hashCode());
        return ret;
    }

}


