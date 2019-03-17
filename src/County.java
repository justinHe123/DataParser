import java.util.*;

public class County {
    private String name;
    private int fips;
    private Election2016 elec2016;
    private Education2016 educ2016;
//    private Employment2016 employ2016;
    private Trump2016 trump2016;

    public County(String name, int fips) {
        this.name = name;
        this.fips = fips;
        elec2016 = new Election2016(-1);
        educ2016 = new Education2016(-1);
//        employ2016 = new Employment2016(-1, -1, -1, -1);
        trump2016 = new Trump2016();
    }

    public County(String name) {
        this.name = name;
        fips = -1;
        elec2016 = new Election2016(-1);
        educ2016 = new Education2016(-1);
        trump2016 = new Trump2016();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFips() {
        return fips;
    }

    public void setFips(int fips) {
        this.fips = fips;
    }

    public Election2016 getVote2016() {
        return elec2016;
    }

    public void setElec2016(Election2016 vote2016) {
        this.elec2016 = vote2016;
    }

    public Education2016 getEduc2016() {
        return educ2016;
    }

    public void setEduc2016(Education2016 educ2016) {
        this.educ2016 = educ2016;
    }

    public Trump2016 getTrump2016(){
        return trump2016;
    }


//    public Employment2016 getEmploy2016() {
//        return employ2016;
//    }
//
//    public void setEmploy2016(Employment2016 employ2016) {
//        this.employ2016 = employ2016;
//    }

    public String toString(){
        return fips + "," + name + "," + elec2016.toString() + "," + educ2016.toString() + "," + trump2016.toString();
    }
}
