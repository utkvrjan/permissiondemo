package com.yanzhen.util;

import com.alibaba.fastjson.JSON;
import com.yanzhen.po.NavNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("treeBuilder2")
public class TreeBuilder2 {
    List<NavNode> nodes = new ArrayList<>();

//    public String buildTree(List<NavNode> nodes) {
//        TreeBuilder2 treeBuilder = new TreeBuilder2(nodes);
//        return treeBuilder.buildJSONTree();
//    }

    public List<NavNode> buildTree(List<NavNode> nodes) {
        TreeBuilder2 treeBuilder = new TreeBuilder2(nodes);
        return treeBuilder.buildTree();
    }

    public TreeBuilder2() {}

    public TreeBuilder2(List<NavNode> nodes) {
        super();
        this.nodes = nodes;
    }

    // 构建JSON树形结构
    public String buildJSONTree() {
        List<NavNode> nodeTree = buildTree();
        String jsonStr = JSON.toJSONString(nodeTree);
        return jsonStr;
    }

    // 构建树形结构
    public List<NavNode> buildTree() {
        List<NavNode> treeNodes = new ArrayList<>();
        List<NavNode> rootNodes = getRootNodes();
        for (NavNode rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    // 递归子节点
    public void buildChildNodes(NavNode node) {
        List<NavNode> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (NavNode child : children) {
                buildChildNodes(child);
            }
            node.setChild(children);
        }
    }

    // 获取父节点下所有的子节点
    public List getChildNodes(NavNode pnode) {
        List<NavNode> childNodes = new ArrayList<>();
        for (NavNode n : nodes) {
            if (pnode.getId().equals(n.getParentId())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    // 判断是否为根节点
    public boolean rootNode(NavNode node) {
        boolean isRootNode = true;
        for (NavNode n : nodes) {
            if (node.getParentId().equals(n.getId())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    // 获取集合中所有的根节点
    public List<NavNode> getRootNodes() {
        List<NavNode> rootNodes = new ArrayList<>();
        for (NavNode n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }

}
