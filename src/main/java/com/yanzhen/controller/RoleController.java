package com.yanzhen.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.yanzhen.po.Menu;
import com.yanzhen.po.Role;
import com.yanzhen.service.IRoleService;
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
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有的角色信息
     */
    @RequestMapping("role/roleAll")
    @ResponseBody
    public JsonObject queryRoleAll(){
        JsonObject object=new JsonObject();
        List<Role> list=roleService.queryRoleAll();
        System.out.println(list);
        object.setCode(200);
        object.setCount((long) list.size());
        object.setData(list);
        object.setMsg("ok");
        return object;
    }

    /**
     * 添加功能实现
     */
    @RequestMapping("role/addRoleSubmit")
    @ResponseBody
    public R addRole(Role role){
        roleService.addRole(role);
        return R.ok();
    }


    /**
     * 删除功能
     */
    @RequestMapping("role/deleteRoleById")
    @ResponseBody
    public R deleteRoleByID(Integer id){
        roleService.deleteRoleById(id);
        return R.ok();
    }


    /**
     * 添加关联角色权限
     */
    @ResponseBody
    @RequestMapping("role/addPremSubmit")
    public R addPermSubmit(int [] ids,int roleId){
        //调用service层实现关联工作
        roleService.insertForEach(ids,roleId);
        return R.ok();
    }

    /**
     * 进行角色的修改功能
     */
    @ResponseBody
    @RequestMapping("role/updateRoleSubmit")
    public R updateRoleById(Role role){
         role.setUpdateTime(new Date());
         roleService.updateRole(role);
         return R.ok();
    }

    /**
     * 角色树查询
     */
    @ResponseBody
    @RequestMapping("/queryRoleTree")
     public Object queryRoleTree(){
         return roleService.queryRoleTree();
     }




    /**
     * 角色主页跳转
     */
   @RequestMapping("/role")
   public String roleMenu(){
       return "pages/role";
   }

    /**
     * 添加转发页面
     */
    @RequestMapping("/addRole")
    public String addRole(Model model,Integer parentId){
         model.addAttribute("parentId",parentId);
         return "pages/addRole";
    }



    /**
     * 添加关联权限跳转页面
     * @param model
     * @param id  角色id
     * @return
     */
    @RequestMapping("/addPerm")
    public String addPerm(Model model,int id){
        model.addAttribute("roleId",id);
        return "pages/addPram";
    }

    /**
     * 进行角色页面的修改跳转
     */
    @RequestMapping("role/queryroleByID")
    public String updateRole(Model model,int id){
       //根据id查询角色信息 返回前台
        Role role=roleService.queryRoleById(id);
        model.addAttribute("role",role);
        return "pages/updateRole";
    }



}
