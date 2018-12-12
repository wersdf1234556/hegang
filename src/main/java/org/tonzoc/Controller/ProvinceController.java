package org.tonzoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.ProvinceMapper;
import org.tonzoc.Model.Province;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/19 0019.
 */
@RestController
@RequestMapping("province")
public class ProvinceController extends BaseApiService {
    @Autowired
    private ProvinceMapper provinceMapper;
    @RequestMapping("list")
    public ResponseBase list() {
        try {
            List<Province> myList = provinceMapper.list();
            PageList pageList = new PageList(myList.size(), myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }

    }
    @RequestMapping("listPaged")
    public ResponseBase listPaged(@RequestParam(value = "name", defaultValue = "") String name,
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
            System.out.println(order);
            System.out.println(sort);
            List<Province> myList = provinceMapper.listPaged(selectParam);
            int total = provinceMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }
    @RequestMapping("listById")
    public ResponseBase listById(@RequestParam(value = "id", defaultValue = "") String id) {
        try {
            System.out.println("selectById:" + id);
            int result = provinceMapper.listById(Long.parseLong(id));
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
