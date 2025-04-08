package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;
/**
 * The ExpenseTrackerView class builds the GUI using Java Swing components.
 * It allows the user to input expense data and view it in a table.
 */
public class ExpenseTrackerView extends JFrame {

  /** Table displaying transaction records */
  private JTable transactionsTable;
  /** Button to add a transaction */
  private JButton addTransactionBtn;
  /** Field for entering the transaction amount */
  private JFormattedTextField amountField;
  /** Field for entering the transaction category */
  private JTextField categoryField;
  /** Table model for updating data in the view */
  private DefaultTableModel model;

  /**
   * Constructs the ExpenseTrackerView GUI layout.
   */
  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  /**
   * Refreshes the table with the latest list of transactions.
   *
   * @param transactions the list of transactions to display
   */
  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }



  /**
   * Returns the button used to add a new transaction.
   *
   * @return the Add Transaction JButton
   */
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  /**
   * Returns the model backing the transactions table.
   *
   * @return the DefaultTableModel instance
   */
  public DefaultTableModel getTableModel() {
    return model;
  }

  /**
   * Returns the table that displays all transactions.
   *
   * @return the JTable used in the view
   */

  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  /**
   * Gets the amount entered by the user.
   * @return the entered amount as a double
   */

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }
  /**
   * Sets the formatted amount input field.
   *
   * @param amountField the JFormattedTextField for amount input
   */
  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  /**
   * Gets the entered category.
   * @return the category text
   */
  public String getCategoryField() {
    return categoryField.getText();
  }
  /**
   * Sets the category input field. (Setter primarily for testing or GUI updates.)
   *
   * @param categoryField the text field to set as the category input
   */
  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}
