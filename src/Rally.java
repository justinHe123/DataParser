public class Rally {
    private String city;
    private String numVisitors;

    public Rally(String city, String visitors) {
        this.city = city;
        this.numVisitors = numVisitors;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumVisitors() {
        return numVisitors;
    }

    public void setNumVisitors(String numVisitors) {
        this.numVisitors = numVisitors;
    }

    public String toString(){
        return "[" + city + "," + numVisitors + "]";
    }
}
