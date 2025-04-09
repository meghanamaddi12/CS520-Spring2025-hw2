package filter;

import model.Transaction;
import java.util.List;

/**
 * Strategy interface for filtering transactions.
 * Different filtering rules will implement this interface.
 */
public interface TransactionFilter {

    /**
     * Applies a filter to the given list of transactions.
     *
     * @param transactions the list of transactions to filter
     * @return a new list containing only the transactions that match the filter condition
     */
    List<Transaction> filter(List<Transaction> transactions);
}


