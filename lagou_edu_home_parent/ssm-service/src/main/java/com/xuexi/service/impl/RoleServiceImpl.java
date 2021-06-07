package com.xuexi.service.impl;

import com.xuexi.dao.RoleMapper;
import com.xuexi.domain.Role;
import com.xuexi.domain.RoleMenuVo;
import com.xuexi.domain.Role_menu_relation;
import com.xuexi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    /*
     查询所有角色&条件查询
     */
    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    /*
     根据角色id查询该角色关联的菜单信息ID
     */
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    /*
     为角色分配菜单信息
    */
    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1、根据roleId清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
       
        //2.为角色分配菜单
        for(Integer mid : roleMenuVo.getMenuIdList()){
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleId) {
        //调用根据roleId晴空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleId);

        roleMapper.deleteRole(roleId);
    }
}
