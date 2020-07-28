package optimisation.problem;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import optimisation.problem.api.Pile;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class SinglePile implements Pile {
    // TODO: put in properties
    private static Float ITEM_SELLING_PRICE = 10f;

    @Getter
    private final Float [] pile;
    private final Float maximumProfit;
    private final LinkedMultiValueMap<Float, Integer> maxProfitPerItem;


    public SinglePile(Float [] _pile) {
        this.pile = _pile;
        maxProfitPerItem = new LinkedMultiValueMap<>();
        maximumProfit = calculateMaximumProfit();
    }


    @Override
    public Float getMaximumProfit() {
        return maximumProfit;
    }

    @Override
    public Set<Integer> getNumbersOfItemsForMaximumProfit() {
        List<List<Integer>> items = maxProfitPerItem.keySet().stream()
            .filter(getMaximumProfit()::equals)
            .map(maxProfitPerItem::get)
            .collect(Collectors.toCollection(ArrayList::new));

        HashSet<Integer> result = new HashSet<>();
        items.forEach( i-> result.addAll(i));

        return result;
    }

    private float calculateMaximumProfit() {
        float maxProfitTemp = 0;

        for (int i = 0; i < pile.length; i++) {
            maxProfitTemp += ITEM_SELLING_PRICE - pile[i];
            maxProfitPerItem.add(maxProfitTemp, i + 1);
            log.debug("{} profit ->  items {}", maxProfitTemp, i + 1);
        }

        return maxProfitPerItem.keySet().stream()
            .max(Float::compareTo).get();


    }


}
