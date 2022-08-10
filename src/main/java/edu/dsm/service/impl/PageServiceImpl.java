package edu.dsm.service.impl;

import edu.dsm.dao.PageDao;
import edu.dsm.service.PageService;
import edu.dsm.util.ErrorMessage;
import edu.dsm.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageMapper;

    @Override
    public int selectSize(String entity) {
        return pageMapper.selectSize(entity);
    }

    @Override
    public Page paging(String entity, Integer pageNumber, Integer pageSize) {
        if (pageNumber <= 0 ) {
            //  将pageNumber<= 0的非法值置为1
            pageNumber = 1;
        }
        // todo 添加对pageSize的限制
        Page page = new Page();
        if (pageSize <= 0){
            //  零条直接返回空
            Map<String,Object> error = new HashMap<>();
            error.put("错误信息",new ErrorMessage("输入错误","输入的pageSize值小于等于0"));
            List list = new ArrayList<>();
            list.add(error);
            page.setTableData(list);
            return page;
        }
        Integer pageBegin = (pageNumber - 1) * pageSize;
        //当前页码
        page.setPageNum(pageNumber);
        //前（后）一页页码
        page.setPrePage(pageNumber - 1);
        page.setNextPage(pageNumber + 1);
        //总数据条数
        page.setSize(pageMapper.selectSize(entity));
        //开始（末尾）数据是第几行
        page.setStartRow(pageBegin + 1);
        page.setEndRow(pageBegin + pageSize + 1);
        //每页数据条数
        page.setPageSize(pageSize);
        //总页数
        Integer div = page.getSize() / pageSize;
        Integer pages = page.getSize() % pageSize == 0 ? div : div + 1;
        page.setPages(pages);
        //是否是第（最后）一页,是否有前（后）一页
        page.setIsFirstPage(pageNumber == 1);
        page.setHasPreviousPage(pageNumber != 1);
        page.setIsLastPage(pageNumber.equals(pages));
        page.setHasNextPage(!pageNumber.equals(pages));
        //查询到的数据
        page.setTableData(pageMapper.selectTableData(entity, pageBegin, pageSize));
        return page;
    }

    @Override
    public List<Map<String, Object>> selectTableData(String entity, Integer pageBegin, Integer pageSize) {
        pageBegin = pageBegin - 1;
        return pageMapper.selectTableData(entity,pageBegin,pageSize);
    }
}
