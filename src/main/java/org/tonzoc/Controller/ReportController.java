package org.tonzoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.BaseApiService;
import org.tonzoc.Common.ResponseBase;
import org.tonzoc.Mapper.ReportMapper;
import org.tonzoc.Model.Report;


import java.util.List;

/**
 * Created by Administrator on 2018/12/11 0011.
 */
@RestController
@RequestMapping("reports")
public class ReportController extends BaseApiService {

    @Autowired
    private ReportMapper reportMapper;

    @RequestMapping("list")
    public ResponseBase list() {
        try {
            List<Report> myList = reportMapper.list();
            System.out.println(myList);
            return setResultSuccessWithData(myList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }

    }

}
