/// Classe Item
// Nela é permitida comparar dois itens (Comparable) e criar cópiar idênticas (Cloneable - clone())
public class Item implements Comparable<Item>, Cloneable {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    /// Construtor padrão
    // É chamado toda vez que cria um novo item
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

    /// ToString() do Item
    @Override
    public String toString(){
        return "Nome: "+nome+", Descrição: "+descricao+", Efeito: "+efeito+", Quantidade: "+quantidade;
    }

    /// Equals()
    // Para saber se dois itens são iguais
    @Override
    public boolean equals(Object obj) {

        // Se as duas variáveis apontam para o mesmo objeto na memória, já é igual
        if (this == obj)
            return true;

        // Se obj (objeto que recebo para comparar) for null, não há o que comparar; e
        // se as classes forem diferentes, também não são iguais (ambos precisam ser exatamente da classe Item)
        if (obj == null || getClass() != obj.getClass())
            return false;

        // Avisamos ao Java que queremos tratar obj como Item
        Item outro = (Item) obj;

        // Compara nome, descrição e efeito dos dois itens que queremos comparar
        // Transforma tudo para minúsculo ignorando maiúsculas
        return nome.equalsIgnoreCase(outro.nome)
                && descricao.equalsIgnoreCase(outro.descricao)
                && efeito.equalsIgnoreCase(outro.efeito);
    }

    /// compareTo()
    // Define como um Item deve ser comparado com outro (em ordem alfabética nesse caso)
    // Aqui comparamos pelo nome, em ordem alfabética, ignorando maiúsculas/minúsculas
    @Override
    public int compareTo(Item i){
        // Compara o nome do item atual (this.nome) com o nome do outro item (i.nome)
        // Retorna < 0 se this vem antes, 0 se são equivalentes, > 0 se this vem depois
        return this.nome.compareToIgnoreCase(i.nome);
    }

    /// Construtor de cópia
    // É chamado quando você quer criar um novo item igual a outro, mas de forma independente (ou seja, alterar um não muda o outro)
    public Item(Item modelo) throws Exception
    {
        // Verifica se o item passado existe
        if (modelo == null)
            throw new Exception("Modelo ausente");

        // Copiamos cada atributo do modelo original para o novo objeto
        // Isso garante que o novo Item tenha os mesmos valores, mas é um objeto independente
        this.nome       = modelo.nome;
        this.descricao  = modelo.descricao;
        this.efeito     = modelo.efeito;
        this.quantidade = modelo.quantidade;
    }

    /// Clone()
    // Cria um novo objeto com os mesmos dados, mas em outro espaço da memória (cópias independentes)
    // Chamamos dentro do clone o construtor de cópia do item, e dentro dele é que cada item é clonado individualmente
    @Override
    public Object clone()
    {
        Item retorno = null; // Variável que vai guardar a cópia criada

        try
        {
            // Chamo o construtor de cópia, passando o próprio item atual (this)
            // Assim, é criado um novo objeto Inventario com os mesmos itens do original, mas de forma independente (alterar um não afeta o outro)
            retorno = new Item(this);
        }

        // Esse erro quase nunca ocorre, pois o objeto atual (this) dificilmente é nulo
        catch (Exception erro) { }

        // Retorna o novo Inventário criado (a cópia independente do original)
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


