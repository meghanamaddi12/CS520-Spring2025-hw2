import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

// NEW: Import filter strategy classes
import filter.AmountFilter;
import filter.CategoryFilter;
import filter.TransactionFilter;

/**
 * The main entry point of the Expense Tracker application.
 * Initialize s the MVC components and sets up event handling.
 */
public class ExpenseTrackerApp {
  /**
   * Main method to launch the application.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();

      if (!InputValidation.isValidAmount(String.valueOf(amount)) &&
              !InputValidation.isValidCategory(category)) {
        JOptionPane.showMessageDialog(view, "Invalid amount and category entered.\n\n" + "Amount must be a number > 0 and < 1000.\n" + "Valid categories: food, travel, bills, entertainment, other.");
        view.toFront();
      } else if (!InputValidation.isValidAmount(String.valueOf(amount))) {
        JOptionPane.showMessageDialog(view, "Invalid amount entered. Please enter a number between 0 and 1000.");
        view.toFront();
      } else if (!InputValidation.isValidCategory(category)) {
        JOptionPane.showMessageDialog(view, "Invalid category entered. Valid: food, travel, bills, entertainment, other.");
        view.toFront();
      } else {
        boolean added = controller.addTransaction(amount, category); // Call controller to add transaction
        if (added) {
          view.refreshTable(model.getTransactions());
        }
      }
    });

    // NEW: Handle filtering by amount
    view.getSearchByAmountBtn().addActionListener(e -> {
      String inputAmount = view.getAmountSearchField().getText().replace(",", "").trim();

      if (!InputValidation.isValidAmount(inputAmount)) {
        JOptionPane.showMessageDialog(view, "Invalid amount. Please enter a number between 0 and 1000.");
        return;
      }

      try {
        double amount = Double.parseDouble(inputAmount);
        TransactionFilter filter = new AmountFilter(amount);
        controller.applyFilter(filter);
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(view, "Amount must be a valid number.");
      }
    });

    // NEW: Handle filtering by category
    view.getSearchByCategoryBtn().addActionListener(e -> {
      String inputCategory = view.getCategorySearchField().getText().trim();

      if (!InputValidation.isValidCategory(inputCategory)) {
        JOptionPane.showMessageDialog(view, "Invalid category. Valid categories: food, travel, bills, entertainment, other.");
        return;
      }

      TransactionFilter filter = new CategoryFilter(inputCategory);
      controller.applyFilter(filter);
    });

  }

}