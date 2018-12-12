package org.tonzoc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tonzoc.Common.*;
import org.tonzoc.Mapper.*;
import org.tonzoc.Model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
@RestController
@RequestMapping("flowPaths")
public class FlowPathsController extends BaseApiService {
    @Autowired
    private FlowPathsMapper flowPathsMapper;

    @Autowired
    private StepMapper stepMapper;

    @Autowired
    private MemberBindMapper memberBindMapper;
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private A15Mapper a15Mapper;
	//获取下一步
    @RequestMapping("listNextStep")
    public ResponseBase getNextStep(@RequestParam(value = "stepId", defaultValue = "") String stepId
    ) {
//        Flow flow = new Flow();
//        int result = flowMapper.insertFlow(flow);
//        System.out.println(flowId);
//    	Step step = stepMapper.selectByFlowId(Long.parseLong(flowId));
//        System.out.println(flowId);
//    	long stepId=step.getId();
//    	System.out.println(stepId);
//    	Step step1 =stepMapper.selectById(stepId);
        Step step = stepMapper.selectById(Long.parseLong(stepId));
        long nextStepId = step.getNextStepId();
        if(nextStepId==0) {
            return setResultError(100,"没有下一步了");
        }
        Step nextStep = stepMapper.selectById(nextStepId);
        return setResultSuccessWithData(nextStep);
    }


    //获取下一步的审批人
    @RequestMapping("listNextStepUser")
    public ResponseBase getNextStepUser(@RequestParam(value = "stepId", defaultValue = "") String stepId
    ) {
//        Flow flow = new Flow();
//        int result = flowMapper.insertFlow(flow);
        Step step = stepMapper.selectById(Long.parseLong(stepId));
        long nextStepId = step.getNextStepId();
    	if(nextStepId==0) {
   		 return setResultError(100,"没有下一步了");
      	}
    	User user = memberBindMapper.selectUser(nextStepId);
    	return setResultSuccessWithData(user);
    }
    //提交
    @RequestMapping("add")
    public ResponseBase add(
            @RequestParam(value = "a15Id", defaultValue = "") String a15Id,
            @RequestParam(value = "stepId", defaultValue = "") String stepId,
            @RequestParam(value = "userId", defaultValue = "") String userId,
            @RequestParam(value = "nextStepId", defaultValue = "") String nextStepId,
            @RequestParam(value = "nextUserId", defaultValue = "") String nextUserId
    ) {
        try {
        	FlowPaths flowPaths = new FlowPaths();
            flowPaths.setA15Id(Long.parseLong(a15Id));
        	flowPaths.setStepId(Long.parseLong(stepId));
        	flowPaths.setCurrentUserId(Long.parseLong(userId));
        	flowPaths.setStatus("completed");
        	Timestamp d = new Timestamp(System.currentTimeMillis());
        	flowPaths.setCreatedAt(d);
            int result = flowPathsMapper.insert(flowPaths);
            if(result<1) {
                return setInsertResultError();
            }

            FlowPaths flowPaths1 = new FlowPaths();
            flowPaths1.setA15Id(Long.parseLong(a15Id));
            flowPaths1.setStepId(Long.parseLong(nextStepId));
            flowPaths1.setCurrentUserId(Long.parseLong(nextUserId));
            flowPaths1.setStatus("processing");
            Timestamp d1 = new Timestamp(System.currentTimeMillis());
            flowPaths1.setCreatedAt(d1);
            int result1 = flowPathsMapper.insert(flowPaths1);
            if(result1<1) {
                return setInsertResultError();
            }
            //修改对应a15Id的a15s表中的status
            FlowPaths result4 =a15Mapper.selectLatestStatus(Long.parseLong(a15Id));
            String status2=result4.getStatus();
            int result5 = a15Mapper.update(Long.parseLong(a15Id),status2);
            if(result5<1) {
                return setUpdateResultError();
            }

            return setResultSuccess("提交成功");
        } catch (Exception e) {
            // TODO: handle exception
            return setInsertResultError();
        }
    }
    //审批提交，同时判断是否有下一步
    @RequestMapping("approval")
    public ResponseBase approval(
            @RequestParam(value = "flowPathId", defaultValue = "") String flowPathId,
            @RequestParam(value = "a15Id", defaultValue = "") String a15Id,
            @RequestParam(value = "nextStepId", defaultValue = "") String nextStepId,
            @RequestParam(value = "nextUserId", defaultValue = "") String nextUserId,
            @RequestParam(value = "status", defaultValue = "completed") String status
    ) {
        try {
            if(status.equals("completed")){
                int result1 = flowPathsMapper.update(Long.parseLong(flowPathId),status);
                FlowPaths flowPaths1 = new FlowPaths();
                flowPaths1.setA15Id(Long.parseLong(a15Id));
                flowPaths1.setStepId(Long.parseLong(nextStepId));
                flowPaths1.setCurrentUserId(Long.parseLong(nextUserId));
                flowPaths1.setStatus("processing");
                Timestamp d1 = new Timestamp(System.currentTimeMillis());
                flowPaths1.setCreatedAt(d1);
                int result = flowPathsMapper.insert(flowPaths1);
//                int select = flowPathsMapper.selectUserId(Long.parseLong(a15Id),Long.parseLong(nextUserId));
//                long id =flowPathsMapper.selectId(Long.parseLong(a15Id),Long.parseLong(nextUserId));
//                int result3 = flowPathsMapper.selectMaxId();
                if(result<1) {
                    return setInsertResultError();
                }
            }
            if(status.equals("rejected")){
                int result2 = flowPathsMapper.update(Long.parseLong(flowPathId),status);
            }
            if(Long.parseLong(nextStepId)==0) {
                return setResultError(100,"没有下一步了");
            }
            FlowPaths result4 =a15Mapper.selectLatestStatus(Long.parseLong(a15Id));
            String status2=result4.getStatus();
            int result5 = a15Mapper.update(Long.parseLong(a15Id),status2);
            return setResultSuccess("提交成功");

        } catch (Exception e) {
            // TODO: handle exception
            return setInsertResultError();
        }
    }

    //使用userId查询已办或待办事项
    @RequestMapping("listPaged")
    public ResponseBase listPaged(
            @RequestParam(value = "listNo", required = false,defaultValue = "") String listNo,
                                  @RequestParam(value = "currentUserId", defaultValue = "") String currentUserId,
                                  @RequestParam(value = "status", defaultValue = "") String status,
                                  @RequestParam(value = "order", defaultValue = "id") String order,
                                  @RequestParam(value = "sort", defaultValue = "desc") String sort,
                                  @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                  @RequestParam(value = "pageIndex", defaultValue = "1") String pageIndex) {
        try {
            List<WhereParam> whereParams = new ArrayList<>();
            WhereParam whereParam = new WhereParam("fp.currentUserId", "=", currentUserId);
            whereParams.add(whereParam);
            WhereParam whereParam1 = new WhereParam("fp.status", "like", status);
            whereParams.add(whereParam1);
            WhereParam whereParam2 = new WhereParam("m.listNo", "like", listNo);
            whereParams.add(whereParam2);
            SelectParam selectParam = new SelectParam(order, sort, Integer.parseInt(pageSize),
                    Integer.parseInt(pageIndex), whereParams);
            System.out.println(order);
            System.out.println(sort);
            List<FlowPaths> myList = flowPathsMapper.listPaged(selectParam);
            int total = flowPathsMapper.countPaged(selectParam);
            PageList pageList = new PageList(total, myList);
            return setResultSuccessWithData(pageList);
        } catch (Exception e) {
            // TODO: handle exception
            return setSelectResultError();
        }
    }
    @RequestMapping("listByA15Id")
    public ResponseBase listByA15Id(@RequestParam(value = "a15Id", defaultValue = "") String a15Id) {
        try {
            System.out.println("listByA15Id:" + a15Id);
            List<FlowPaths> flowPaths = flowPathsMapper.listByA15Id(Long.parseLong(a15Id));
            return setResultSuccessWithData(flowPaths);
        } catch (Exception e) {
        	System.out.println(e);
            // TODO: handle exception
            return setSelectResultError();
        }
    }
    
    
}
