import java.io.*;
import java.util.*;

public class Utils {
    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public ArrayList<ElectionResult> parse2016PresidentialResults(String file){
        String[] data = file.split("\n");
        ArrayList<ElectionResult> results = new ArrayList<>();
        for (String line : data){
            results.add(new ElectionResult(line));
        }
        return results;
    }

}
