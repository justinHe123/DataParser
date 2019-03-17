import java.util.*;

public class Trump2016 {
    private List<String> cities;

    public Trump2016() {
        cities = new ArrayList<String>();
    }

    public List<String> getCities() {
        return cities;
    }

    public void addCity(String city){
        cities.add(city);
    }

    public String toString(){
        String s = "{";
        for (String city : cities){
            s += city + ",";
        }
        if (!s.equals("{")) s = s.substring(0, s.length() - 1);
        return s + "}";
    }
}
