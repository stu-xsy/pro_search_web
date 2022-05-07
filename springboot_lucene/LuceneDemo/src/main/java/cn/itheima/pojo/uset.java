package cn.itheima.pojo;

public class uset {
    private String keyword;
    private double TFIDF;


    public double getTFIDF() {
        return TFIDF;
    }

    public void setTFIDF(double TFIDF) {
        this.TFIDF = TFIDF;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
