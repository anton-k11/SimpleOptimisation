package optimisation.problem;

import lombok.extern.slf4j.Slf4j;
import optimisation.problem.api.StackOfPiles;
import optimisation.problem.input.InputParser;
import optimisation.problem.output.MessageWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class CommandLiner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream;
        if (0 == args.length) {
            inputStream = System.in;
            log.info("Input is System.in");
        } else {
            inputStream = new FileInputStream(args[0]);
            log.info("Input is " + args[0]);
        }

        InputParser inputParser = new InputParser(inputStream);


        MessageWriter messageWriter = new MessageWriter(System.out);

        List<StackOfPiles> stackOfPilesList = inputParser.getStackOfPiles();
        for (int i = 0; i < stackOfPilesList.size(); i++) {
            StackOfPiles stackOfPiles = stackOfPilesList.get(i);
            Float maximumProfit =  stackOfPiles.getMaximumProfit();
            Set<Integer> numbersOfItemsForMaximumProfit = stackOfPiles.getNumbersOfItemsForMaximumProfit();

            messageWriter.writeMessages(i+1, maximumProfit, numbersOfItemsForMaximumProfit);
        }

    }

}