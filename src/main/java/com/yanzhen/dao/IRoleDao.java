package com.yanzhen.dao;

import com.yanzhen.po.Node;
import com.yanzhen.po.Role;
import com.yanzhen.po.RoleMenu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("roleDao")
public interface IRoleDao {
    /**
     * 查询所有的角色信息
     */
    public List<Role> queryRoleAll();

    /**
     * 添加
     */
    void addRole(Role role );


    /**
     * 根据id查询角色信息
     */
     Role queryRoleByID(Integer id);

    /**
     * 修改
     */
    void updateRole(Role role );

    /**
     * 删除
     */
    void deleteRoleById(Integer id);

    /**
     * 删除角色关联到的菜单内容
     */
    void deleteRoleMenuByRoleId(Integer roleId);

    /**
     * 批量的添加工作
     */
     void insertForEach(List<RoleMenu> list);

    /**
     * 查询树
     */
    List<Node> queryRoleTree();
}
