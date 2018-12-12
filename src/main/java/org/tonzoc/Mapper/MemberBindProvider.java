package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.Param;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.Utility;

/**
 * Created by Administrator on 2018/11/15.
 */
public class MemberBindProvider {
    private final String mainTable = "s";
    private final String tableName = "steps s left join memberbind m on m.stepId = s.id";
    private final String selects = "s.id, s.flowId, s.name,s.nextStepId, s.created_at, s.updated_at";
    private final String counts = "count(*)";

    //分页查询
    public String listPaged(@Param("params") SelectParam selectParam) {
        StringBuilder sb = new StringBuilder(Utility.getSql1(mainTable, this.tableName, this.selects, selectParam.getWhereParams()));

        sb.append(" ORDER BY ");
        sb.append(selectParam.getOrder());
        sb.append(" ");
        sb.append(selectParam.getSort());

//        sb.append(" LIMIT ");
        sb.append(" LIMIT #{params.offset}, #{params.pageSize}");
//        sb.append(selectParam.getPageIndex());
//        sb.append(",");
//        sb.append(selectParam.getPageSize());
        

        System.out.println(sb.toString());
        return sb.toString();
    }

    //查询总条数
    public String countPaged(@Param("params") SelectParam selectParam) {
        return Utility.getSql1(this.mainTable, this.tableName, this.counts, selectParam.getWhereParams());
    }
}
