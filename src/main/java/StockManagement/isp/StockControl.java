package StockManagement.isp;

import java.util.LinkedList;

public class StockControl {
    private LinkedList<Product> produtos = new LinkedList<>();

    // Inserir novo produto
    public void inserirProduto(String name, double unitPrice, int quantity) {
        Product novoProduto = new Product(name, unitPrice, quantity);
        produtos.add(novoProduto);
    }

    // Pesquisar produto por ID
    public Product pesquisarProdutoPorId(int id) {
        for (Product p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Atualizar produto
    public boolean atualizarProduto(int id, String newName, double newUnitPrice, int newQuantity) {
        Product p = pesquisarProdutoPorId(id);
        if (p != null) {
            p.setName(newName);
            p.setUnitPrice(newUnitPrice);
            p.setQuantity(newQuantity);
            return true;
        }
        return false;
    }

    // Listar todos os produtos
    public LinkedList<Product> listarProdutos() {
        return produtos;
    }

    // Registar venda
    public boolean registarVenda(Product produto, int quantidade) {
        return produto.registerSale(quantidade);
    }

    // Remover produto por ID
    public boolean removerProduto(int id) {
        Product p = pesquisarProdutoPorId(id);
        if (p != null) {
            produtos.remove(p);
            return true;
        }
        return false;
    }

    // Calcular valor total do stock
    public double calcularValorTotalStock() {
        double total = 0;
        for (Product p : produtos) {
            total += p.getUnitPrice() * p.getQuantity();
        }
        return total;
    }

    // Verificar se produto está em falta (stock zero)
    public boolean estaEmFalta(int id) {
        Product p = pesquisarProdutoPorId(id);
        if (p != null) {
            return p.getQuantity() == 0;
        }
        return false;
    }

    // Encontrar produto mais caro
    public Product encontrarProdutoMaisCaro() {
        if (produtos.isEmpty()) return null;

        Product maisCaro = produtos.get(0);
        for (Product p : produtos) {
            if (p.getUnitPrice() > maisCaro.getUnitPrice()) {
                maisCaro = p;
            }
        }
        return maisCaro;
    }

    // Verificar se produto está com stock baixo (abaixo de limite)
    public boolean estaStockBaixo(int id, int limite) {
        Product p = pesquisarProdutoPorId(id);
        if (p != null) {
            return p.getQuantity() < limite;
        }
        return false;
    }

    // Encontrar produto mais vendido
    public Product encontrarProdutoMaisVendido() {
        if (produtos.isEmpty()) return null;

        Product maisVendido = produtos.get(0);
        for (Product p : produtos) {
            if (p.getSales() > maisVendido.getSales()) {
                maisVendido = p;
            }
        }
        return maisVendido;
    }

    // Gerar relatório de produtos em falta (stock zero)
    public LinkedList<Product> gerarRelatorioProdutosEmFalta() {
        LinkedList<Product> emFalta = new LinkedList<>();
        for (Product p : produtos) {
            if (p.getQuantity() == 0) {
                emFalta.add(p);
            }
        }
        return emFalta;
    }

    // Transferir stock de um produto entre dois stocks diferentes
    // ainda vou mexer
    public static boolean transferirStock(Product produtoOrigem, StockControl stockDestino, int quantidade) {
        if (produtoOrigem.getQuantity() < quantidade) {
            return false;
        }
        produtoOrigem.setQuantity(produtoOrigem.getQuantity() - quantidade);

        // Tenta encontrar o mesmo produto no stockDestino
        Product produtoDestino = stockDestino.pesquisarProdutoPorId(produtoOrigem.getId());

        if (produtoDestino == null) {
            // Se não existir, cria um novo produto com os mesmos dados e quantidade transferida
            stockDestino.inserirProduto(produtoOrigem.getName(), produtoOrigem.getUnitPrice(), quantidade);
        } else {
            // Se existir, atualiza quantidade somando
            produtoDestino.setQuantity(produtoDestino.getQuantity() + quantidade);
        }
        return true;
    }
}
