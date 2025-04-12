package filter;

import model.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Filters transactions based on a specified amount threshold.
 */
public class AmountFilter implements TransactionFilter {

    private final double maxAmount;

    /**
     * Creates a filter that allows transactions up to the given maximum amount.
     *
     * @param maxAmount the highest amount allowed
     */
    public AmountFilter(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() == maxAmount) {
                filtered.add(t);
            }
        }
        return filtered;
    }
}

