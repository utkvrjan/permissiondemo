package com.yanzhen.dao;

import com.yanzhen.po.Menu;
import com.yanzhen.po.NavNode;
import com.yanzhen.po.Node;
import com.yanzhen.po.RoleMenu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("menuDao")
public interface IMenuDao {
    /**
     * 查询所有的菜单信息
     */
    List<Menu> queryMenuAll();

    /**
     * 添加菜单
     */
    void addMenu(Menu menu);

    /**
     * 删除功能
     */
    void deleteMenuById(Integer id);

    /**
     * 根据id查询菜单详细信息
     */
    Menu queryMenuByID(Integer id);

    /**
     * 修改菜单记录信息
     */
    void updateMenuSubmit(Menu menu);

    /**
     * 根据角色查询关联到的菜单
     */
    List<RoleMenu> queryMenuByRoleId(Integer roleID);

    /**
     * 获取树结构的菜单
     */
    List<Node> queryMenuTree();

    /**
     * 根据id获取关联的菜单信息
     */
    List<NavNode> queryNavNodeListTree(Integer id);

    List<Menu> isChild(Integer id);

}
