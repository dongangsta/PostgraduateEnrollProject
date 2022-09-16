package edu.dsm.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PageMapper {
    List<Map<String,Object>> selectTableData(String entity, Integer pageBegin, Integer pageSize);

    int selectSize(String entity);
}
