package edu.dsm.dao;

import java.io.Serializable;
import java.util.List;

/**
 * The interface Base mapper.
 *
 * @param <T> the type parameter
 */
public interface BaseMapper<T> {
    /**
     * delete by primary key
     *
     * @param id p
     * @return count int
     */
    int deleteByPrimaryKey(Serializable id);

    /**
     * insert record
     *
     * @param record record
     * @return count int
     */
    int insert(T record);

    /**
     * Select by primary key t.
     *
     * @param id the id
     * @return the t
     */
    T selectByPrimaryKey(Integer id);

    /**
     * Select all list.
     *
     * @return the list
     */
    List<T> selectAll();

    /**
     * updateByPrimaryKey
     *
     * @param record record
     * @return count int
     */
    int updateByPrimaryKey(T record);

}
