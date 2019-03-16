public class Election2016 {
    private double perDem;
    private double perGop;

    public Election2016(double perDem, double perGop) {
        this.perDem = perDem;
        this.perGop = perGop;
    }

    public double getPerDem() {
        return perDem;
    }

    public void setPerDem(double perDem) {
        this.perDem = perDem;
    }

    public double getPerGop() {
        return perGop;
    }

    public void setPerGop(double perGop) {
        this.perGop = perGop;
    }

    public String toString(){
        return perDem + "," + perGop;
    }
}
