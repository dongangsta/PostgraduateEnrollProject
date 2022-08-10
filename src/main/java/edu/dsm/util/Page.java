package edu.dsm.util;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Page {
    //第几页
    private Integer pageNum;
    //每页数据条数
    private Integer pageSize;
    //总数据条数
    private Integer size;
    //起始（末尾）数据
    private Integer startRow;
    private Integer endRow;
    //总页数
    private Integer pages;
    //上（下）一页页码
    private Integer prePage;
    private Integer nextPage;
    //是否是第（最后）一行
    private Boolean isFirstPage;
    private Boolean isLastPage;
    //有无前（后）页
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    //数据数组
    private List<Map<String,Object>> tableData;
}
