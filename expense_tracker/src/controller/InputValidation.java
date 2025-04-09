package controller;

import java.util.Arrays;

/**
 * A utility class for validating user inputs in the Expense Tracker application.
 * Ensures the amount is within an acceptable range and the category is valid.
 */

public class InputValidation {
  /**
   * Default constructor.
   * This class is not intended to be instantiated since it only provides static utility methods.
   */
  public InputValidation() {
    // Not intended for instantiation
  }

  /**
   * NEW: Checks if a string represents a valid amount.
   * Accepts strings like "100.0" and verifies they are numeric and within range.
   *
   * @param amountStr the user-entered amount as a string
   * @return true if it's a valid number between 0 and 1000 (exclusive), false otherwise
   */
  public static boolean isValidAmount(String amountStr) {
    try {
      double amount = Double.parseDouble(amountStr);
      return amount > 0 && amount < 1000;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Validates that the category is not null, non-empty, alphabetic,
   * and one of the predefined valid categories.
   *
   * @param category the transaction category
   * @return true if the category is valid
   */
  public static boolean isValidCategory(String category) {

    if(category == null) {
      return false; 
    }
  
    if(category.trim().isEmpty()) {
      return false;
    }

    if(!category.matches("[a-zA-Z]+")) {
      return false;
    }

    String[] validWords = {"food", "travel", "bills", "entertainment", "other"};

    if(!Arrays.asList(validWords).contains(category.toLowerCase())) {
      // invalid word  
      return false;
    }
  
    return true;
  
  }

}