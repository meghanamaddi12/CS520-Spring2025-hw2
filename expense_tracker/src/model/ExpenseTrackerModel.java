package model;

import java.util.ArrayList;
import java.util.Collections; // CHANGED: Imported for immutability
import java.util.List;

/**
 * The ExpenseTrackerModel class maintains a list of transactions.
 * It provides methods to add, remove, and retrieve transactions.
 */

public class ExpenseTrackerModel {
  /**
   * Stores all transactions.
   * This list is private to ensure encapsulation.
   */
  private final List<Transaction> transactions; // CHANGED: Made private and final
  /**
   * Constructs a new ExpenseTrackerModel with an empty list of transactions.
   */
  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }
  /**
   * Adds a transaction to the list.
   *
   * @param t the transaction to add
   */
  public void addTransaction(Transaction t) {
    transactions.add(t);
  }
  /**
   * Removes a transaction from the list.
   *
   * @param t the transaction to remove
   */
  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }
  /**
   * Returns the list of all transactions.
   * to ensure immutability when accessed externally.
   *
   * @return a list of transactions
   */
  public List<Transaction> getTransactions() {
    return Collections.unmodifiableList(new ArrayList<>(transactions)); // CHANGED: Return immutable copy
  }
  /**
   * Calculates the total cost of all transactions.
   *
   * @return the sum of all transaction amounts
   */
  public double getTotalCost() {
    double total = 0.0;
    for (Transaction t : transactions) {
      total += t.getAmount();
    }
    return total;
  }


}