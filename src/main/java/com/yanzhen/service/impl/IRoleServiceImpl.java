package com.yanzhen.service.impl;

import com.yanzhen.dao.IRoleDao;
import com.yanzhen.po.Node;
import com.yanzhen.po.Role;
import com.yanzhen.po.RoleMenu;
import com.yanzhen.service.IRoleService;
import com.yanzhen.util.TreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private TreeBuilder treeBuilder;
    @Override
    public List<Role> queryRoleAll() {
        return roleDao.queryRoleAll();
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Role queryRoleById(Integer id) {
        return roleDao.queryRoleByID(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        roleDao.deleteRoleById(id);
    }

    @Override
    @Transactional
    public void insertForEach(int[] ids, int roleId) {
        //先删除原来的映射关系
        this.deleteRoleMenuByRoleId(roleId);
        //遍历添加
        List<RoleMenu> list=new ArrayList<>();
        for(int id:ids){
            list.add(new RoleMenu(roleId,id));
        }
        roleDao.insertForEach(list);
    }

    @Override
    public void deleteRoleMenuByRoleId(int id) {
        roleDao.deleteRoleMenuByRoleId(id);
    }

    @Override
    public List<Node> queryRoleTree() {
        //查询所有的数据
        List nodeList=roleDao.queryRoleTree();
        treeBuilder.buildTree(nodeList);
        return nodeList;
    }
}
