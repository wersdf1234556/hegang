package org.tonzoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.FlowMapper;
import org.tonzoc.Model.Flow;
import org.tonzoc.Model.Project;
import org.tonzoc.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
@RestController
@RequestMapping("flow")
public class FlowController extends BaseApiService {
    @Autowired
    private FlowMapper flowMapper;

    @RequestMapping("list")
    public ResponseBase list() {
        try {
            List<Flow> myList = flowMapper.list();
            PageList pageList = new PageList(myList.size(), myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }

    }
    @RequestMapping("listPaged")
    public ResponseBase listPaged(@RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "projectId", defaultValue = "0") String projectId,
                                  @RequestParam(value = "order", defaultValue = "id") String order,
                                  @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                  @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                  @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {

        try {
            List<WhereParam> whereParams = new ArrayList<>();
            WhereParam whereParam = new WhereParam("f.name", "like", name);
            whereParams.add(whereParam);
            WhereParam whereParam1 = new WhereParam("f.projectId", "=", projectId);
            whereParams.add(whereParam1);
            if(Long.parseLong(projectId)==0){
                whereParams.remove(whereParam1);
            }

            SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
                    Integer.parseInt(pageIndex), whereParams);
            System.out.println(order);
            System.out.println(sort);
            List<Flow> myList = flowMapper.listPaged(selectParam);
            int total = flowMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }
    @RequestMapping("add")
    public ResponseBase add(@RequestParam(value = "name", defaultValue = "") String name,
                            @RequestParam(value = "projectId", defaultValue = "") String projectId
    ) {
        try {
            int result = flowMapper.insert(name,Long.parseLong(projectId));
            if(result<1) {
                return setInsertResultError();
            }
            return setResultSuccess("添加成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setInsertResultError();
        }
    }
    @RequestMapping("edit")
    public ResponseBase edit(@RequestParam(value = "id", defaultValue = "") String id,
                             @RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "projectId", defaultValue = "") String projectId) {
        try {
            System.out.println("update:" + id);
            int result = flowMapper.update(Long.parseLong(id), name, Long.parseLong(projectId));
            if(result<1) {
                return setUpdateResultError();
            }
            return setResultSuccess("修改成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setUpdateResultError();
        }
    }
    @RequestMapping("delete")
    public ResponseBase delete(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            System.out.println("delete:" + id);
            int result = flowMapper.delete(Long.parseLong(id));
            if(result<1) {
                return setDeleteResultError();
            }
            return setResultSuccess("删除成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setDeleteResultError();
        }
    }
    @RequestMapping("listById")
    public ResponseBase selectById(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            System.out.println("selectById:" + id);
            Flow flow = flowMapper.selectById(Long.parseLong(id));
            return setResultSuccessWithData(flow);
//            if (result < 1) {
//                return setSelectResultError();
//            }
//            return setResultSuccess("查询成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }
    @RequestMapping("listByProjectId")
    public ResponseBase selectByProjectId(@RequestParam(value = "projectId", defaultValue = "") String projectId) {
        try {
            System.out.println("selectByProjectId:" + projectId);
            List<Flow> myList = flowMapper.selectByProjectId(Long.parseLong(projectId));
//            if (result < 1) {
//                return setSelectResultError();
//            }
            return setResultSuccessWithData(myList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }
}
