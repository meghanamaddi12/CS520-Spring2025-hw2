package controller;

import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

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

    @Before
    public void setupTestEnvironment() {
        model = new ExpenseTrackerModel();
        view = new ExpenseTrackerView();
        controller = new ExpenseTrackerController(model, view);
    }

    @Test
    public void testAddTransactionSuccessfully() {
        boolean result = controller.addTransaction(50.00, "food");
        System.out.println("Added Transaction:");
        Transaction added = model.getTransactions().get(0);
        System.out.println("Amount: " + added.getAmount() + ", Category: " + added.getCategory());
        System.out.println("Total cost: " + model.getTotalCost());

        assertTrue("Add operation should return true", result);
        assertEquals("Should have exactly one transaction stored", 1, model.getTransactions().size());
        assertEquals("Amount should match input", 50.00, added.getAmount(), 0.001);
        assertEquals("Category should match input", "food", added.getCategory());
        assertEquals("Total cost should update correctly", 50.00, model.getTotalCost(), 0.001);
    }

    @Test
    public void testRejectsInvalidInputs() {
        boolean addedInvalidAmount = controller.addTransaction(-20.0, "food");
        System.out.println("Attempt to add negative amount: " + addedInvalidAmount);
        assertFalse("Should reject transaction with negative amount", addedInvalidAmount);

        boolean addedInvalidCategory = controller.addTransaction(25.0, "Meghana");
        System.out.println("Attempt to add invalid category: " + addedInvalidCategory);
        System.out.println("Transactions after invalid attempts: " + model.getTransactions().size());

        assertFalse("Should reject transaction with unknown category", addedInvalidCategory);
        assertEquals("Still no transaction should be stored", 0, model.getTransactions().size());
    }

    @Test
    public void testFilterByAmount100Only() {
        controller.addTransaction(100.0, "food");
        controller.addTransaction(50.0, "bills");
        controller.addTransaction(100.0, "travel");

        TransactionFilter filter = new AmountFilter(100.0);
        List<Transaction> result = filter.filter(model.getTransactions());

        System.out.println("Filtered result count: " + result.size());
        for (Transaction t : result) {
            System.out.println("Amount: " + t.getAmount() + ", Category: " + t.getCategory());
        }

        assertEquals("Should return only transactions with amount = 100", 2, result.size());
        assertTrue("All filtered amounts should be 100",
                result.stream().allMatch(t -> t.getAmount() == 100.0));
    }

    @Test
    public void testFilterByCategory() {
        controller.addTransaction(40.0, "food");
        controller.addTransaction(60.0, "travel");
        controller.addTransaction(30.0, "food");

        TransactionFilter filter = new CategoryFilter("food");
        List<Transaction> result = filter.filter(model.getTransactions());

        System.out.println("Filtered result count: " + result.size());
        for (Transaction t : result) {
            System.out.println("Amount: " + t.getAmount() + ", Category: " + t.getCategory());
        }

        assertEquals("Should return only transactions with category 'food'", 2, result.size());
        assertTrue("All filtered categories should be 'food'",
                result.stream().allMatch(t -> t.getCategory().equalsIgnoreCase("food")));
    }
}
