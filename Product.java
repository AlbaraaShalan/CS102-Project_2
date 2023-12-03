public class Product implements Discountable {
    private String name;
    private double price;
    private int inventoryLevel;
    private double discountRate;

    public Product(String name, double price, int inventoryLevel, double discountRate) {
        this.name = name;
        this.price = price;
        this.inventoryLevel = inventoryLevel;
        this.discountRate = discountRate;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getInventoryLevel() {
        return this.inventoryLevel;
    }

    public double getDiscountRate() {
        return this.discountRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInventoryLevel(int inventoryLevel) {
        this.inventoryLevel = inventoryLevel;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public double calculateDiscount() {
        // Implement discount calculation logic
        return 0;
    }
}
