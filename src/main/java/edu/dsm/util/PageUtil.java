package edu.dsm.util;

/**
 * The type Page util.
 */
public class PageUtil {

    private Integer  offset = 0;        //开始记录的偏移数
    private Integer  pagesize = 5;      //每页记录数
    private Integer  totalCount = 0;    //总记录数
    private Integer  pageNo;            //当前页数

    /**
     * Instantiates a new Page util.
     */
    public PageUtil() { }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * Sets offset.
     *
     * @param offset the offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * Gets pagesize.
     *
     * @return the pagesize
     */
    public Integer getPagesize() {
        return pagesize;
    }

    /**
     * Sets pagesize.
     *
     * @param pagesize the pagesize
     */
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Gets page no.
     *
     * @return the page no
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * Sets page no.
     *
     * @param pageNo the page no
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * Get total page int.
     *
     * @return the int
     */
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

    /**
     * Is next boolean.
     *
     * @return the boolean
     */
//判断下一页的方法
    public boolean isNext(){
        return pageNo<getTotalPage();
    }

    /**
     * Is previous boolean.
     *
     * @return the boolean
     */
//判断上一页
    public boolean isPrevious(){
        return pageNo>1;
    }


}
