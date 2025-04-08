package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * Represents a single transaction with an amount, category, and timestamp.
 */
public final class Transaction {// CHANGED: made class final

  /** Amount of the transaction */
  private final double amount; // CHANGED: private + final
  /** Category of the transaction (e.g., food, travel) */
  public final String category; // CHANGED: private + final
  /** Timestamp when the transaction was created */
  public final String timestamp; // CHANGED: private + final

  /**
   * Constructs a Transaction with a specified amount and category.
   *
   * @param amount   the amount of the transaction
   * @param category the category of the transaction
   */
  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  /**
   * Gets the transaction amount.
   *
   * @return the amount
   */
  public double getAmount() {
    return amount;
  }
  /**
   * Gets the transaction category.
   *
   * @return the category
   */
  public String getCategory() {
    return category;
  }
  /**
   * Gets the timestamp of the transaction.
   *
   * @return the timestamp as a String
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Generates a timestamp in the format dd-MM-yyyy HH:mm.
   *
   * @return formatted timestamp
   */
  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    return sdf.format(new Date());
  }

}