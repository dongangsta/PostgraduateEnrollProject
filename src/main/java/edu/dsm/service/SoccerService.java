package edu.dsm.service;

import edu.dsm.entity.po.Soccer;

/**
 * ClassName: SoccerService Description: Author: dong Date: 2023/6/2 11:30 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface SoccerService extends BaseService<Soccer>{
    public void addSoccer(Soccer soccer);
}
