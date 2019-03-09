import java.util.*;

public class DataManager {
    private List<State> states;

    public DataManager() {
        states = new ArrayList<State>();
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
