package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.Param;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.Utility;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
public class FlowPathsSelectProvider {
    private final String mainTable = "fp";
    private final String tableName = "flowpaths fp left join a15s a on fp.a15Id = a.id LEFT JOIN meterageList m ON a.meterageListId = m.id";
    private final String selects = "fp.id,fp.a15Id,fp.stepId,fp.currentUserId,fp.status,fp.created_at,fp.updated_at ";
    private final String counts = "count(*)";
    public String listPaged(@Param("params") SelectParam selectParam) {
        StringBuilder sb = new StringBuilder(Utility.getSql3(mainTable, this.tableName, this.selects, selectParam.getWhereParams()));

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

    public String countPaged(@Param("params") SelectParam selectParam) {
        return Utility.getSql3(this.mainTable, this.tableName, this.counts, selectParam.getWhereParams());
    }
}
