package com.yanzhen.controller;

import com.yanzhen.po.Dept;
import com.yanzhen.service.IDeptService;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
@Controller
public class DeptController {
    @Autowired
    private IDeptService deptService;
    /**
     * 查询所有的部门信息
     */
    @RequestMapping("dept/deptAll")
    @ResponseBody
    public JsonObject queryDeptAll(){
        List<Dept> list= deptService.queryDeptAll();
        //创建返回值对象信息
        JsonObject object=new JsonObject();
        object.setCode(0);
        object.setMsg("ok");
        object.setCount((long) list.size());
        object.setData(list);
        System.out.println(list);
        return object;
    }

    @RequestMapping("dept/addDeptSubmit")
    @ResponseBody
    public R addDept(Dept dept){
        //创建记录时间
        dept.setCreateTime(new Date());
        deptService.addDept(dept);
        return R.ok();
    }

    /**
     * 修改操作
     */
    @ResponseBody
    @RequestMapping("dept/updateDeptSubmit")
    public R updateDept(Dept dept){
        deptService.updateDept(dept);
        return R.ok();
    }

    /**
     * 根据id删除部门信息
     */
    @RequestMapping("dept/deleteDeptByID")
    @ResponseBody
    public R deleteDeptByID(int id){
        deptService.deleteDeptById(id);
        return R.ok();
    }


    /**
     * 部门树结构的渲染工作
     */
    @RequestMapping("queryDeptTree")
    @ResponseBody
    public Object queryDeptTree(){
       return deptService.queryDeptTree();
    }


    /******
     * 页面的渲染使用
     */
    @RequestMapping("/dept")
    public String deptIndex(){
       return "pages/dept";
    }

    @RequestMapping("addDept")
    public String addDept(Integer type, Integer parentId, Model model){
        model.addAttribute("type",type+1);
        model.addAttribute("parentId",parentId);
        return "pages/addDept";
    }

}
