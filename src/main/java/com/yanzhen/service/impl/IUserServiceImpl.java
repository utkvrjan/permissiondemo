package com.yanzhen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanzhen.dao.IUserDao;
import com.yanzhen.po.User;
import com.yanzhen.po.UserRole;
import com.yanzhen.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public PageInfo<User> findUserAll(int page, int pageSize, User user) {
           PageHelper.startPage(page,pageSize);//分页
           //查询所有的用户信息
           List<User> list=userDao.queryUserAll(user);
           PageInfo<User> pageinfoList=new PageInfo<User>(list);
           return pageinfoList;
    }

    @Override
    public void addUser(User user) {
       //获取user对象中的角色id集合
       List <Integer> ids=user.getRoleList();
       userDao.addUser(user);//添加用户
       //获取用户id
        int userId=user.getId();
        List<UserRole> list=new ArrayList<>();
        for(Integer rid:ids){
            list.add(new UserRole(rid,userId));
        }
        //关联角色
        userDao.addUserRole(list);
    }

    @Override
    public void deleteUserInfoByIds(List<Integer> ids) {
        userDao.deleteUserByIds(ids);
        userDao.deleteUserRoleByUserIds(ids);
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    @Override
    public void updateUserSubmit(User user) {
       userDao.updateUserSubmit(user);
    }

    @Override
    public User queryUserInfoByNameAndPwd(String username, String password) {
        return userDao.queryUserInfoByNameAndPwd(username,password);
    }

    @Override
    public List<User> findUrlAndPermByUserId(Integer id) {
        return userDao.findUrlAndPermByUserId(id);
    }

    @Override
    public User queryUserByUserName(String name) {
        return userDao.queryUserByUserName(name);
    }

}
