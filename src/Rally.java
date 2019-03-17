public class Rally {
    private String city;
    private String visitors;

    public Rally(String city, String visitors) {
        this.city = city;
        this.visitors = visitors;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString(){
        return "[" + city + "," + visitors + "]";
    }
}
