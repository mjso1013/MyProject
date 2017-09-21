package vo;

public class IncreaseGradeVO {
    private int increaseNum;     //등급번호
    private int year;			 //연도
    private String grade;		 //등급
    private int increaseRate;    //인상률
    private String avgIncreaseRate;


    public int getIncreaseNum() {
        return increaseNum;
    }
    public void setIncreaseNum(int increaseNum) {
        this.increaseNum = increaseNum;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public int getIncreaseRate() {
        return increaseRate;
    }
    public void setIncreaseRate(int increaseRate) {
        this.increaseRate = increaseRate;
    }
    public String getAvgIncreaseRate() {
        return avgIncreaseRate;
    }
    public void setAvgIncreaseRate(String avgIncreaseRate) {
        this.avgIncreaseRate = avgIncreaseRate;
    }


}
