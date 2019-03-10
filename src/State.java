import java.util.*;

public class State {
    private String name;
    private List<County> counties;

    public State(String name) {
        this.name = name;
        counties = new ArrayList<>();
    }

    public int countyIndex(String name){
        for(int i = 0; i < counties.size(); i++){
            County county = counties.get(i);
            if (county.getName().equals(name)) return i;
        }
        return -1;
    }

    public int countyIndex(int fips){
        for(int i = 0; i < counties.size(); i++){
            County county = counties.get(i);
            if (county.getFips() == fips) return i;
        }
        return -1;
    }

    public void add(County county){
        counties.add(county);
    }

    public void removeCounty(County county){
        counties.remove(county);
    }

    public County removeCounty(int index){
        return counties.remove(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }
}
