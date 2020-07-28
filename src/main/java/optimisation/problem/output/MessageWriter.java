package optimisation.problem.output;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class MessageWriter {
    private final String utf8 = StandardCharsets.UTF_8.name();

    private final OutputStream outputStream;


    public void writeMessages(int stacksOfPilesNumber, float maximumProfit,
                              Set<Integer> numbersOfItemsForMaximumProfit) throws IOException {

        outputStream.write(("Stacks of piles " + stacksOfPilesNumber).getBytes(utf8));
        writeNewLine();
        outputStream.write(getMaximumProfitMessage(maximumProfit).getBytes(utf8));
        writeNewLine();
        outputStream.write(getNumberOfItemsMessage(numbersOfItemsForMaximumProfit).getBytes(utf8));
        writeNewLine();
    }

    public String getMaximumProfitMessage(float profit) {
        String profitStr = new DecimalFormat("###.##").format(profit);
        return "Maximum profit is " + profitStr + ".";
    }

    public String getNumberOfItemsMessage(Set<Integer> numbersOfItemsForMaximumProfit) {
        StringBuilder b = new StringBuilder();
        numbersOfItemsForMaximumProfit
            .stream()
            .sorted()
            .forEach(f -> {
                b.append(" ");
                b.append(f);
                }
            );

        return "Number of fluts to buy:" + b.toString();

    }

    private void writeNewLine() throws IOException {
        outputStream.write(System.lineSeparator().getBytes(utf8));
    }
}
