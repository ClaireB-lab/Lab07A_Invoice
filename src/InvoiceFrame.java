import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/// Claire Battelle
/// October 12, 2026
/// Creates the frame for the application
class InvoiceFrame extends JFrame {
    private JTextField txtTitle, txtAddress, txtProductName, txtPrice, txtQty;
    private JTextArea txtAreaOutput;
    private Invoice invoice;

    public InvoiceFrame() {
        setTitle("Lab 07A - Multi-Item Invoice Application");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input Panel with form fields
        JPanel panelInput = new JPanel(new GridLayout(7, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("Invoice Title:"));
        txtTitle = new JTextField("Software Engineering Services");
        panelInput.add(txtTitle);

        panelInput.add(new JLabel("Customer Address:"));
        txtAddress = new JTextField("John Doe, 123 Main St, Cincinnati OH");
        panelInput.add(txtAddress);

        panelInput.add(new JLabel("Product Name:"));
        txtProductName = new JTextField();
        panelInput.add(txtProductName);

        panelInput.add(new JLabel("Unit Price ($):"));
        txtPrice = new JTextField();
        panelInput.add(txtPrice);

        panelInput.add(new JLabel("Quantity:"));
        txtQty = new JTextField();
        panelInput.add(txtQty);

        JButton btnAddLineItem = new JButton("Add Line Item");
        JButton btnNewInvoice = new JButton("Reset / New Invoice");

        panelInput.add(btnAddLineItem);
        panelInput.add(btnNewInvoice);

        add(panelInput, BorderLayout.NORTH);

        // Output Area (JTextArea inside a scroll pane)
        txtAreaOutput = new JTextArea();
        txtAreaOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaOutput.setEditable(false);
        add(new JScrollPane(txtAreaOutput), BorderLayout.CENTER);

        // Initialize the invoice object
        startNewInvoice();

        // Action Listener for Adding a Line Item
        btnAddLineItem.addActionListener((ActionEvent e) -> {
            try {
                String prodName = txtProductName.getText().trim();
                if (prodName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a product name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double price = Double.parseDouble(txtPrice.getText());
                int qty = Integer.parseInt(txtQty.getText());

                // Create product and line item, then add to the current invoice
                Product p = new Product(prodName, price);
                invoice.addLineItem(new LineItem(p, qty));

                // Update display and clear product fields for the next item
                txtAreaOutput.setText(invoice.formatInvoice());
                txtProductName.setText("");
                txtPrice.setText("");
                txtQty.setText("");

                // Lock title and address after the first item is added so they aren't accidentally changed mid-invoice
                txtTitle.setEnabled(false);
                txtAddress.setEnabled(false);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for price and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action Listener to Reset / Start a New Invoice
        btnNewInvoice.addActionListener((ActionEvent e) -> {
            startNewInvoice();
            txtAreaOutput.setText("");
            txtTitle.setEnabled(true);
            txtAddress.setEnabled(true);
            txtProductName.setText("");
            txtPrice.setText("");
            txtQty.setText("");
        });
    }

    private void startNewInvoice() {
        invoice = new Invoice(txtTitle != null ? txtTitle.getText() : "Software Engineering Services",
                txtAddress != null ? txtAddress.getText() : "Customer Address");
    }
}