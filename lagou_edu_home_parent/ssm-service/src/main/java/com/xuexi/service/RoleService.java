package com.xuexi.service;

import com.xuexi.domain.Role;
import com.xuexi.domain.RoleMenuVo;
import com.xuexi.domain.Role_menu_relation;

import java.util.List;

public interface RoleService {
    /*
     查询所有角色&条件查询
     */
    public List<Role> findAllRole(Role role);

    /*
     根据角色id查询该角色关联的菜单信息ID
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
     为角色分配菜单信息
    */
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
     删除角色
     */
    public void deleteRole(Integer roleId);

}
