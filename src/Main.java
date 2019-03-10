import java.util.*;

public class Main {
    public static void main(String[] args) {
        DataManager data = Utils.parseAllData();
        List<State> states = data.getStates();
        for (State state : states){
            for (County county : state.getCounties())
                System.out.println(state.getName() + "," + county.toString());
        }
    }
}
