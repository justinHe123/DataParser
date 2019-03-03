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
}
