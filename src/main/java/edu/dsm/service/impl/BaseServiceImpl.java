package edu.dsm.service.impl;

import edu.dsm.dao.BaseMapper;
import edu.dsm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<M extends BaseMapper<T>,T> implements BaseService<T> {
    /**
     * 注入mapper对象
     *
     * 注意 这里只能使用@Autowired注解  不能是@Resource
     */
    @Autowired
    protected M baseMapper;
    /**
     * 获取当前类实际注入的mapper对象
     *
     * @return mapper对象
     */
    @Override
    public M getBaseMapper() {
        return baseMapper;
    }
}
