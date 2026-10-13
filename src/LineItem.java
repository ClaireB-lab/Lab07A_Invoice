import javax.swing.*;
import java.awt.*;

/// Claire Battelle
/// October 12, 2026
/// calculates the total price
/// stores a reference to a product
class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getUnitPrice() * quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}