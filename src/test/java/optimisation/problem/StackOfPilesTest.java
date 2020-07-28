package optimisation.problem;

import lombok.extern.slf4j.Slf4j;
import optimisation.problem.api.StackOfPiles;
import optimisation.problem.api.Pile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static optimisation.problem.TestConstants.pileWith5;
import static optimisation.problem.TestConstants.pileWith9;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class StackOfPilesTest {



    private static final Integer EXPECTED_NUMBER_OF_ITEMS_FOR_MAXIMUM_PROFIT [] = { 6, 7, 8, 9, 10, 12, 13 };
    private static final int EXPECTED_MAXIMUM_PROFIT = 40;

    private StackOfPiles stackOfPiles;

    @BeforeEach
    public void createSchuur() {
        Pile pile1 = new SinglePile(pileWith5);
        Pile pile2 = new SinglePile(pileWith9);
        stackOfPiles = new StackOfPiles(pile1, pile2);
    }

    @Test
    public void whenSchuurProfitIs40() {
        Float maxProfitComposition = stackOfPiles.getMaximumProfit();

        assertEquals(EXPECTED_MAXIMUM_PROFIT, maxProfitComposition);
    }

    @Test
    public void whenSchuurNumberOfItemsForMaximumProfitMatches() {
        Set<Integer> numberOfItemsFoxMaximumProfit = stackOfPiles.getNumbersOfItemsForMaximumProfit();

        assertArrayEquals(EXPECTED_NUMBER_OF_ITEMS_FOR_MAXIMUM_PROFIT, numberOfItemsFoxMaximumProfit.toArray());
    }
}
