package repositories;

import entities.Produto;
import java.util.List;

public interface IProdutoRepository {
    void salvar(Produto produto);
    void remover(Long id);
    void alterar(Produto produto);
    List<Produto> listar();
    Produto buscarPorId(Long id);
    Produto buscarPorCodigoBarras(String codigoBarras);
} 