public class ElectionResult{
    private double demVotes, gopVotes, totalVotes, percentDem, percentGop, pointDiff, pointDiffPercent;
    private String stateAbbr, countyName, combinedFips;

    public ElectionResult(String line){
        int index = line.indexOf("\"");
        if(line.contains("\"")){
            line = this.removeComma(line, index);
            line = line.replace("\"", "");
        }
    String[] vals = line.split(",");
    demVotes = Double.parseDouble(vals[1]);
    gopVotes = Double.parseDouble(vals[2]);
    totalVotes = Double.parseDouble(vals[3]);
    percentDem = Double.parseDouble(vals[4]);
    percentGop = Double.parseDouble(vals[5]);
    pointDiff = Double.parseDouble(vals[6]);
    pointDiffPercent = Double.parseDouble(vals[7].substring(0, vals[7].length() - 1)) / 100;
    stateAbbr = vals[8];
    countyName = vals[9];
    combinedFips = vals[10];
}

    private String removeComma(String line, int startIndex){
        int maxIndex = line.indexOf("\"", startIndex + 1);
        int index = line.indexOf(",", startIndex);
        if(index < maxIndex){
            line = line.substring(0, index) + line.substring(index + 1, line.length());
            line = removeComma(line, index);
        }
        return line;
    }

    public double getDemVotes(){
        return demVotes;
    }

    public void setDemVotes(double votes){
        demVotes = votes;
    }

    public double getGopVotes(){
        return gopVotes;
    }

    public void setGopVotes(double votes){
        gopVotes = votes;
    }

    public double getTotalVotes(){
        return totalVotes;
    }

    public void setTotalVotes(double votes){
        totalVotes = votes;
    }

    public double getPercentDem(){
        return percentDem;
    }

    public void setPercentDem(double percent){
        percentDem = percent;
    }

    public double getPercentGop(){
        return percentGop;
    }

    public void setPercentGop(double percent){
        percentGop = percent;
    }

    public double getPointDiff(){
        return pointDiff;
    }

    public void setPointDiff(double diff){
        pointDiff = diff;
    }

    public Double getPointDiffPercent(){
        return pointDiffPercent;
    }

    public void setPointDiffPercent(double percent){
        pointDiffPercent = percent;
    }

    public String getStateAbbr(){
        return stateAbbr;
    }

    public void setStateAbbr(String abbr){
        stateAbbr = abbr;
    }

    public String getCountyName(){
        return countyName;
    }

    public void setCountyName(String name){
        countyName = name;
    }

    public String getCombinedFips(){
        return combinedFips;
    }

    public void setCombinedFips(String fips){
        combinedFips = fips;
    }

}
