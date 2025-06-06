package StockManagement.isp;

import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StockControl stock = new StockControl();

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    inserirProduto();
                    break;
                case "2":
                    pesquisarProduto();
                    break;
                case "3":
                    atualizarProduto();
                    break;
                case "4":
                    listarProdutos();
                    break;
                case "5":
                    registarVenda();
                    break;
                case "6":
                    removerProduto();
                    break;
                case "7":
                    calcularValorTotalStock();
                    break;
                case "8":
                    verificarProdutoEmFalta();
                    break;
                case "9":
                    encontrarProdutoMaisCaro();
                    break;
                case "10":
                    verificarStockBaixo();
                    break;
                case "11":
                    encontrarProdutoMaisVendido();
                    break;
                case "12":
                    gerarRelatorioProdutosEmFalta();
                    break;
                case "13":
                    transferirStockEntreStocks();
                    break;
                case "0":
                    System.out.println("Programa terminado.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== Gestão de Stock ===");
        System.out.println("1. Inserir novo produto");
        System.out.println("2. Pesquisar produto por ID");
        System.out.println("3. Atualizar produto");
        System.out.println("4. Listar todos os produtos");
        System.out.println("5. Registar venda");
        System.out.println("6. Remover produto");
        System.out.println("7. Calcular valor total do stock");
        System.out.println("8. Verificar se produto está em falta");
        System.out.println("9. Encontrar produto mais caro");
        System.out.println("10. Verificar stock baixo");
        System.out.println("11. Encontrar produto mais vendido");
        System.out.println("12. Gerar relatório de produtos em falta");
        System.out.println("13. Transferir stock entre stocks");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void inserirProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço unitário: ");
        double preco = lerDouble();

        System.out.print("Quantidade inicial: ");
        int quantidade = lerInt();

        stock.inserirProduto(nome, preco, quantidade);
        System.out.println("Produto inserido com sucesso.");
    }

    private static void pesquisarProduto() {
        System.out.print("ID do produto: ");
        int id = lerInt();

        Product p = stock.pesquisarProdutoPorId(id);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void atualizarProduto() {
        System.out.print("ID do produto: ");
        int id = lerInt();

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo preço unitário: ");
        double preco = lerDouble();

        System.out.print("Nova quantidade: ");
        int quantidade = lerInt();

        boolean atualizado = stock.atualizarProduto(id, nome, preco, quantidade);
        if (atualizado) {
            System.out.println("Produto atualizado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void listarProdutos() {
        LinkedList<Product> lista = stock.listarProdutos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto no stock.");
            return;
        }
        for (Product p : lista) {
            System.out.println(p);
        }
    }

    private static void registarVenda() {
        System.out.print("ID do produto: ");
        int id = lerInt();

        Product p = stock.pesquisarProdutoPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade vendida: ");
        int quantidade = lerInt();

        boolean sucesso = stock.registarVenda(p, quantidade);
        if (sucesso) {
            System.out.println("Venda registada com sucesso.");
        } else {
            System.out.println("Quantidade insuficiente em stock.");
        }
    }

    private static void removerProduto() {
        System.out.print("ID do produto a remover: ");
        int id = lerInt();

        boolean removido = stock.removerProduto(id);
        if (removido) {
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void calcularValorTotalStock() {
        double total = stock.calcularValorTotalStock();
        System.out.printf("Valor total do stock: %.2f\n", total);
    }

    private static void verificarProdutoEmFalta() {
        System.out.print("ID do produto: ");
        int id = lerInt();

        boolean emFalta = stock.estaEmFalta(id);
        if (emFalta) {
            System.out.println("Produto está em falta (stock zero).");
        } else {
            System.out.println("Produto não está em falta.");
        }
    }

    private static void encontrarProdutoMaisCaro() {
        Product p = stock.encontrarProdutoMaisCaro();
        if (p != null) {
            System.out.println("Produto mais caro:");
            System.out.println(p);
        } else {
            System.out.println("Nenhum produto no stock.");
        }
    }

    private static void verificarStockBaixo() {
        System.out.print("ID do produto: ");
        int id = lerInt();

        System.out.print("Limite para stock baixo: ");
        int limite = lerInt();

        boolean baixo = stock.estaStockBaixo(id, limite);
        if (baixo) {
            System.out.println("Produto está com stock baixo.");
        } else {
            System.out.println("Produto não está com stock baixo.");
        }
    }

    private static void encontrarProdutoMaisVendido() {
        Product p = stock.encontrarProdutoMaisVendido();
        if (p != null) {
            System.out.println("Produto mais vendido:");
            System.out.println(p);
        } else {
            System.out.println("Nenhum produto no stock.");
        }
    }

    private static void gerarRelatorioProdutosEmFalta() {
        LinkedList<Product> emFalta = stock.gerarRelatorioProdutosEmFalta();
        if (emFalta.isEmpty()) {
            System.out.println("Não há produtos em falta.");
            return;
        }
        System.out.println("Produtos em falta:");
        for (Product p : emFalta) {
            System.out.println(p);
        }
    }

    private static void transferirStockEntreStocks() {
        System.out.print("ID do produto a transferir: ");
        int id = lerInt();

        System.out.print("Quantidade a transferir: ");
        int quantidade = lerInt();

        // Criar um segundo stock para simular a transferência
        StockControl stockDestino = new StockControl();

        Product pOrigem = stock.pesquisarProdutoPorId(id);
        if (pOrigem == null) {
            System.out.println("Produto não encontrado no stock de origem.");
            return;
        }

        boolean sucesso = StockControl.transferirStock(pOrigem, stockDestino, quantidade);
        if (sucesso) {
            System.out.println("Transferência realizada com sucesso.");
            System.out.println("Stock origem atualizado:");
            System.out.println(pOrigem);
            System.out.println("Stock destino:");
            for (Product p : stockDestino.listarProdutos()) {
                System.out.println(p);
            }
        } else {
            System.out.println("Quantidade insuficiente para transferência.");
        }
    }

    private static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor insira um número inteiro válido: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Por favor insira um número válido: ");
            }
        }
    }
}
