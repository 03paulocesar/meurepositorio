package repositories;

import entities.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository implements IProdutoRepository {
    private List<Produto> produtos;
    private Long proximoId;

    public ProdutoRepository() {
        this.produtos = new ArrayList<>();
        this.proximoId = 1L;
    }

    @Override
    public void salvar(Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
    }

    @Override
    public void remover(Long id) {
        produtos.removeIf(produto -> produto.getId().equals(id));
    }

    @Override
    public void alterar(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(produto.getId())) {
                produtos.set(i, produto);
                break;
            }
        }
    }

    @Override
    public List<Produto> listar() {
        return new ArrayList<>(produtos);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Produto buscarPorCodigoBarras(String codigoBarras) {
        return produtos.stream()
                .filter(produto -> produto.getCodigoBarras().equals(codigoBarras))
                .findFirst()
                .orElse(null);
    }
} 