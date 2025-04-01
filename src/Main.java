import entities.Produto;
import repositories.IProdutoRepository;
import repositories.ProdutoRepository;
import services.IProdutoService;
import services.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static IProdutoRepository produtoRepository = new ProdutoRepository();
    private static IProdutoService produtoService = new ProdutoService(produtoRepository);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    removerProduto();
                    break;
                case 6:
                    adicionarEstoque();
                    break;
                case 7:
                    removerEstoque();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE CONTROLE DE ESTOQUE ===");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Buscar Produto");
        System.out.println("4. Atualizar Produto");
        System.out.println("5. Remover Produto");
        System.out.println("6. Adicionar ao Estoque");
        System.out.println("7. Remover do Estoque");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProduto() {
        try {
            System.out.println("\n=== CADASTRO DE PRODUTO ===");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine();
            System.out.print("Código de Barras: ");
            String codigoBarras = scanner.nextLine();

            Produto produto = new Produto(null, nome, descricao, preco, quantidade, categoria, codigoBarras);
            produtoService.cadastrarProduto(produto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    private static void listarProdutos() {
        try {
            System.out.println("\n=== LISTA DE PRODUTOS ===");
            List<Produto> produtos = produtoService.listarProdutos();
            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
            } else {
                produtos.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    private static void buscarProduto() {
        try {
            System.out.println("\n=== BUSCA DE PRODUTO ===");
            System.out.println("1. Buscar por ID");
            System.out.println("2. Buscar por Código de Barras");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            Produto produto = null;
            if (opcao == 1) {
                System.out.print("ID do produto: ");
                Long id = scanner.nextLong();
                produto = produtoService.buscarProdutoPorId(id);
            } else if (opcao == 2) {
                System.out.print("Código de Barras: ");
                String codigoBarras = scanner.nextLine();
                produto = produtoService.buscarProdutoPorCodigoBarras(codigoBarras);
            }

            if (produto != null) {
                System.out.println(produto);
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
    }

    private static void atualizarProduto() {
        try {
            System.out.println("\n=== ATUALIZAÇÃO DE PRODUTO ===");
            System.out.print("ID do produto: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Limpar o buffer

            Produto produto = produtoService.buscarProdutoPorId(id);
            if (produto != null) {
                System.out.print("Novo nome: ");
                produto.setNome(scanner.nextLine());
                System.out.print("Nova descrição: ");
                produto.setDescricao(scanner.nextLine());
                System.out.print("Novo preço: ");
                produto.setPreco(scanner.nextDouble());
                System.out.print("Nova quantidade: ");
                produto.setQuantidade(scanner.nextInt());
                scanner.nextLine(); // Limpar o buffer
                System.out.print("Nova categoria: ");
                produto.setCategoria(scanner.nextLine());
                System.out.print("Novo código de barras: ");
                produto.setCodigoBarras(scanner.nextLine());

                produtoService.atualizarProduto(produto);
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    private static void removerProduto() {
        try {
            System.out.println("\n=== REMOÇÃO DE PRODUTO ===");
            System.out.print("ID do produto: ");
            Long id = scanner.nextLong();
            produtoService.removerProduto(id);
            System.out.println("Produto removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
        }
    }

    private static void adicionarEstoque() {
        try {
            System.out.println("\n=== ADICIONAR AO ESTOQUE ===");
            System.out.print("ID do produto: ");
            Long id = scanner.nextLong();
            System.out.print("Quantidade a adicionar: ");
            int quantidade = scanner.nextInt();
            produtoService.adicionarEstoque(id, quantidade);
            System.out.println("Estoque atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar ao estoque: " + e.getMessage());
        }
    }

    private static void removerEstoque() {
        try {
            System.out.println("\n=== REMOVER DO ESTOQUE ===");
            System.out.print("ID do produto: ");
            Long id = scanner.nextLong();
            System.out.print("Quantidade a remover: ");
            int quantidade = scanner.nextInt();
            produtoService.removerEstoque(id, quantidade);
            System.out.println("Estoque atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover do estoque: " + e.getMessage());
        }
    }
} 