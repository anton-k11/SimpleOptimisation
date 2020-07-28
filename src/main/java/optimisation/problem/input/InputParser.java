package optimisation.problem.input;

import lombok.Getter;
import optimisation.problem.SinglePile;
import optimisation.problem.api.StackOfPiles;
import optimisation.problem.api.Pile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InputParser {
    Scanner scanner;

    @Getter
    List<StackOfPiles> stackOfPiles = new ArrayList<>();

    public InputParser(InputStream input) throws IOException {
        this.scanner = new Scanner(input);
        parseStream();
    }

    private void parseStream() throws IOException {
        while (scanner.hasNext()) {
            String command = scanner.nextLine().trim();
            if (isInteger(command)) {
                if (0 == Integer.parseInt(command))
                    break;
                stackOfPiles.add(new StackOfPiles(createPiles(command)));
            } else if (command.isEmpty() || command.isBlank()) {
                continue;
            } else {
                throw new IOException("Can not understand line: " + command);
            }
        }
    }

    private boolean isInteger(String command) {
        try {
            Integer.parseInt(command);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private List<Pile> createPiles(String command) throws NumberFormatException, IOException {
        List<Pile> groupOfPiles = new ArrayList<>();
        int numberOfPiles = Integer.parseInt(command);

        for (int i = 0; scanner.hasNext() && i < numberOfPiles; i++) {
            String pileString = scanner.nextLine();
            Scanner pileScanner = new Scanner(pileString);
            ArrayList<Float> pileElements = new ArrayList<>();

            int pileLength = pileScanner.nextInt();

            for (int j = 0; pileScanner.hasNext() && j < pileLength; j++) {
                pileElements.add(pileScanner.nextFloat());
            }

            if (pileElements.size() != pileLength) throw new IOException("Pile " + pileString
                + " length differs from size " + pileLength + " provided");

            groupOfPiles.add(new SinglePile(pileElements.toArray(Float[]::new)));
        }
        return groupOfPiles;

    }
}
