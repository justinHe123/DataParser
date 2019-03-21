public class Education2016 {
    private double perNoHighSchool;
//    private double onlyHighSchool;
//    private double someCollege;
//    private double bachelorsOrMore;

//    public Education2016(double noHighSchool, double onlyHighSchool, double someCollege, double bachelorsOrMore) {
//        this.noHighSchool = noHighSchool;
//        this.onlyHighSchool = onlyHighSchool;
//        this.someCollege = someCollege;
//        this.bachelorsOrMore = bachelorsOrMore;
//    }

    public Education2016 (double perNoHighSchool){
        this.perNoHighSchool = perNoHighSchool;
    }

    public double getPerNoHighSchool() {
        return perNoHighSchool;
    }

    public void setPerNoHighSchool(double perNoHighSchool) {
        this.perNoHighSchool = perNoHighSchool;
    }

//    public double getOnlyHighSchool() {
//        return onlyHighSchool;
//    }
//
//    public void setOnlyHighSchool(double onlyHighSchool) {
//        this.onlyHighSchool = onlyHighSchool;
//    }
//
//    public double getSomeCollege() {
//        return someCollege;
//    }
//
//    public void setSomeCollege(double someCollege) {
//        this.someCollege = someCollege;
//    }
//
//    public double getBachelorsOrMore() {
//        return bachelorsOrMore;
//    }
//
//    public void setBachelorsOrMore(double bachelorsOrMore) {
//        this.bachelorsOrMore = bachelorsOrMore;
//    }
//
//    public String toString(){
//        return noHighSchool + "," + onlyHighSchool + "," + someCollege + "," + bachelorsOrMore;
//    }

    public String toString(){
        return "" + perNoHighSchool;
    }
}
