import java.io.*;
import java.util.LinkedList;

public class TamilTokenizerMain {

    private static final String inputF = "op-10-19.si-ta.ta";
    private static final String outputF = inputF + ".tok";


    public static void main(String[] args) throws IOException {
      //  String[]  fileSet ={"let0-", "let1-", "let2-", "let3-", "let4-", "let5-","extract-"};
        String suffix = ".si-ta.ta";
        String tokSuffix =  ".tok";
        String date = "1-17";
        String fileIndex =args[0];
//        int fileIndex = Integer.parseInt(args[0]);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileIndex+suffix)));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileIndex + tokSuffix + suffix)));
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileSet[fileIndex]+date+suffix)));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileSet[fileIndex]+date+tokSuffix + suffix)));

        TamilTokenizer tamilTokenizer = new TamilTokenizer();

        String line = bufferedReader.readLine().trim();

        while (line != null) {

            LinkedList<String> tokenized = tamilTokenizer.splitWords(line);


            String str = "";
            for (String term : tokenized) {
                str = str + term + " ";
            }

            if (str != null)
                str = str.trim();

            String[] parts = str.split("\\s+");

            String lineToWrite = "";

            for (int i = 0; i < parts.length; i++) {
                if (i == parts.length - 1) {
                    lineToWrite += parts[i].trim();
                    break;
                }

                if (parts[i].equalsIgnoreCase("/") || parts[i].equalsIgnoreCase(".")) {
                    if (i > 0 && isNumeric(parts[i - 1])) {
                        lineToWrite = lineToWrite.trim();

                        if (i < parts.length - 1 && isNumeric(parts[i + 1]))
                            lineToWrite += parts[i];
                        else
                            lineToWrite += parts[i] + " ";
                    } else {
                        lineToWrite += parts[i] + " ";
                    }
                } else {
                    lineToWrite += parts[i] + " ";
                }

            }

            bufferedWriter.write(lineToWrite + "\n");


            line = bufferedReader.readLine();

            if (line != null)
                line = line.trim();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static boolean isNumeric(String str) {
        try {
            Integer integer = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}


