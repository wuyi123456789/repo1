package com.xuexi.controller;

import com.xuexi.domain.*;
import com.xuexi.service.MenuService;
import com.xuexi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> list = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询所有角色成功",list);
    }

    /*
     * 查询所有父子菜单（分配菜单的第一个接口）
     * */
    @RequestMapping("/findAllMenu")
    public ResponseResult  findSubMenuListByPid(){

        //-1表示查询所有的父子级菜单
        List<Menu> list = menuService.findSubMenuListByPid(-1);

        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",list);
        return new ResponseResult(true,200,"查询所有的父子级菜单信息成功",map);
    }

    /*
     根据角色id查询该角色关联的菜单信息ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return  new ResponseResult(true,200,"查询角色关联的菜单信息成功",menuByRoleId);
    }

    /*
    * 为角色分配菜单
    * */
    @RequestMapping("/roleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
         roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"响应成功",null);
    }

    /*
     删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
       roleService.deleteRole(id);
       return new ResponseResult(true,200,"删除角色成功",null);
    }
}
