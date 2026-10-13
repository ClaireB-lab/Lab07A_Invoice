import javax.swing.*;

/// Claire Battelle
/// October 12, 2026
/// Runs the invoice frame
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InvoiceFrame().setVisible(true);
        });
    }
}