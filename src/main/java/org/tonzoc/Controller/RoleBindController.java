package org.tonzoc.Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.ModuleMapper;
import org.tonzoc.Mapper.RoleBindMapper;
import org.tonzoc.Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/20 0020.
 */
@RestController
@RequestMapping("rolebind")
public class RoleBindController extends BaseApiService {
    @Autowired
    private RoleBindMapper roleBindMapper;
    @Autowired
    private ModuleMapper moduleMapper;


    @RequestMapping("list")
    public ResponseBase list() {
        try {
            List<Module> myList = roleBindMapper.list();
            PageList pageList = new PageList(myList.size(), myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return setSelectResultError();
        }

    }
    @RequestMapping("listPaged")
    public ResponseBase listPaged(@RequestParam(value = "roleId", defaultValue = "") String roleId,
                                  @RequestParam(value = "moduleId", defaultValue = "") String moduleId,
                                  @RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "order", defaultValue = "id") String order,
                                  @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                  @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                  @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {
        try {
            List<WhereParam> whereParams = new ArrayList<>();
            WhereParam whereParam1 = new WhereParam("rb.roleId", "=", roleId);
            whereParams.add(whereParam1);
            WhereParam whereParam2 = new WhereParam("rb.moduleId", "=", moduleId);
            whereParams.add(whereParam2);
            WhereParam whereParam3 = new WhereParam("name", "like", name);
            whereParams.add(whereParam3);
            SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
                    Integer.parseInt(pageIndex), whereParams);
            System.out.println(order);
            System.out.println(sort);
            List<Module> myList = roleBindMapper.listPaged(selectParam);
            int total = roleBindMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }
    
    @RequestMapping("add")
    public ResponseBase add(@RequestParam(value = "roleId", defaultValue = "") String roleId,
                            @RequestParam(value = "moduleId", defaultValue = "") String moduleId) {
        try {
            int result = roleBindMapper.insert(Long.parseLong(roleId), Long.parseLong(moduleId));
            if(result<1) {
                return setInsertResultError();
            }
           
            return setResultSuccess("添加成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setInsertResultError();
        }
    }
    //批量
    @RequestMapping("batchUpdate")
    public ResponseBase update(@RequestParam(value = "roleId", defaultValue = "") String roleId,
                               @RequestParam(value = "moduleCommaList", defaultValue = "") String moduleCommaList) {
        try {
            String[] modules=moduleCommaList.split(",");
            System.out.println(modules);
            int result = roleBindMapper.delete(Long.parseLong(roleId));
            //取出所获的moduleId
            for (int i = 0; i < modules.length; i++) {
                int result1 = roleBindMapper.insert(Long.parseLong(roleId),Long.parseLong(modules[i]));
                System.out.println(result1);
                if (result1 < 1) {
                    //修改失败
                    return setUpdateResultError();
                }
            }
            return setResultSuccess("修改成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setUpdateResultError();
        }
    }

    @RequestMapping("edit")
    public ResponseBase edit(@RequestParam(value = "id", defaultValue = "") String id,
                             @RequestParam(value = "roleId", defaultValue = "") String roleId,
                             @RequestParam(value = "moduleId", defaultValue = "") String moduleId) {
        try {
            System.out.println("update:" + id);
            int result = roleBindMapper.update(Long.parseLong(id), Long.parseLong(roleId), Long.parseLong(moduleId));
            if(result<1) {
                return setUpdateResultError1();
            }
            return setResultSuccess("修改成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setUpdateResultError1();
        }
    }

    @RequestMapping("delete")
    public ResponseBase delete(@RequestParam(value = "roleId", defaultValue = "") String roleId) {
        try {
            System.out.println("delete:" + roleId);
            int result = roleBindMapper.delete(Long.parseLong(roleId));
            if(result<1) {
                return setDeleteResultError1();
            }
            return setResultSuccess("删除成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setDeleteResultError();
        }
    }
    //根据角色id查询模块
    @RequestMapping("listModulesByRoleId")
    public ResponseBase selectById(@RequestParam(value = "roleId", defaultValue = "") String roleId) {
        try {
            System.out.println("selectById:" + roleId);
            List<Module> myList = roleBindMapper.selectModulesByRoleId(Long.parseLong(roleId));
//            for (Module module : myList) {
//                module.setChildren(moduleMapper.listByParentId(module.getId()));
//            }
//            PageList pageList = new PageList(myList.size(), myList);
//            Map<String, Object> map = new HashMap<>();
//            List<Module> modules =roleBindMapper.menuTree();
//            map.put("modules",modules);
//            List<Long> getMenusByRid = roleBindMapper.getMenusByRid(Long.parseLong(roleId));
//            map.put("moduleId",getMenusByRid);
//            return setResultSuccessWithData(map);
            return setResultSuccessWithData(myList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }


}
