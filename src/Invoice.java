import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/// Claire Battelle
/// October 12, 2026
/// creates the format for the invoice receipt
class Invoice {
    private String title;
    private String customerAddress;
    private List<LineItem> items;

    public Invoice(String title, String customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        this.items = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        items.add(item);
    }

    public double getTotalDue() {
        double total = 0;
        for (LineItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String formatInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("           ").append(title).append("\n");
        sb.append("========================================\n\n");
        sb.append("Customer Address:\n").append(customerAddress).append("\n\n");
        sb.append("----------------------------------------\n");
        sb.append(String.format("%-15s %-6s %-8s %-8s\n", "Product", "Qty", "Price", "Total"));
        sb.append("----------------------------------------\n");

        for (LineItem item : items) {
            sb.append(String.format("%-15s %-6d $%-7.2f $%-7.2f\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getUnitPrice(),
                    item.getTotal()));
        }

        sb.append("----------------------------------------\n");
        sb.append(String.format("TOTAL AMOUNT DUE: $%.2f\n", getTotalDue()));
        sb.append("========================================\n");
        return sb.toString();
    }
}