package edu.dsm.service.impl;

import edu.dsm.dao.UserMapper;
import edu.dsm.entity.po.User;
import edu.dsm.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements UserService {

    /**
     * Get one by id user.
     *
     * @param userId the user id
     * @return the user
     */
    @Override
    public User getOneById(Integer userId){
        return this.getBaseMapper().selectByPrimaryKey(userId );
    }

    /**
     * Get by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    @Override
    public User getByUserName(String userName){return this.getBaseMapper().selectByUserName(userName);}

    /**
     * Get admin by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    @Override
    public User getAdminByUserName(String userName){return this.getBaseMapper().selectAdminByUserName(userName);}

    /**
     * Get all list.
     *
     * @return the list
     */
    @Override
    public List<User> getAll(){return this.getBaseMapper().selectAll();}

    /**
     * Turn to admin int.
     *
     * @return the int
     */
    @Override
    public int turnRole(User user){ return this.getBaseMapper().turnRole(user);}

    /**
     * Add user int.
     *
     * @param user the user
     * @return the int
     */
    @Override
    public int addUser(User user){ return this.getBaseMapper().insert(user);}

    /**
     * Delete batch users int.
     *
     * @param ids the ids
     * @return the int
     */
    @Override
    public int deleteBatchUsers(Integer [] ids){
        return this.getBaseMapper().deleteBatchUsers(ids);
    }

    /**
     * Re password int.
     *
     * @param userId the user id
     * @return the int
     */
    @Override
    public int rePassword(Integer userId){return this.getBaseMapper().rePassword(userId);}

    /**
     * Update user int.
     *
     * @param user the user
     * @return the int
     */
    @Override
    public int updateUser(User user){return this.getBaseMapper().updateByPrimaryKey(user);}

    /**
     * Update predict int.
     *
     * @param predict the predict
     * @param userId  the user id
     * @return the int
     */
    @Override
    public int updatePredict(Integer predict,Integer userId){return this.getBaseMapper().updatePredict(predict,userId);}
}
