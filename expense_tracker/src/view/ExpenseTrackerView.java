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

  // NEW: Filter fields and buttons
  /**
   * Search field for filtering by amount.
   */
  private JTextField amountSearchField;
  /**
   * Search field for filtering by amount.
   */
  private JTextField categorySearchField;
  /**
   * Button to trigger filtering by amount.
   */
  private JButton searchByAmountBtn;
  /**
   * Button to trigger filtering by category.
   */
  private JButton searchByCategoryBtn;

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

    // NEW: Initialize filtering fields and buttons
    amountSearchField = new JTextField(6);
    categorySearchField = new JTextField(6);
    searchByAmountBtn = new JButton("Search Amount");
    searchByCategoryBtn = new JButton("Search Category");

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);

    // NEW: Add filter components to panel
    inputPanel.add(new JLabel("Filter Amount:"));
    inputPanel.add(amountSearchField);
    inputPanel.add(searchByAmountBtn);
    inputPanel.add(new JLabel("Filter Category:"));
    inputPanel.add(categorySearchField);
    inputPanel.add(searchByCategoryBtn);
  
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
   * Safely parses the input, removing commas and handling invalid input gracefully.
   *
   * @return the parsed amount, or -1 if the input is not a valid number
   */
  public double getAmountField() {
    String rawInput = amountField.getText().replace(",", "").trim();
    try {
      return Double.parseDouble(rawInput);
    } catch (NumberFormatException e) {
      return -1; // Indicates invalid input
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
  // NEW: Getters for filtering components

  /**
   * Returns the text field for amount search input.
   * @return JTextField for entering amount filter
   */
  public JTextField getAmountSearchField() {
    return amountSearchField;
  }
  /**
   * Returns the text field for category search input.
   * @return JTextField for entering category filter
   */
  public JTextField getCategorySearchField() {
    return categorySearchField;
  }
  /**
   * Returns the button used to search by amount.
   * @return JButton that triggers amount-based filtering
   */
  public JButton getSearchByAmountBtn() {
    return searchByAmountBtn;
  }
  /**
   * Returns the button used to search by category.
   * @return JButton that triggers category-based filtering
   */
  public JButton getSearchByCategoryBtn() {
    return searchByCategoryBtn;
  }
}
