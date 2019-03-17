import javax.xml.crypto.Data;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        DataManager data = Utils.parseAllData();
        DataManager data = findCompleteDatasets(Utils.parseAllData());
        for (State state : data.getStates()){
            for (County county : state.getCounties())
                System.out.println(state.getName() + "," + county.toString());
        }
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
}
