package com.yanzhen.service;

import com.yanzhen.po.Dept;
import com.yanzhen.po.Node;

import java.util.List;

public interface IDeptService {
    /**
     * 查询所有的部门
     */
    List<Dept> queryDeptAll();

    /**
     * 添加部门信息
     */
    void addDept(Dept dept);

    /**
     * 根据id查询部门信息
     */
    Dept queryDeptByID(Integer id);

    /***
     * 修改操作
     */
    void updateDept(Dept dept);

    /**
     * 根据id删除记录信息
     */
    void deleteDeptById(Integer id);

    /**
     * 查询树信息
     */
    List<Node> queryDeptTree();
}
