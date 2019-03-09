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

//    OLD
//    public static ArrayList<ElectionResult> parse2016PresidentialResults(String file){
//        if (file.indexOf(",") == 0) {
//            file = file.substring(file.indexOf("\n") + 1); // skips the first line
//        }
//        String[] data = file.split("\n");
//        ArrayList<ElectionResult> results = new ArrayList<>();
//        for (String line : data){
//            formatData(line);
//            results.add(new ElectionResult(line));
//        }
//        return results;
//    }

    public static ArrayList<Election2016> parseElectionResults(String file){
        String[] data = file.split("\n");
        ArrayList<Election2016> results = new ArrayList<>();
        for (int i = 1; i < data.length; i++){
            String[] vals = data[i].split(",");
            double demVotes = Double.parseDouble(vals[1]);
            double gopVotes = Double.parseDouble(vals[2]);
            double totalVotes = Double.parseDouble(vals[3]);
            results.add(new Election2016(demVotes, gopVotes, totalVotes));
        }
        return results;
    }

    public static ArrayList<Employment2016> parseEmploymentData(String file){
        String[] data = file.split("\n");
        ArrayList<Employment2016> results = new ArrayList<>();
        for (int i = 8; i < data.length; i++){
            String[] vals = data[i].split(",");
            int lastIndex = data.length - 1;
            int totalLaborForce = Integer.parseInt(vals[lastIndex - 3]);
            int employedLaborForce = Integer.parseInt(vals[lastIndex - 2]);
            int unemployedLaborForce = Integer.parseInt(vals[lastIndex - 1]);
            double unemployedPercent = Double.parseDouble(vals[lastIndex]);
            results.add(new Employment2016(totalLaborForce, employedLaborForce, unemployedLaborForce, unemployedPercent));
        }
        return results;
    }

    public static ArrayList<Education2016> parseEducationData(String file){
        String[] data = file.split("\n");
        ArrayList<Education2016> results = new ArrayList<>();
        for (int i = 5; i < data.length; i++){
            String[] vals = formatData(data[i]).split(",");
            double noHighSchool = Double.parseDouble(vals[44]);
            double onlyHighSchool = Double.parseDouble(vals[45]);
            double someCollege = Double.parseDouble(vals[46]);
            double bachelorsOrMore = Double.parseDouble(vals[47]);
            results.add(new Education2016(noHighSchool,onlyHighSchool,someCollege,bachelorsOrMore));
        }
        return results;
    }

    private static String formatData(String line) {
        int index = line.indexOf("\"");
        while (index != -1) {
            int index2 = line.indexOf("\"", index + 1);
            line = line.substring(0, line.indexOf("\""))
                    + line.substring(index, index2).replace(",", "")
                    + line.substring(index2);
            index = line.indexOf("\"", index2 + 1);
        }
        return line.replace("\"", "");

    }

}
