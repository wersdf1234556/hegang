package org.tonzoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.BasicMapper;
import org.tonzoc.Model.Basic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/11/11.
 */
@RestController
@RequestMapping("basic")
public class BasicController extends BaseApiService{

    @Autowired
    private BasicMapper basicMapper;

    //添加
    @RequestMapping("add")
    public ResponseBase add(@RequestParam(value = "name", defaultValue = "") String name,
                            @RequestParam(value = "companyid", defaultValue = "0") String companyid,
                            @RequestParam(value = "length", defaultValue = "0.000") String length,
                            @RequestParam(value = "startSection", defaultValue = "") String startSection,
                            @RequestParam(value = "endSection", defaultValue = "") String endSection,
                            @RequestParam(value = "startDate", defaultValue = "") String startDate,
                            @RequestParam(value = "materialRatio", defaultValue = "0.000") String materialRatio,
                            @RequestParam(value = "materialPeriod", defaultValue = "0") String materialPeriod,
                            @RequestParam(value = "startRatio", defaultValue = "0.000") String startRatio,
                            @RequestParam(value = "startPeriod", defaultValue = "0") String startPeriod,
                            @RequestParam(value = "retentionRatio", defaultValue = "0.000") String retentionRatio,
                            @RequestParam(value = "retentionPeriod", defaultValue = "0") String retentionPeriod,
                            @RequestParam(value = "note", defaultValue = "") String note) {
        try {
            int result = basicMapper.addBasic(name, Long.parseLong(companyid),new BigDecimal(length), startSection, endSection,
                    startDate,new BigDecimal(materialRatio),Long.parseLong(materialPeriod),new BigDecimal(startRatio), Long.parseLong(startPeriod),
                    new BigDecimal(retentionRatio),Long.parseLong(retentionPeriod),note);
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
    //查询,分页
    @RequestMapping("listPaged")
    public ResponseBase selectBasicAll(@RequestParam(value = "name", defaultValue = "") String name,
                                       @RequestParam(value = "companyid", defaultValue = "") String companyid,
                                       @RequestParam(value = "order", defaultValue = "id") String order,
                                       @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                       @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                       @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {

        try {
            List<WhereParam> whereParams = new ArrayList<>();
            WhereParam whereParam1 = new WhereParam("name", "like", name);
            whereParams.add(whereParam1);
            WhereParam whereParam2 = new WhereParam("companyid", "=", companyid);
            whereParams.add(whereParam2);
            SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
                    Integer.parseInt(pageIndex), whereParams);
            System.out.println(order);
            System.out.println(sort);
            List<Basic> myList = basicMapper.listPaged(selectParam);
            int total = basicMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError1();
        }
    }
}