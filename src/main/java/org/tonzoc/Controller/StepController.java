package org.tonzoc.Controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.MemberBindMapper;
import org.tonzoc.Mapper.StepMapper;
import org.tonzoc.Model.Company;
import org.tonzoc.Model.Step;

@RestController
@RequestMapping("steps")
public class StepController extends BaseApiService {

    @Autowired
    private StepMapper stepMapper;
	@Autowired
	private MemberBindMapper memberBindMapper;

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

//    @RequestMapping("listFirstStep")
//    public ResponseBase listFirstStep() {
//    	try {
//    		List<Step> myList = stepMapper.listFirstStep();
//    		return setResultSuccessWithData(myList);
//		} catch (Exception e) {
//			// TODO: handle exception
//			return setSelectResultError();
//		}
//    }
@RequestMapping("add")
public ResponseBase add(@RequestParam(value = "flowId", defaultValue = "0") String flowId,
						@RequestParam(value = "name", defaultValue = "") String name,
						@RequestParam(value = "nextStepId", defaultValue = "0") String nextStepId) {
	try {
		int result = stepMapper.insert(Long.parseLong(flowId), name, Long.parseLong(nextStepId));
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
	public ResponseBase listPaged(@RequestParam(value = "name",defaultValue = "") String name,
								  @RequestParam(value = "flowId",defaultValue = "") String flowId,
								  @RequestParam(value = "order", defaultValue = "id") String order,
								  @RequestParam(value = "sort", defaultValue = "desc") String sort,
								  @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
								  @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {

		try {
			List<WhereParam> whereParams = new ArrayList<>();
			WhereParam whereParam1 = new WhereParam("s.name", "like", name);
			whereParams.add(whereParam1);
            WhereParam whereParam2 = new WhereParam("s.flowId", "=", flowId);
            whereParams.add(whereParam2);
			SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
					Integer.parseInt(pageIndex), whereParams);
			System.out.println(order);
			System.out.println(sort);
			List<Step> myList = memberBindMapper.listPaged(selectParam);
			int total = memberBindMapper.countPaged(selectParam);
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
			int result = stepMapper.delete(Long.parseLong(id));
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
							 @RequestParam(value = "flowId", defaultValue = "0") String flowId,
							 @RequestParam(value = "name", defaultValue = "") String name,
							 @RequestParam(value = "nextStepId", defaultValue = "") String nextStepId) {
		try {
			System.out.println("update:" + id);
			int result = stepMapper.update(Long.parseLong(id), Long.parseLong(flowId), name,Long.parseLong(nextStepId));
			if(result<1) {
				return setUpdateResultError();
			}
			return setResultSuccess("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			return setUpdateResultError();
		}

	}
	@RequestMapping("listByFlowId")
    public ResponseBase listByFlowId(@RequestParam(value = "flowId", defaultValue = "") String flowId) {
        try {
            System.out.println("listByFlowId:" + flowId);
            Step myList = stepMapper.selectByFlowId(Long.parseLong(flowId));
			return setResultSuccessWithData(myList);
        } catch (Exception e) {
        	System.out.println(e);
            // TODO: handle exception
            return setSelectResultError();
        }
    }
//    @RequestMapping("listById")
//    public ResponseBase listById(@RequestParam(value = "id", defaultValue = "") String id) {
//        try {
//            System.out.println("listById:" + id);
//            Step step = stepMapper.nextStep(Long.parseLong(id));
//            return setResultSuccessWithData(step);
////            PageList pageList = new PageList(myList.size(), myList);
////            return setResultSuccessWithData(pageList);
//        } catch (Exception e) {
//        	System.out.println(e);
//            // TODO: handle exception
//            return setSelectResultError();
//        }
//    }







}
