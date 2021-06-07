package com.xuexi.service;

import com.github.pagehelper.PageInfo;
import com.xuexi.domain.*;

import java.util.List;

public interface UserService {
    /*
     用户分页&多条件组合查询
    */
    public PageInfo<User> findAllUserByPage(UserVo userVo);

    /*
     用户登陆（根据用户名查询具体的用户信息）
     */
    public User login(User user) throws Exception;

    /*
     根据用户ID查询关联的角色信息
     */
    public List<Role> findUserRelationById(Integer id);

    /*
     用户关联角色
     */
    public void  userContextRole(UserVo userVo);

    /*
        获取用户权限,进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userId);
}
