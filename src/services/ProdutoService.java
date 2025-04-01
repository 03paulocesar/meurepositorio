package services;

import entities.Produto;
import repositories.IProdutoRepository;
import java.util.List;

public class ProdutoService implements IProdutoService {
    private IProdutoRepository repository;

    public ProdutoService(IProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        repository.salvar(produto);
    }

    @Override
    public void removerProduto(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        repository.remover(id);
    }

    @Override
    public void atualizarProduto(Produto produto) {
        if (produto.getId() == null) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo");
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero");
        }
        repository.alterar(produto);
    }

    @Override
    public List<Produto> listarProdutos() {
        return repository.listar();
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return repository.buscarPorId(id);
    }

    @Override
    public Produto buscarProdutoPorCodigoBarras(String codigoBarras) {
        if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
            throw new IllegalArgumentException("Código de barras não pode ser vazio");
        }
        return repository.buscarPorCodigoBarras(codigoBarras);
    }

    @Override
    public void adicionarEstoque(Long id, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a adicionar deve ser maior que zero");
        }
        Produto produto = buscarProdutoPorId(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        repository.alterar(produto);
    }

    @Override
    public void removerEstoque(Long id, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a remover deve ser maior que zero");
        }
        Produto produto = buscarProdutoPorId(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        if (produto.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        repository.alterar(produto);
    }
} 