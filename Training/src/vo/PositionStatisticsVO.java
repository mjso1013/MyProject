package vo;

public class PositionStatisticsVO {

    private String position;
    private int memberCount;
    private int maxSal;
    private int minSal;
    private int avgSal;
    private int sumSal;
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public int getMemberCount() {
        return memberCount;
    }
    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }
    public int getMaxSal() {
        return maxSal;
    }
    public void setMaxSal(int maxSal) {
        this.maxSal = maxSal;
    }
    public int getMinSal() {
        return minSal;
    }
    public void setMinSal(int minSal) {
        this.minSal = minSal;
    }
    public int getAvgSal() {
        return avgSal;
    }
    public void setAvgSal(int avgSal) {
        this.avgSal = avgSal;
    }
    public int getSumSal() {
        return sumSal;
    }
    public void setSumSal(int sumSal) {
        this.sumSal = sumSal;
    }



}
