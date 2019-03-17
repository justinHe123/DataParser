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

    public static DataManager parseAllData() {
        DataManager data = new DataManager();
        parseElectionData(data);
        parseEducationData(data);
//        parseEmploymentData(data);
        parseTrumpData(data);
        return data;
    }

    private static void parseElectionData(DataManager data) {
        String[] electionData = readFileAsString("data/2016_Presidential_Results.csv").split("\n");
        for (int i = 1; i < electionData.length; i++) {
            String[] vals = formatData(electionData[i]).split(",");
            addElection2016(data, vals);
        }
    }

    private static void addElection2016(DataManager data, String[] vals) {
        try {
            Election2016 election = createElection2016(vals);
            County county = findCounty(data, vals, 8, 10, 9);
            county.setElec2016(election);
        } catch (Exception e){
            System.err.println("Invalid format: " + Arrays.toString(vals));
        }
    }

    private static Election2016 createElection2016(String[] vals) {
//        double perDem = Double.parseDouble(vals[4]);
        double perGop = formatElectionPercent(Double.parseDouble(vals[5]));
        return new Election2016(perGop);

    }

    private static void parseEducationData(DataManager data) {
        String[] educationData = readFileAsString("data/Education.csv").split("\n");
        for (int i = 5; i < educationData.length - 10; i++) {
            String[] vals = formatData(educationData[i]).split(",");
            addEducation2016(data, vals);
        }
    }

    private static void addEducation2016(DataManager data, String[] vals) {
        try {
            Education2016 education = createEducation2016(vals);
            County county = findCounty(data, vals, 1, 0, 2);
            county.setEduc2016(education);
        } catch (Exception e){
            System.err.println("Invalid format: " + Arrays.toString(vals));
        }
    }

    private static Education2016 createEducation2016(String[] vals) {
        double noHighSchool = Double.parseDouble(vals[43]);
//        double onlyHighSchool = Double.parseDouble(vals[44]);
//        double someCollege = Double.parseDouble(vals[45]);
//        double bachelorsOrMore = Double.parseDouble(vals[46]);
        return new Education2016(noHighSchool);
    }

//    private static void parseEmploymentData(DataManager data) {
//        String[] employmentData = readFileAsString("data/Unemployment.csv").split("\n");
//        for (int i = 8; i < employmentData.length; i++) {
//            String[] vals = formatData(employmentData[i]).split(",");
//            addEmployment2016(data, vals);
//        }
//    }
//
//    private static void addEmployment2016(DataManager data, String[] vals) {
//        try {
//            Employment2016 employment = createEmployment2016(vals);
//            County county = findCounty(data, vals, 1, 0, 2);
//            county.setEmploy2016(employment);
//        } catch (Exception e){
//            System.err.println("Invalid format: " + Arrays.toString(vals));
//        }
//    }
//
//    private static Employment2016 createEmployment2016(String[] vals) {
//        int totalLaborForce = Integer.parseInt(vals[42].trim());
//        int employedLaborForce = Integer.parseInt(vals[43].trim());
//        int unemployedLaborForce = Integer.parseInt(vals[44].trim());
//        double unemployedPercent = Double.parseDouble(vals[45].trim());
//        return new Employment2016(totalLaborForce, employedLaborForce, unemployedLaborForce, unemployedPercent);
//    }

    private static void parseTrumpData(DataManager data){
        String[] trumpData = Utils.readFileAsString("data/Trump_Rallies.csv").split("\n");
        for (int i = 1; i < trumpData.length; i++){
            String[] vals = formatData(trumpData[i]).split(",");
            addTrump2016(data, vals);
        }
    }

    private static void addTrump2016(DataManager data, String[] vals) {
        try {
            Rally rally = createRally(vals);
            if (rally.getVisitors().equals("")) throw new NumberFormatException();
            County county = findCountyByName(data, vals, 2, 6);
            county.getTrump2016().addRally(rally);
        } catch (Exception e){
            System.err.println("Invalid format: " + Arrays.toString(vals));
        }
    }

    private static Rally createRally(String[] vals){
        String city = vals[1];
        String visitors = vals[4];
        return new Rally(city, visitors);
    }

    private static County findCountyByName(DataManager data, String[] vals, int stateNameIndex, int countyNameIndex){
        State state = findState(data, vals, stateNameIndex);
        String countyName = cleanCountyName(vals[countyNameIndex]);
        int countyIndex = state.countyIndex(countyName);
        if (countyIndex == -1){
            County county = new County(countyName);
            state.add(county);
            return county;
        } else return state.getCounties().get(countyIndex);
    }

    private static State findState(DataManager data, String[] vals, int stateNameIndex){
        String stateName = vals[stateNameIndex].toUpperCase();
        int stateIndex = data.stateIndex(stateName);
        State state;
        if (stateIndex == -1) {
            state = new State(stateName);
            data.add(state);
        } else state = data.getStates().get(stateIndex);
        return state;
    }

    private static County findCounty(DataManager data, String[] vals, int stateNameIndex, int fipsIndex, int countyNameIndex){
        State state = findState(data, vals, stateNameIndex);

        int fips = Integer.parseInt(vals[fipsIndex]);
        int countyIndex = state.countyIndex(fips);
        if (countyIndex == -1) {
            String countyName = vals[countyNameIndex];
            countyName = cleanCountyName(countyName);
            County county = new County(countyName, fips);
            state.add(county);
            return county;
        } else return state.getCounties().get(countyIndex);
    }

    private static String formatData(String line) {
        String buildString = "";
        char[] chars = line.toCharArray();
        int length = chars.length;
        boolean inQuotes = false;
        for (int i = 0; i < length; i++) {
            if (chars[i] == '"') inQuotes = !inQuotes;
            else if (!(inQuotes && chars[i] == ',')) {
                buildString += chars[i];
            }
        }
        return buildString;
    }

    private static boolean endsInCapital(String s) {
        char c = s.charAt(s.length() - 1);
        return c >= 'A' && c <= 'Z';
    }

    private static String cleanCountyName(String s){
        if (endsInCapital(s)) s = s.substring(0, s.length() - 3);
        int countyEndIndex = s.length() - 7;
        if (countyEndIndex > 0 && s.substring(countyEndIndex, s.length()).equals(" County")) s = s.substring(0, countyEndIndex);
        return s.trim();

    }

    private static double formatElectionPercent(Double d){
        d = d * 100;
        String s = String.format("%,.1f", d);
        return Double.parseDouble(s);
    }
}
