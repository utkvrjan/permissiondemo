package com.yanzhen.controller;

import com.yanzhen.po.*;
import com.yanzhen.service.IMenuService;
import com.yanzhen.util.JsonObject;
import com.yanzhen.util.R;
import com.yanzhen.util.TreeBuilder;
import com.yanzhen.util.TreeBuilder2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private TreeBuilder treeBuilder;
    @Autowired
    private TreeBuilder2 treeBuilder2;
    /**
     * 查询所有的菜单信息
     */
    @ResponseBody
    @RequestMapping("menu/menuAll")
    public JsonObject queryMenuAll(){
        JsonObject object=new JsonObject();
        List<Menu> list=menuService.queryMenuAll();
        System.out.println(list);
        object.setCode(200);
        object.setCount((long) list.size());
        object.setData(list);
        object.setMsg("ok");
        return object;
    }


    /**
     * 菜单添加操作
     */
    @RequestMapping("menu/addMenuSubmit")
    @ResponseBody
    public R addMenuSubmit(Menu menu){
        menu.setCreateTime(new Date());
        menuService.addMenu(menu);
        return R.ok();
    }

    /**
     * 菜单的删除
     */
    @RequestMapping("menu/deleteMenuById")
    @ResponseBody
    public R deleteMenById(Integer id){
        menuService.deleteMenuById(id);
        return R.ok();
    }

    /**
     * 实现修改操作
     */
    @RequestMapping("menu/updateMenuSubmit")
    @ResponseBody
    public R updateMenuSubmit(Menu menu){
        menuService.updateMenuSubmit(menu);
        return R.ok();
    }

    /**
     * 获取树状结构数据
     */
    @RequestMapping("menu/queryMenuTree")
    @ResponseBody
    public Object queryMenuTree(int id){
          //查询Node数据
        List<Node> list=menuService.queryMenuTree();
        System.out.println(list);
          //根据角色查询关联到的菜单
        List<RoleMenu> roleMenus=menuService.queryMenuByRoleId(id);
          //渲染已经选中内容
         for(RoleMenu rm:roleMenus){
                //获取当前对象的id
                int menuId=rm.getMenuId();
                for(Node node:list){
                    if(node.getId()==menuId){
                        //判断是否有子节点
                        List<Menu> menusList=  menuService.isChild(menuId);
                        if(menusList.size()==0){//如果没有子节点
                            node.setChecked(true);//设置为true 选中状态
                        }
                    }
                }
          }
        /*for(Node node:list){
            node.setChecked(false);
            for(RoleMenu rm:roleMenus){
                int menuId=rm.getMenuId();
                if(node.getId()==menuId){
                    node.setChecked(true);
                }
            }
        }*/
          //组装树结构
              String result =treeBuilder.buildTree(list);
        System.out.println("===============");
        System.out.println();
          return result;
    }



    /**
     * 根据菜单id查询菜单详细信息
     */
    @RequestMapping("menu/queryMenuByID")
    public String queryMenuByID(Integer id,Model model){
        //根据id查询记录详细信息
        Menu menu=menuService.queryMenuByID(id);
        //逐个设定值信息
        model.addAttribute("menu",menu);
        return "pages/updateMenu";
    }



    /***
     * 静态页面的跳转
     */
    @RequestMapping("/menu")
    public String menuIndex(){
        return "pages/menu";
    }

    /**
     * 添加页面
     */
    @RequestMapping("/addMenu")
    public String addMenu(Model model, int type, int parentId){
        System.out.println(type);
        //如果是头部添加
        if(type==-1){
            type=0;
            parentId=-1;
        }else{
            type=type+1;
        }
        model.addAttribute("type",type);
        model.addAttribute("parentId",parentId);
        return "pages/addMenu";
    }


    /**
     * 根据用户id查询导航树
     */
    @RequestMapping("menuTree")
    @ResponseBody
    public Object MenuNavTree(HttpSession session){
        //获取登录用户对象
        User user= (User) session.getAttribute("user");
        //根据id获取菜单
        List<NavNode> obj=menuService.queryNavNodeListTree(user.getId());

        //拼接数据
        Map map=new HashMap<>();
        //设置home
        Map home=new HashMap<>();
        home.put("title","系统管理");
        home.put("href","pages/welcome.html");
        map.put("homeInfo",home);

        //logoInfo
        Map logo=new HashMap<>();
        logo.put("title","后台管理");
        logo.put("image","images/logo.png");
        logo.put("href","");
        map.put("logoInfo",logo);

        //设置导航数据
        //1 转成树结构
        obj= treeBuilder2.buildTree(obj);
        map.put("menuInfo",obj);
        System.out.println(map);

        return map;
    }

}
