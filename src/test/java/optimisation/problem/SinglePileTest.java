package optimisation.problem;

import lombok.extern.slf4j.Slf4j;
import optimisation.problem.api.Pile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class SinglePileTest {

    private Pile pile;

    @BeforeEach
    public void createPile() {
        pile = new SinglePile(TestConstants.pileWith6);

    }

    @Test
    public void whenSinglePileProfit8 () {
        float maxProfit = pile.getMaximumProfit();
        assertEquals(8, maxProfit);
    }

    @Test
    public void whenSinglePileNumberOfPiles4() {
        Set<Integer> items = pile.getNumbersOfItemsForMaximumProfit();
        assertArrayEquals(items.toArray(), new Integer[] {4});
    }
}
