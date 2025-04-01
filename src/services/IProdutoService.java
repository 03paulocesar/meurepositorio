package services;

import entities.Produto;
import java.util.List;

public interface IProdutoService {
    void cadastrarProduto(Produto produto);
    void removerProduto(Long id);
    void atualizarProduto(Produto produto);
    List<Produto> listarProdutos();
    Produto buscarProdutoPorId(Long id);
    Produto buscarProdutoPorCodigoBarras(String codigoBarras);
    void adicionarEstoque(Long id, int quantidade);
    void removerEstoque(Long id, int quantidade);
} 