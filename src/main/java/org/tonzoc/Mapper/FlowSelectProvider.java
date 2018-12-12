package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.Param;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.Utility;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
public class FlowSelectProvider {
    private final String mainTable = "f";
    private final String tableName = "flow f LEFT JOIN projects p ON f.projectId = p.id";
    private final String selects = "f.id, f.name, f.projectId, f.created_at, f.updated_at";
    private final String counts = "count(*)";
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
