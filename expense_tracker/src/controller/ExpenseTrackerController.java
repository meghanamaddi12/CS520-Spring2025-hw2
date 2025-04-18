package controller;

import view.ExpenseTrackerView;

import java.util.List;

import filter.TransactionFilter; // NEW: Import strategy interface



import model.ExpenseTrackerModel;
import model.Transaction;



/**
 * Controller class for managing interactions between the model and view.
 * Handles input validation and updates the view with transaction data.
 */
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  /**
   * Constructs the controller with the given model and view.
   *
   * @param model the model to manage transactions
   * @param view the view to interact with the user
   */

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  /**
   * Refreshes the view with the latest list of transactions from the model.
   */
  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  /**
   * Adds a new transaction if input is valid.
   *
   * @param amount The amount of the transaction.
   * @param category The category of the transaction.
   * @return true if transaction was added successfully, false otherwise.
   */

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(String.valueOf(amount))) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    refresh();
    return true;
  }
  /**
   * Applies a given filter strategy and updates the view with filtered transactions.
   *
   * @param filter The filter strategy to apply.
   */
  public void applyFilter(TransactionFilter filter) {
    List<Transaction> filtered = filter.filter(model.getTransactions());
    view.refreshTable(filtered);
  }
}