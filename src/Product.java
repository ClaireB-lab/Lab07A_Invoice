/// Claire Battelle
/// October 12, 2026
/// stores the product name and unit price
public class Product {
    private String name;
    private double unitPrice;

    public Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }
}