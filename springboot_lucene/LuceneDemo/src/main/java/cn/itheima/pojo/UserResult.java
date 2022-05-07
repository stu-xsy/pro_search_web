package cn.itheima.pojo;

public class UserResult {

    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
