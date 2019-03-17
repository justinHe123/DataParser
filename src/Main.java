import java.io.*;
import java.util.*;

public class Main {
    final static double NO_HS_AVG = 13.0;

    public static void main(String[] args) {
//        DataManager data = Utils.parseAllData();
        DataManager data = findCompleteDatasets(Utils.parseAllData());
//        for (State state : data.getStates()){
//            for (County county : state.getCounties())
//                System.out.println(state.getName() + "," + county.toString());
//        }
        List<DataManager> splitData = splitByNatAvg(data);
//        System.out.println("ABOVE");
//        for (State state : splitData.get(0).getStates()){
//            for (County county : state.getCounties())
//                System.out.println(state.getName() + "," + county.toString());
//        }
//        System.out.println("BELOW");
//        for (State state : splitData.get(1).getStates()){
//            for (County county : state.getCounties())
//                System.out.println(state.getName() + "," + county.toString());
//        }
        saveDataToFile("aboveNationalAverage.csv", splitData.get(0));
        saveDataToFile("belowNationalAverage.csv", splitData.get(1));
    }

    public static DataManager findCompleteDatasets(DataManager data){
        DataManager completeData = new DataManager();
        for (State state : data.getStates()){
            State newState = new State(state.getName());
            completeData.add(newState);
            for (County county : state.getCounties()){
                boolean complete = true;
                if (county.getTrump2016().getRallies().size() == 0) complete = false;
                else if (county.getEduc2016().getNoHighSchool() == -1) complete = false;
                else if (county.getElec2016().getPerGop() == -1) complete = false;
                if (complete){
                    newState.add(county);
                }
            }
        }
        return completeData;
    }

    public static List<DataManager> splitByNatAvg(DataManager data){
        List<DataManager> splitData = new ArrayList<DataManager>();
        DataManager above = new DataManager();
        DataManager below = new DataManager();
        for (State state : data.getStates()) {
            State state1 = new State(state.getName());
            State state2 = new State(state.getName());
            above.add(state1);
            below.add(state2);
            for (County county : state.getCounties()) {
                if (county.getEduc2016().getNoHighSchool() > NO_HS_AVG) state1.add(county);
                else state2.add(county);
            }
        }
        splitData.add(above);
        splitData.add(below);
        return splitData;
    }

    public static void saveDataToFile(String file, DataManager dm){
        String data = "";
        for (State state : dm.getStates()){
            for (County county : state.getCounties()){
                data += state.getName() + "," + county.toString() + "\n";
            }
        }
        writeDataToFile(file, data);
    }


    private static void writeDataToFile(String filePath, String data){
        File outFile = new File(filePath);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
