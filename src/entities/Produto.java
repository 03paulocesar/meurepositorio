package entities;

public class Produto extends BaseEntity {
    private double preco;
    private int quantidade;
    private String categoria;
    private String codigoBarras;

    public Produto() {
        super();
    }

    public Produto(Long id, String nome, String descricao, double preco, int quantidade, String categoria, String codigoBarras) {
        super(id, nome, descricao);
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.codigoBarras = codigoBarras;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public String toString() {
        return super.toString() + 
               ", Preço: R$" + preco + 
               ", Quantidade: " + quantidade + 
               ", Categoria: " + categoria + 
               ", Código de Barras: " + codigoBarras;
    }
} 