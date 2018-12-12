package org.tonzoc.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.BaseApiService;
import org.tonzoc.Common.PageList;
import org.tonzoc.Common.ResponseBase;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.WhereParam;
import org.tonzoc.Mapper.TenderMapper;
import org.tonzoc.Model.Tender;

@RestController
@RequestMapping("tenders")
public class TenderController extends BaseApiService {

    @Autowired
    private TenderMapper tenderMapper;

    @RequestMapping("list")
    public ResponseBase list() {
    	try {
    		List<Tender> myList = tenderMapper.list();
    		PageList pageList = new PageList(myList.size(), myList);
    		return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
    }

    @RequestMapping("add")
    public ResponseBase add(@RequestParam(value = "name", defaultValue = "") String name,
							@RequestParam(value = "startSection", defaultValue = "") String startSection,
							@RequestParam(value = "projectId", defaultValue = "") String projectId,
                            @RequestParam(value = "code", defaultValue = "") String code,
                            @RequestParam(value = "endSection", defaultValue = "") String endSection,
							@RequestParam(value = "length", defaultValue = "") String length,
                            @RequestParam(value = "startDate", defaultValue = "") String startDate,
							@RequestParam(value = "endDate", defaultValue = "") String endDate,
                            @RequestParam(value = "ownerCompany", defaultValue = "") String ownerCompany,
                            @RequestParam(value = "contractCompany", defaultValue = "") String contractCompany,
                            @RequestParam(value = "contractTel", defaultValue = "") String contractTel,
                            @RequestParam(value = "superviseCompany", defaultValue = "") String superviseCompany,
                            @RequestParam(value = "superviseTel", defaultValue = "") String superviseTel,
                            @RequestParam(value = "testCompany", defaultValue = "") String testCompany,
                            @RequestParam(value = "defaultApprovalDate", defaultValue = "") String defaultApprovalDate,
                            @RequestParam(value = "note", defaultValue = "") String note) {
    	try {
    		int result = tenderMapper.insert(name, Long.parseLong(projectId), code, startSection, endSection, new BigDecimal(length), startDate, endDate, ownerCompany,
                    contractCompany, contractTel, superviseCompany, superviseTel, testCompany, defaultApprovalDate, note);
    		if (result < 1) {
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
                                @RequestParam(value = "order", defaultValue = "id") String order,
                                @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {
      
    	try {
			List<WhereParam> whereParams = new ArrayList<>();
			WhereParam whereParam = new WhereParam("t.name", "like", name);
			whereParams.add(whereParam);
			SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
					Integer.parseInt(pageIndex), whereParams);
			System.out.println(order);
			System.out.println(sort);
            List<Tender> myList = tenderMapper.listPaged(selectParam);
            int total = tenderMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
			return setResultSuccessWithData(pageList);
			
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
    }

    @RequestMapping("delete")
    public ResponseBase delete(@RequestParam(value = "id", defaultValue = "") String id) {
    	try {
			System.out.println("delete:" + id);
			int result = tenderMapper.delete(Long.parseLong(id));
			if(result<1) {
				return setDeleteResultError();
			}
			return setResultSuccess("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setDeleteResultError();
		}
    	
    }

    @RequestMapping("edit")
    public ResponseBase edit(@RequestParam(value = "id", defaultValue = "") String id,
							 @RequestParam(value = "name", defaultValue = "") String name,
							 @RequestParam(value = "projectId", defaultValue = "") String projectId,
							 @RequestParam(value = "code", defaultValue = "") String code,
                             @RequestParam(value = "startSection", defaultValue = "") String startSection,
                             @RequestParam(value = "endSection", defaultValue = "") String endSection,
                             @RequestParam(value = "length", defaultValue = "") String length,
                             @RequestParam(value = "startDate", defaultValue = "") String startDate,
                             @RequestParam(value = "endDate", defaultValue = "") String endDate,
                             @RequestParam(value = "ownerCompany", defaultValue = "") String ownerCompany,
                             @RequestParam(value = "contractCompany", defaultValue = "") String contractCompany,
                             @RequestParam(value = "contractTel", defaultValue = "") String contractTel,
                             @RequestParam(value = "superviseCompany", defaultValue = "") String superviseCompany,
                             @RequestParam(value = "superviseTel", defaultValue = "") String superviseTel,
                             @RequestParam(value = "testCompany", defaultValue = "") String testCompany,
                             @RequestParam(value = "defaultApprovalDate", defaultValue = "") String defaultApprovalDate,
                             @RequestParam(value = "note", defaultValue = "") String note) {
    	try {
			System.out.println("update:" + id);
			int result = tenderMapper.update(Long.parseLong(id), name, Long.parseLong(projectId), code, startSection, endSection, new BigDecimal(length), startDate, endDate, ownerCompany,
	                contractCompany, contractTel, superviseCompany, superviseTel, testCompany, defaultApprovalDate, note);
			if(result<1) {
				return setUpdateResultError();
			}
			return setResultSuccess("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setUpdateResultError();
		}
    	
    }
}
