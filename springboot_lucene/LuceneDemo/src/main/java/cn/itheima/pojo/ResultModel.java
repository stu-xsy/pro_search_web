package cn.itheima.pojo;

import java.util.List;

public class ResultModel {
    private List<sku> skulist;
    private Long recordcount;
    private Long pageCount;
    private long curPage;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<sku> getSkulist() {
        return skulist;
    }

    public void setSkulist(List<sku> skulist) {
        this.skulist = skulist;
    }

    public Long getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(Long recordcount) {
        this.recordcount = recordcount;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}
