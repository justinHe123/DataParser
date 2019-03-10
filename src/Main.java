import java.util.*;

public class Main {
    public static void main(String[] args) {
//        for (int i = 5; i < electionData.length; i++){
//            String s = electionData[i];
//            System.out.println(formatData(s));
//        }
//
//        String line = electionData[5];
//        System.out.println(line);
//        int index = line.indexOf("\"");
//        while (index != -1) {
//            int index2 = line.indexOf("\"", index + 1);
//
//            line = line.substring(0, index)
//                    + line.substring(index, index2).replace(",", "")
//                    + line.substring(index2);
//            System.out.println(index + "," + index2);
//            System.out.println(line);
//            index = line.indexOf("\"", index2 + 1);
//        }
//
//        System.out.println(line.replace("\"", ""));



        DataManager data = Utils.parseAllData();
        List<State> states = data.getStates();
        for (State state : states){
            for (County county : state.getCounties())
            System.out.println(county.getName());
            System.out.println(state.getName());
        }
    }

    private static String formatData(String line) {
        String buildString = "";
        char[] chars = line.toCharArray();
        int length = chars.length;
        boolean inQuotes = false;
        for (int i = 0; i < length; i++){
            if (chars[i] == '"') inQuotes = !inQuotes;
            else if (!(inQuotes && chars[i] == ',')) {
                buildString += chars[i];
            }
        }
        return buildString;
    }
}
