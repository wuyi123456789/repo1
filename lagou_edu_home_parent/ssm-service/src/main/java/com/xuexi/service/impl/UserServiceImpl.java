package com.xuexi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuexi.dao.UserMapper;
import com.xuexi.domain.*;
import com.xuexi.service.UserService;
import com.xuexi.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    /*
     用户登陆
     */
    @Override
    public User login(User user) throws Exception {
        //1.调用mapper方法 user2:包含了密文密码
        User user2 = userMapper.login(user);
        if(user2 !=null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            return user2;
        }else{
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationById(Integer id) {
        return userMapper.findUserRelationById(id);
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //1、根据用户ID清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        
        //2、再重新建立关联关系
        for(Integer roleId:userVo.getRoleIdList()){
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    /*
     获取用户权限信息
     */
    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationById(userId);

        //2.获取角色ID，保存到List集合中
        ArrayList<Integer> roleIds = new ArrayList<>();
        for(Role role:roleList){
            roleIds.add(role.getId());
        }

        //3.根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //4.查询封装父菜单关联的子菜单
        for(Menu menu:parentMenu){
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        //5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //6.封装数据并返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }
}
