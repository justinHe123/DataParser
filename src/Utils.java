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
        List<State> states = data.getStates();
        String[] electionData = readFileAsString("data/2016_Presidential_Results.csv").split("\n");
        for (int i = 1; i < electionData.length; i++) {
            String[] vals = formatData(electionData[i]).split(",");
            addElection2016(data, vals);
        }
        String[] educationData = readFileAsString("data/Education.csv").split("\n");
        for (int i = 5; i < educationData.length; i++) {
            String[] vals = formatData(educationData[i]).split(",");
            addEducation2016(data, vals);
        }
        String[] employmentData = readFileAsString("data/Unemployment.csv").split("\n");
        for (int i = 8; i < employmentData.length; i++) {
            String[] vals = formatData(employmentData[i]).split(",");
            System.out.println(Arrays.toString(vals));
            addEmployment2016(data, vals);
        }

        return data;
    }

    public static ArrayList<Election2016> parseElectionResults(String file) {
        String[] data = file.split("\n");
        ArrayList<Election2016> results = new ArrayList<>();
        for (int i = 1; i < data.length; i++) {
            String[] vals = data[i].split(",");
            results.add(createElection2016(vals));
        }
        return results;
    }

    public static void addElection2016(DataManager data, String[] vals) {
        Election2016 election = createElection2016(vals);

        County county = findCounty(data, vals, 8, 10, 9);
//        String stateName = vals[8];
//        int stateIndex = data.stateIndex(stateName);
//        State state;
//        if (stateIndex == -1) {
//            state = new State(stateName);
//            data.add(state);
//        } else state = data.getStates().get(stateIndex);
//
//        int fips = Integer.parseInt(vals[10]);
//        int countyIndex = state.countyIndex(fips);
//        County county;
//        if (countyIndex == -1) {
//            String countyName = vals[9];
//            county = new County(countyName, fips);
//        } else county = state.getCounties().get(countyIndex);

        county.setElec2016(election);
    }

    public static Election2016 createElection2016(String[] vals) {
        double demVotes = Double.parseDouble(vals[1]);
        double gopVotes = Double.parseDouble(vals[2]);
        double totalVotes = Double.parseDouble(vals[3]);
        return new Election2016(demVotes, gopVotes, totalVotes);

    }

    public static ArrayList<Education2016> parseEducationData(String file) {
        String[] data = file.split("\n");
        ArrayList<Education2016> results = new ArrayList<>();
        for (int i = 5; i < data.length; i++) {
            String[] vals = formatData(data[i]).split(",");
            results.add(createEducation2016(vals));
        }
        return results;
    }

    public static void addEducation2016(DataManager data, String[] vals) {
        try {
            Education2016 education = createEducation2016(vals);

            County county = findCounty(data, vals, 1, 0, 2);
//            String stateName = vals[1];
//            int stateIndex = data.stateIndex(stateName);
//            State state;
//            if (stateIndex == -1) {
//                state = new State(stateName);
//                data.add(state);
//            } else state = data.getStates().get(stateIndex);
//
//            int fips = Integer.parseInt(vals[0]);
//            int countyIndex = state.countyIndex(fips);
//            County county;
//            if (countyIndex == -1) {
//                String countyName = vals[2];
//                county = new County(countyName, fips);
//            } else county = state.getCounties().get(countyIndex);

            county.setEduc2016(education);
        } catch (Exception e){
            System.out.println("Invalid format");
        }
    }

    public static Education2016 createEducation2016(String[] vals) {
        double noHighSchool = Double.parseDouble(vals[43]);
        double onlyHighSchool = Double.parseDouble(vals[44]);
        double someCollege = Double.parseDouble(vals[45]);
        double bachelorsOrMore = Double.parseDouble(vals[46]);
        return new Education2016(noHighSchool, onlyHighSchool, someCollege, bachelorsOrMore);
    }

    public static ArrayList<Employment2016> parseEmploymentData(String file) {
        String[] data = file.split("\n");
        ArrayList<Employment2016> results = new ArrayList<>();
        for (int i = 8; i < data.length; i++) {
            String[] vals = formatData(data[i]).split(",");
            results.add(createEmployment2016(vals));
        }
        return results;
    }

    public static void addEmployment2016(DataManager data, String[] vals) {
        try {
            Employment2016 employment = createEmployment2016(vals);

            County county = findCounty(data, vals, 1, 0, 2);
//            String stateName = vals[1];
//            int stateIndex = data.stateIndex(stateName);
//            State state;
//            if (stateIndex == -1) {
//                state = new State(stateName);
//                data.add(state);
//            } else state = data.getStates().get(stateIndex);
//
//            int fips = Integer.parseInt(vals[0]);
//            int countyIndex = state.countyIndex(fips);
//            County county;
//            if (countyIndex == -1) {
//                String countyName = vals[2];
//                if (endsInCapital(countyName)) countyName = countyName.substring(0, countyName.length() - 3);
//                county = new County(countyName, fips);
//            } else county = state.getCounties().get(countyIndex);

            county.setEmploy2016(employment);
        } catch (Exception e){
            System.out.println("Invalid format");
        }

    }

    public static Employment2016 createEmployment2016(String[] vals) {
        int totalLaborForce = Integer.parseInt(vals[42]);
        int employedLaborForce = Integer.parseInt(vals[43]);
        int unemployedLaborForce = Integer.parseInt(vals[44]);
        double unemployedPercent = Double.parseDouble(vals[45]);
        return new Employment2016(totalLaborForce, employedLaborForce, unemployedLaborForce, unemployedPercent);
    }

    private static County findCounty(DataManager data, String[] vals, int stateNameIndex, int fipsIndex, int countyNameIndex){
        String stateName = vals[stateNameIndex];
        int stateIndex = data.stateIndex(stateName);
        State state;
        if (stateIndex == -1) {
            state = new State(stateName);
            data.add(state);
        } else state = data.getStates().get(stateIndex);

        int fips = Integer.parseInt(vals[fipsIndex]);
        int countyIndex = state.countyIndex(fips);
        County county;
        if (countyIndex == -1) {
            String countyName = vals[countyNameIndex];
            if (endsInCapital(countyName)) countyName = countyName.substring(0, countyName.length() - 3);
            return new County(countyName, fips);
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
}
