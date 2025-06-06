package StockManagement.isp;

public class Product {
    private static int idCount = 1;
    private int id;
    private String name;
    private double unitPrice;
    private int quantity;
    private int sales;  // total de vendas

    public Product(String name, double unitPrice, int quantity) {
        this.id = idCount++;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.sales = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSales() {
        return sales;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public boolean registerSale(int quantitySold) {
        if (quantitySold <= quantity) {
            this.quantity -= quantitySold;
            this.sales += quantitySold;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Preço: %.2f | Stock: %d | Vendas: %d",
                id, name, unitPrice, quantity, sales);
    }
}
