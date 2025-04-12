package controller;

import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import filter.TransactionFilter;
import filter.AmountFilter;
import filter.CategoryFilter;


/**
 * Unit tests for the ExpenseTrackerController.
 * This class ensures that transactions and filters behave as expected.
 */
public class ExpenseTrackerControllerTest {

    private ExpenseTrackerModel model;
    private ExpenseTrackerView view;
    private ExpenseTrackerController controller;

    /**
     * Runs before each test — sets up a clean state with a new model, view, and controller.
     */
    @BeforeEach
    public void setupTestEnvironment() {
        model = new ExpenseTrackerModel();
        view = new ExpenseTrackerView();  // Although unused in some cases, initialized for consistency
        controller = new ExpenseTrackerController(model, view);
    }
    // Test cases are added here, one by one
    @Test
    public void testAddTransactionSuccessfully() {
        // Act: Try adding a valid transaction
        boolean result = controller.addTransaction(50.00, "food");

        // Confirm the method returned true (successful add)
        assertTrue(result, "Add operation should return true");

        // Verify the model now contains exactly one transaction
        List<Transaction> transactions = model.getTransactions();
        assertEquals(1, transactions.size(), "Should have exactly one transaction stored");

        // Check the values of the added transaction
        Transaction added = transactions.get(0);
        System.out.println("Added Transaction:");
        System.out.println("Amount: " + added.getAmount() + ", Category: " + added.getCategory());
        System.out.println("Total cost: " + model.getTotalCost());
        assertEquals(50.00, added.getAmount(), 0.001, "Amount should match input");
        assertEquals("food", added.getCategory(), "Category should match input");

        // Check that the total cost is updated correctly
        assertEquals(50.00, model.getTotalCost(), 0.001, "Total cost should update correctly");
    }
    @Test
    public void testRejectsInvalidInputs() {
        // Try adding a transaction with a negative amount
        boolean addedInvalidAmount = controller.addTransaction(-20.0, "food");
        System.out.println("Attempt to add negative amount: " + addedInvalidAmount);
        assertFalse(addedInvalidAmount, "Should reject transaction with negative amount");
        assertEquals(0, model.getTransactions().size(), "No transaction should be added");

        // Try adding a transaction with an invalid category
        boolean addedInvalidCategory = controller.addTransaction(25.0, "Meghana");
        System.out.println("Attempt to add invalid category: " + addedInvalidCategory);
        // Print how many transactions are present after the invalid attempts
        List<Transaction> current = model.getTransactions();
        System.out.println("Transactions after invalid attempts: " + current.size());
        assertFalse(addedInvalidCategory, "Should reject transaction with unknown category");
        assertEquals(0, model.getTransactions().size(), "Still no transaction should be stored");
    }
    @Test
    public void testFilterByAmount100Only() {
        // Add transactions with different amounts
        controller.addTransaction(100.0, "food");
        controller.addTransaction(50.0, "bills");
        controller.addTransaction(100.0, "travel");

        // Apply filter for amount = 100.0
        TransactionFilter filter = new AmountFilter(100.0);
        List<Transaction> result = filter.filter(model.getTransactions());

        // Print output
        System.out.println("Filtered result count: " + result.size());
        for (Transaction t : result) {
            System.out.println("Amount: " + t.getAmount() + ", Category: " + t.getCategory());
        }

        // Expect only the two matching transactions
        assertEquals(2, result.size(), "Should return only transactions with amount = 100");
        assertTrue(result.stream().allMatch(t -> t.getAmount() == 100.0), "All filtered amounts should be 100");
    }
    @Test
    public void testFilterByCategory() {
        // Add transactions with different categories
        controller.addTransaction(40.0, "food");
        controller.addTransaction(60.0, "travel");
        controller.addTransaction(30.0, "food");

        // Apply filter for category = "food"
        TransactionFilter filter = new filter.CategoryFilter("food");
        List<Transaction> result = filter.filter(model.getTransactions());

        System.out.println("Filtered result count: " + result.size());
        for (Transaction t : result) {
            System.out.println("Amount: " + t.getAmount() + ", Category: " + t.getCategory());
        }


        // Expect only the two "food" transactions
        assertEquals(2, result.size(), "Should return only transactions with category 'food'");
        assertTrue(result.stream().allMatch(t -> t.getCategory().equalsIgnoreCase("food")), "All filtered categories should be 'food'");
    }
}
