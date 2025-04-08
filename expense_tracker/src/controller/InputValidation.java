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
   * Validates that the amount is within a valid range.
   *
   * @param amount the transaction amount
   * @return true if amount is greater than 0 and less than or equal to 1000
   */
  public static boolean isValidAmount(double amount) {
    
    // Check range
    if(amount >1000) {
      return false;
    }
    if (amount < 0){
      return false;
    }
    if (amount == 0){
      return false;
    }
    return true;
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