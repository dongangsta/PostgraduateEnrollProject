package edu.dsm.service;

import edu.dsm.dao.UserDao;
import edu.dsm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User getOneById(Integer userId){
        return userDao.selectById(userId );
    }
    public User getByUserName(String userName){return userDao.selectByUserName(userName);}
    public User getAdminByUserName(String userName){return userDao.selectAdminByUserName(userName);}
    public List<User> getAll(){return userDao.selectAll();}
    public int turnToAdmin(int userId){ return userDao.turnToAdmin(userId);}
    public int turnToUser(int userId){ return userDao.turnToUser(userId);}
    public int addUser(User user){ return userDao.addUser(user);}
    public int deleteBatchUsers(Integer [] ids){
        return userDao.deleteBatchUsers(ids);
    }
    public int rePassword(Integer userId){return userDao.rePassword(userId);}
    public int updateUser(User user){return userDao.updateUser(user);}
    public int updatePredict(Integer predict,Integer userId){return userDao.updatePredict(predict,userId);}
}
