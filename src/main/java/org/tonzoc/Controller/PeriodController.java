package org.tonzoc.Controller;

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
import org.tonzoc.Mapper.PeriodMapper;
import org.tonzoc.Model.Period;
import org.tonzoc.Model.Project;

@RestController
@RequestMapping("periods")
public class PeriodController extends BaseApiService {

	@Autowired
	private PeriodMapper periodMapper;

	@RequestMapping("list")
	public ResponseBase list() {
		try {
			List<Period> myList = periodMapper.list();
			PageList pageList = new PageList(myList.size(), myList);
			return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}

	@RequestMapping("add")
	public ResponseBase add(@RequestParam(value = "name", defaultValue = "") String name,
							@RequestParam(value = "projectId", defaultValue = "") String projectId,
			@RequestParam(value = "startDate", defaultValue = "") String startDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate) {
		try {
			int result = periodMapper.insert(name,Long.parseLong(projectId), startDate, endDate);
			if(result<1) {
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
			WhereParam whereParam = new WhereParam("name", "like", name);
			whereParams.add(whereParam);
			SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
					Integer.parseInt(pageIndex), whereParams);
			System.out.println(order);
			System.out.println(sort);
			List<Period> myList = periodMapper.listPaged(selectParam);
			int total = periodMapper.countPaged(selectParam);
			PageList pageList = new PageList(total, myList);
			return setResultSuccessWithData(pageList);
		} catch (Exception e) {
			// TODO: handle exception
			return setSelectResultError();
		}
	}
    @RequestMapping("listByProjectId")
    public ResponseBase listByProjectId(@RequestParam(value = "projectId", defaultValue = "") String projectId) {
        try {
            System.out.println("selectById:" + projectId);
            List<Period> myList = periodMapper.listByProjectId(Long.parseLong(projectId));
//            if (result < 1) {
//                return setSelectResultError();
//            }
            return setResultSuccessWithData(myList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }

	@RequestMapping("delete")
	public ResponseBase delete(@RequestParam(value = "id", defaultValue = "") String id) {
		try {
			System.out.println("delete:" + id);
			int result = periodMapper.delete(Long.parseLong(id));
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
			@RequestParam(value = "startDate", defaultValue = "") String startDate,
			@RequestParam(value = "endDate", defaultValue = "") String endDate) {
		try {
			System.out.println("update:" + id);
			int result = periodMapper.update(Long.parseLong(id), name,Long.parseLong(projectId), startDate, endDate);
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
