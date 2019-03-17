import java.util.*;

public class Trump2016 {
    private List<Rally> rallies;

    public Trump2016() {
        rallies = new ArrayList<Rally>();
    }

    public List<Rally> getRallies() {
        return rallies;
    }

    public void addRally(Rally rally){
        rallies.add(rally);
    }

    public String toString(){
        String s = "\"";
        for (Rally rally : rallies){
            s += rally.toString() + ",";
        }
        if (!s.equals("{")) s = s.substring(0, s.length() - 1);
        return s + "\"";
    }
}
