package edu.dsm.service;

import edu.dsm.util.Page;

import java.util.List;
import java.util.Map;

public interface PageService {
    int selectSize(String entity);

    Page paging(String entity, Integer pageNumber, Integer pageSize);

    List<Map<String,Object>> selectTableData(String entity, Integer pageBegin, Integer pageSize);
}
