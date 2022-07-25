package edu.dsm.dao;

import edu.dsm.entity.School;
import edu.dsm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User selectByUserName(String userName);
    public User selectAdminByUserName(String userName);
    public List<User> selectAll();
    public User selectById(Integer id);
    public int turnToAdmin(int userId);
    public int turnToUser(int userId);
    public int addUser(User user);
    public int deleteBatchUsers(Integer [] ids);
    public int rePassword(Integer userId);
    public int updateUser(User user);
    public int updatePredict(Integer predict,Integer userId);
}
