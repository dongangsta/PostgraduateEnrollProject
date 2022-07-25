package edu.dsm.service;

import edu.dsm.dao.UserDao;
import edu.dsm.entity.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * Get one by id user.
     *
     * @param userId the user id
     * @return the user
     */
    public User getOneById(Integer userId){
        return userDao.selectById(userId );
    }

    /**
     * Get by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    public User getByUserName(String userName){return userDao.selectByUserName(userName);}

    /**
     * Get admin by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    public User getAdminByUserName(String userName){return userDao.selectAdminByUserName(userName);}

    /**
     * Get all list.
     *
     * @return the list
     */
    public List<User> getAll(){return userDao.selectAll();}

    /**
     * Turn to admin int.
     *
     * @param userId the user id
     * @return the int
     */
    public int turnToAdmin(int userId){ return userDao.turnToAdmin(userId);}

    /**
     * Turn to user int.
     *
     * @param userId the user id
     * @return the int
     */
    public int turnToUser(int userId){ return userDao.turnToUser(userId);}

    /**
     * Add user int.
     *
     * @param user the user
     * @return the int
     */
    public int addUser(User user){ return userDao.addUser(user);}

    /**
     * Delete batch users int.
     *
     * @param ids the ids
     * @return the int
     */
    public int deleteBatchUsers(Integer [] ids){
        return userDao.deleteBatchUsers(ids);
    }

    /**
     * Re password int.
     *
     * @param userId the user id
     * @return the int
     */
    public int rePassword(Integer userId){return userDao.rePassword(userId);}

    /**
     * Update user int.
     *
     * @param user the user
     * @return the int
     */
    public int updateUser(User user){return userDao.updateUser(user);}

    /**
     * Update predict int.
     *
     * @param predict the predict
     * @param userId  the user id
     * @return the int
     */
    public int updatePredict(Integer predict,Integer userId){return userDao.updatePredict(predict,userId);}
}
