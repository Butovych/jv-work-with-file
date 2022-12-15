package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_SUPPLY_LENGTH = 7;
    private static final int INDEX_OF_BUY_LENGTH = 4;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String setStatistic = readFile(fromFileName);
        String getStat = setStatistic(setStatistic);
        writeStatistic(toFileName, forWright);
    }
    private static String readFile (String fromFileName) {
        StringBuilder firstBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                firstBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }
        return firstBuilder.toString();
    }

    private  static String setStatistic (String value) {
        int countSupply = 0;
        int countBuy = 0;
        String[] amounts = value.split(" ");
        for (String amount : amounts) {
            if (amount.contains("supply")) {
                countSupply += Integer.parseInt(amount.substring(INDEX_OF_SUPPLY_LENGTH,
                        amount.length()));
            } else {
                countBuy += Integer.parseInt(amount.substring(INDEX_OF_BUY_LENGTH,
                        amount.length()));
            }
        }
        String forWright = "supply," + countSupply + LINE_SEPARATOR
                + "buy," + countBuy + LINE_SEPARATOR
                + "result," + (countSupply - countBuy) + LINE_SEPARATOR;
        return forWright;
    }

    private static void writeStatistic (String toFileName, String forWright) {
        File fileForWright = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileForWright))) {
            bufferedWriter.write(forWright);
        } catch (IOException e) {
            throw new RuntimeException("We can't write this file", e);
        }
    }
}
