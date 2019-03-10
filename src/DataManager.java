import java.util.*;

public class DataManager {
    private List<State> states;

    public DataManager() {
        states = new ArrayList<State>();
    }

    public int stateIndex(String name){
        for (int i = 0; i < states.size(); i++){
            State state = states.get(i);
            if (state.getName().equals(name)) return i;
        }
        return -1;
    }

    public void add(State state){
        states.add(state);
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
