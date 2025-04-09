package filter;

import model.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Filters transactions by a specific category.
 */
public class CategoryFilter implements TransactionFilter {

    private final String targetCategory;

    /**
     * Creates a filter that matches a specific transaction category.
     *
     * @param category the category to match
     */
    public CategoryFilter(String category) {
        this.targetCategory = category.toLowerCase();
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getCategory().equalsIgnoreCase(targetCategory)) {
                filtered.add(t);
            }
        }
        return filtered;
    }
}

