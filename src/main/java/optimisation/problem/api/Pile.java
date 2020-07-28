package optimisation.problem.api;

import java.util.Set;

public interface Pile {
    Float getMaximumProfit();

    Set<Integer> getNumbersOfItemsForMaximumProfit();
}
