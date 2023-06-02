package edu.dsm.service.impl;

import edu.dsm.dao.SoccerMapper;
import edu.dsm.entity.po.Soccer;
import edu.dsm.service.SoccerService;
import org.springframework.stereotype.Service;

/**
 * ClassName: SoccerServiceImpl
 * Description:
 * Author: dong Date: 2023/6/2 14:16 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service
public class SoccerServiceImpl extends BaseServiceImpl<SoccerMapper, Soccer> implements SoccerService {
    @Override
    public void addSoccer(Soccer soccer){
        this.getBaseMapper().insert(soccer);
    }
}
