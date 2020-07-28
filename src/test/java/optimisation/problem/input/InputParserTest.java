package optimisation.problem.input;

import optimisation.problem.SinglePile;
import optimisation.problem.TestConstants;
import optimisation.problem.api.StackOfPiles;
import optimisation.problem.api.Pile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class InputParserTest {


    private List<StackOfPiles> stackOfPilesList;

    @BeforeEach
    public void init() throws NumberFormatException, IOException {
        InputStream inputStream = new ByteArrayInputStream(TestConstants.inputString.getBytes());

        InputParser inputParser = new InputParser(inputStream);
        stackOfPilesList = inputParser.getStackOfPiles();
        assertEquals(2, stackOfPilesList.size());

    }

    @Test
    public void givenInputRead1Pile() {
       List<Pile> pileList = stackOfPilesList.get(0).getPiles();
       assertEquals(1, pileList.size());

       assertArrayEquals(TestConstants.pileWith6, ((SinglePile)pileList.get(0)).getPile());


    }

    @Test
    public void givenInputRead2Piles() {
        List<Pile> singlePileList = stackOfPilesList.get(1).getPiles();
        assertEquals(2, singlePileList.size());

        Float [] pile0 = ((SinglePile)singlePileList.get(0)).getPile();
        Float [] pile1 = ((SinglePile)singlePileList.get(1)).getPile();

        assertArrayEquals(TestConstants.pileWith5, pile0);
        assertArrayEquals(TestConstants.pileWith9, pile1);
    }

    //@Test
    // end2end
}
