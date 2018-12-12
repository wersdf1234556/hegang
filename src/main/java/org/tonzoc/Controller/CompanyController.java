package org.tonzoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.CompanyMapper;
import org.tonzoc.Model.Company;
import org.tonzoc.Model.Module;
import org.tonzoc.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/11 0011.
 */
@RestController
@RequestMapping("company")
public class CompanyController extends BaseApiService {
    @Autowired
    private CompanyMapper companyMapper;
    @RequestMapping("listByName")
    public ResponseBase listByName(@RequestParam(value = "name", defaultValue = "") String name,
                                   @RequestParam(value = "order", defaultValue = "id") String order,
                                   @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                   @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                   @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {
        try {
            List<WhereParam> whereParams = new ArrayList<>();
            WhereParam whereParam = new WhereParam("name", "like", name);
            whereParams.add(whereParam);
            SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
                    Integer.parseInt(pageIndex), whereParams);
            System.out.println("listByName:" + name);
            List<Company> myList = companyMapper.listByName(selectParam);
            PageList pageList = new PageList(myList.size(), myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }

    }
    @RequestMapping("listPaged")
    public ResponseBase listPaged(@RequestParam(value = "id", defaultValue = "") String id,
                                  @RequestParam(value = "order", defaultValue = "id") String order,
                                  @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                  @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                  @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {

        try {
            List<WhereParam> whereParams = new ArrayList<>();
            WhereParam whereParam = new WhereParam("id", "=", id);
            whereParams.add(whereParam);
            SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
                    Integer.parseInt(pageIndex), whereParams);
            System.out.println(order);
            System.out.println(sort);
            List<Company> myList = companyMapper.listPaged(selectParam);
            int total = companyMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }
    @RequestMapping("add")
    public ResponseBase add(@RequestParam(value = "name", defaultValue = "") String name,
                            @RequestParam(value = "contact", defaultValue = "") String contact,
                            @RequestParam(value = "tel", defaultValue = "") String tel) {
        try {
            int result = companyMapper.insert(name, contact, tel);
            if(result<1) {
                return setInsertResultError();
            }
            if(result==1){
                return setInsertResultError1();
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
                             @RequestParam(value = "contact", defaultValue = "") String contact,
                             @RequestParam(value = "tel", defaultValue = "") String tel) {
        try {
            System.out.println("update:" + id);
            int result = companyMapper.update(Long.parseLong(id), name, contact, tel);
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
            int result = companyMapper.delete(Long.parseLong(id));
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
    public ResponseBase listById(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            System.out.println("listById:" + id);
            int result = companyMapper.listById(Long.parseLong(id));
            if (result < 1) {
                return setSelectResultError();
            }
            return setResultSuccess("查询成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }





}
