package edu.dsm.dao;

import edu.dsm.entity.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User selectByUserName(String userName);
    User selectAdminByUserName(String userName);
    List<User> selectAll();
    User selectById(Integer id);
    int turnToAdmin(int userId);
    int turnToUser(int userId);
    int addUser(User user);
    int deleteBatchUsers(Integer [] ids);
    int rePassword(Integer userId);
    int updateUser(User user);
    int updatePredict(Integer predict,Integer userId);
}
