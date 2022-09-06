package edu.dsm.service;

import edu.dsm.entity.po.User;

import java.util.List;

public interface UserService {
    User getOneById(Integer userId);
    User getByUserName(String userName);
    User getAdminByUserName(String userName);
    List<User> getAll();
    int turnRole(User user);
    int addUser(User user);
    int deleteBatchUsers(Integer [] ids);
    int rePassword(Integer userId);
    int updateUser(User user);
    int updatePredict(Integer predict,Integer userId);
}
