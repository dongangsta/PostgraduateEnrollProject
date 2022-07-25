package edu.dsm.util;

public class PageUtil {

    private Integer  offset = 0;        //开始记录的偏移数
    private Integer  pagesize = 5;      //每页记录数
    private Integer  totalCount = 0;    //总记录数
    private Integer  pageNo;            //当前页数

    public PageUtil() { }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    //总页数
    public int getTotalPage(){
        if (totalCount==0){
            return 0;
        }
        if(totalCount%pagesize==0){
            return totalCount/pagesize;
        }else{
            return totalCount/pagesize+1;
        }
    }

    //判断下一页的方法
    public boolean isNext(){
        return pageNo<getTotalPage();
    }

    //判断上一页
    public boolean isPrevious(){
        return pageNo>1;
    }


}
