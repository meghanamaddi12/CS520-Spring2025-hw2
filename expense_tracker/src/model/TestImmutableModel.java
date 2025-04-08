package model;

import model.ExpenseTrackerModel;
import model.Transaction;

import java.util.List;

public class TestImmutableModel {
    public static void main(String[] args) {
        ExpenseTrackerModel model = new ExpenseTrackerModel();
        model.addTransaction(new Transaction(100, "food"));

        List<Transaction> externalList = model.getTransactions();
        System.out.println("Initial size: " + externalList.size());

        // Try modifying the returned list
        try {
            externalList.add(new Transaction(50, "travel"));
            System.out.println("❌ Failed: List is still mutable!");
        } catch (UnsupportedOperationException e) {
            System.out.println("✅ Passed: List is immutable.");
        }
    }
}
