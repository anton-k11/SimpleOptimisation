package optimisation.problem.api;

import lombok.Getter;

import java.util.*;


public class StackOfPiles implements Pile {
    @Getter
    private final List<Pile> piles;
    private final Float maximumProfit;


    public StackOfPiles(List<Pile> piles) {
        this.piles = piles;
        maximumProfit = calculateMaximumProfit();
    }

    public StackOfPiles(Pile ... piles) {
        this(Arrays.asList(piles));
    }

    @Override
    public Float getMaximumProfit() {
        return maximumProfit;
    }

    @Override
    public Set<Integer> getNumbersOfItemsForMaximumProfit() {
        Set<Integer> result = new HashSet<>();

        for (Pile currentPile : piles) {
            result = sum(currentPile, result);
        }
        return result;
    }

    private Float calculateMaximumProfit() {
        return (float) piles.stream()
            .map(Pile::getMaximumProfit)
            .mapToDouble(Float::floatValue)
            .sum();
    }

    private Set<Integer> sum(Pile pile, Set<Integer> numbersOfItemsForMaximumProfit) {
        Set<Integer> numsFromPile = pile.getNumbersOfItemsForMaximumProfit();
        Set<Integer> result = new HashSet<>();

        if (numbersOfItemsForMaximumProfit.isEmpty()) {
            result.addAll(numsFromPile);
        } else {
            for (Integer i1: numbersOfItemsForMaximumProfit) {
                for (Integer i2: numsFromPile) {
                    result.add(i1 + i2);
                }
            }
        }
        return result;
    }
}
