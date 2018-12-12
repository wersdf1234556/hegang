package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.Param;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.Utility;

public class PeriodSelectProvider {
    private final String tableName = "periods";
    private final String selects = "id, name, projectId, startDate, endDate, created_at, updated_at";
    private final String counts = "count(*)";

    public String listPaged(@Param("params") SelectParam selectParam) {
        StringBuilder sb = new StringBuilder(Utility.getSql(this.tableName, this.selects, selectParam.getWhereParams()));

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
        return Utility.getSql(this.tableName, this.counts, selectParam.getWhereParams());
    }
}
