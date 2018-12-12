package org.tonzoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.MemberBindMapper;
import org.tonzoc.Mapper.ModuleMapper;
import org.tonzoc.Model.MemberBind;
import org.tonzoc.Model.Module;
import org.tonzoc.Model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/15.
 */
@RestController
@RequestMapping("memberbind")
public class MemberBindController extends BaseApiService{

    @Autowired
    private MemberBindMapper memberBindMapper;

    //查询全部
    @RequestMapping("list")
    public ResponseBase list() {
        try {
            List<Step> myList = memberBindMapper.list();
            PageList pageList = new PageList(myList.size(), myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
            return setSelectResultError1();
        }
    }

    
   
    //分页查询
    @RequestMapping("listPaged")
    public ResponseBase listPaged(@RequestParam(value = "name",  required = false,defaultValue = "") String name,
                                  @RequestParam(value = "stepId", defaultValue = "") String stepId,
                                  @RequestParam(value = "userId", defaultValue = "") String userId,
                                  @RequestParam(value = "order", defaultValue = "id") String order,
                                  @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                  @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                  @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {

        try {
            List<WhereParam> whereParams = new ArrayList<>();
            WhereParam whereParam1 = new WhereParam("name", "like", name);
            whereParams.add(whereParam1);
            WhereParam whereParam2 = new WhereParam("userId", "=", userId);
            whereParams.add(whereParam2);
            SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize), Integer.parseInt(pageIndex), whereParams);
            System.out.println(order);
            System.out.println(sort);
            List<Step> myList = memberBindMapper.listPaged(selectParam);
            int total = memberBindMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
            return setSelectResultError1();
        }
    }

    //根据id查询一条
    @RequestMapping("listById")
    public ResponseBase listById(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            System.out.println("selectById:" + id);
            List<MemberBind> myList = memberBindMapper.getById(Long.parseLong(id));
            PageList pageList = new PageList(myList.size(), myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
        	e.printStackTrace();
            return setSelectResultError1();
        }
    }

    //根据stepId进行查询
    @RequestMapping("listByStepId")
    public ResponseBase getByStepId(@RequestParam(value = "stepId", defaultValue = "") String stepId) {
        try {
            System.out.println("selectByStepId:" + stepId);
            List<MemberBind> myList = memberBindMapper.getByStepId(Long.parseLong(stepId));
            PageList pageList = new PageList(myList.size(), myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
        	e.printStackTrace();
            return setSelectResultError1();
        }
    }

    //添加
    @RequestMapping("save")
    public ResponseBase save(@RequestParam(value = "stepId", defaultValue = "") String stepId,
                              @RequestParam(value = "userId", defaultValue = "") String userId) {
        try {
            int result = memberBindMapper.save(Long.parseLong(stepId),Long.parseLong(userId));
            if (result < 1) {
                // 添加失败
                return setInsertResultError();
            }
            return setResultSuccess("添加成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setInsertResultError2();
        }
    }

    //修改
    @RequestMapping("update")
    public ResponseBase update(@RequestParam(value = "stepId", defaultValue = "") String stepId,
                               @RequestParam(value = "userId", defaultValue = "") String userId) {
        try {
            int result = memberBindMapper.delete(Long.parseLong(stepId));
//            if (result < 1) {
//                //删除失败
//                return setDeleteResultError();
//            }
//            return setResultSuccess("删除成功");
            int result1 = memberBindMapper.save(Long.parseLong(stepId),Long.parseLong(userId));
            if (result1 < 1) {
                //修改失败
                return setUpdateResultError();
            }
            return setResultSuccess("修改成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setUpdateResultError();
        }
    }

    //删除
    @RequestMapping("delete")
    public ResponseBase delete(@RequestParam(value = "stepId", defaultValue = "") String stepId) {
        try {
            System.out.println("delete:" + stepId);
            int result = memberBindMapper.delete(Long.parseLong(stepId));
            if (result < 1) {
                //删除失败
                return setDeleteResultError();
            }
            return setResultSuccess("删除成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setDeleteResultError();
        }

    }

}