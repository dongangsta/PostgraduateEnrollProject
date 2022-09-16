package edu.dsm.dao;

import edu.dsm.entity.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectByUserName(String userName);
    User selectAdminByUserName(String userName);
    int turnRole(User user);
    int rePassword(Integer userId);
    int updatePredict(Integer predict,Integer userId);
}
