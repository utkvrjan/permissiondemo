package com.yanzhen.service.impl;

import com.yanzhen.dao.IMenuDao;
import com.yanzhen.po.Menu;
import com.yanzhen.po.NavNode;
import com.yanzhen.po.Node;
import com.yanzhen.po.RoleMenu;
import com.yanzhen.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class IMenuServiceImpl implements IMenuService {
    @Autowired
    private IMenuDao menuDao;
    @Override
    public List<Menu> queryMenuAll() {
        return menuDao.queryMenuAll();
    }

    @Override
    public void addMenu(Menu menu) {
        menuDao.addMenu(menu);
    }

    @Override
    public void deleteMenuById(Integer id) {
        menuDao.deleteMenuById(id);
    }

    @Override
    public Menu queryMenuByID(Integer id) {
        return menuDao.queryMenuByID(id);
    }

    @Override
    public void updateMenuSubmit(Menu menu) {
        menuDao.updateMenuSubmit(menu);
    }

    @Override
    public List<RoleMenu> queryMenuByRoleId(Integer roleID) {
        return menuDao.queryMenuByRoleId(roleID);
    }

    @Override
    public List<Node> queryMenuTree() {
        return menuDao.queryMenuTree();
    }

    @Override
    public List<NavNode> queryNavNodeListTree(Integer id) {
        return menuDao.queryNavNodeListTree(id);
    }

    @Override
    public List<Menu> isChild(Integer id) {
        return menuDao.isChild(id);
    }
}
