package org.tonzoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.ProjectMapper;
import org.tonzoc.Model.Company;
import org.tonzoc.Model.Project;
import org.tonzoc.Model.Tender;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/19 0019.
 */
@RestController
@RequestMapping("projects")
public class ProjectController extends BaseApiService {
    @Autowired
    private ProjectMapper projectMapper;

    @RequestMapping("list")
    public ResponseBase list() {
        try {
            List<Project> myList= projectMapper.list();
            for (Project p :myList){
                System.out.println(p.getCompany());
            }
            System.out.println(myList);

            return setResultSuccessWithData(myList);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return setSelectResultError();
        }
    }

    @RequestMapping("add")
    public ResponseBase add(@RequestParam(value = "name", defaultValue = "") String name,
                            @RequestParam(value = "code", defaultValue = "") String code,
                            @RequestParam(value = "provinceId", defaultValue = "") String provinceId,
                            @RequestParam(value = "ownerCompanyId", defaultValue = "") String ownerCompanyId,
                            @RequestParam(value = "length", defaultValue = "0.000") String length,
                            @RequestParam(value = "startSection", defaultValue = "") String startSection,
                            @RequestParam(value = "endSection", defaultValue = "") String endSection,
                            @RequestParam(value = "startDate", defaultValue = "") String startDate,
                            @RequestParam(value = "note", defaultValue = "") String note) {
        try {
            int result = projectMapper.insert(name, code, Long.parseLong(provinceId), Long.parseLong(ownerCompanyId),  new BigDecimal(length), startSection, endSection, startDate, note);
            if (result < 1) {
                // 添加失败
                return setInsertResultError();
            }
            return setResultSuccess("添加成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setInsertResultError();
        }
    }

    @RequestMapping("listPaged")
    public ResponseBase listPaged(@RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "provinceId", defaultValue = "0") String provinceId,
                                  @RequestParam(value = "order", defaultValue = "id") String order,
                                  @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                  @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                  @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {

        try {
            List<WhereParam> whereParams = new ArrayList<>();
            WhereParam whereParam = new WhereParam("p.name", "like", name);
            whereParams.add(whereParam);
            WhereParam whereParam2= new WhereParam("p.provinceId", "=", provinceId);
            whereParams.add(whereParam2);
           if(Long.parseLong(provinceId)==0){
                whereParams.remove(whereParam2);
            }
            SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
                    Integer.parseInt(pageIndex), whereParams);
            System.out.println(order);
            System.out.println(sort);
            List<Project> myList = projectMapper.listPaged(selectParam);
            int total = projectMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }

    }
    //ownerCompanyId的名字
    @RequestMapping("edit")
    public ResponseBase edit(@RequestParam(value = "id", defaultValue = "0") String id,
                             @RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "code", defaultValue = "") String code,
                             @RequestParam(value = "provinceId", defaultValue = "") String provinceId,
                             @RequestParam(value = "ownerCompanyId", defaultValue = "0") String ownerCompanyId,
                             @RequestParam(value = "length", defaultValue = "0.000") String length,
                             @RequestParam(value = "startSection", defaultValue = "") String startSection,
                             @RequestParam(value = "endSection", defaultValue = "") String endSection,
                             @RequestParam(value = "startDate", defaultValue = "") String startDate,
                             @RequestParam(value = "note", defaultValue = "") String note) {
        try {
            System.out.println("update:" + id);
            int result = projectMapper.update(Long.parseLong(id), name, code, Long.parseLong(provinceId), Long.parseLong(ownerCompanyId),  new BigDecimal(length), startSection, endSection, startDate, note);
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
    public ResponseBase delete(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            System.out.println("delete:" + id);
            int result = projectMapper.delete(Long.parseLong(id));
            if(result<1) {
                return setDeleteResultError1();
            }
            if(result==1){
                return setDeleteResultError2();
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
            List<Project> myList = projectMapper.selectById(Long.parseLong(id));
//            if (result < 1) {
//                return setSelectResultError();
//            }
            return setResultSuccessWithData(myList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }

    @RequestMapping("listByProvinceId")
    public ResponseBase selectByProvinceId(@RequestParam(value = "provinceId", defaultValue = "") String provinceId) {
        try {
            System.out.println("selectById:" + provinceId);
            List<Project> myList = projectMapper.selectById(Long.parseLong(provinceId));
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
