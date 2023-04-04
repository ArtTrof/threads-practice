package asynchronous.test_wit_files;

import java.io.*;

public class FileUtils {
    public static void getStatistics(String fromFilePath, String toFilePath) {
        String[] strings = readFromFile(fromFilePath);
        String report = createReport(strings);
        writeToFile(report, toFilePath);
    }

    private static String[] readFromFile(String fromFilePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(fromFilePath))) {
            var val = bf.read();
            while (val != -1) {
                stringBuilder.append((char) val);
                val = bf.read();
            }
            String wordsWithoutEnter = stringBuilder.toString().replaceAll("\n", ",")
                    .replaceAll("\r", "");
            return wordsWithoutEnter.split(",");
        } catch (IOException e) {
            throw new RuntimeException("Cant read file " + e);
        }
    }

    private static String createReport(String[] splitArray) {
        var supply = 0;
        var buy = 0;
        for (int i = 0; i < splitArray.length; i = i + 2) {
            if (splitArray[i].equals("supply")) {
                supply += Integer.parseInt(splitArray[i + 1]);
            } else if (splitArray[i].equals("buy")) {
                buy += Integer.parseInt(splitArray[i + 1]);
            }
        }
        return "supply," + supply + "\n" + "buy," + buy + "\nresult:" + (supply - buy);
    }

    private static void writeToFile(String resultReport, String toFilePath) {
        File file = new File(toFilePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(resultReport);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
