package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.Param;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.Utility;

/**
 * Created by Administrator on 2018/11/19 0019.
 */
public class RoleBindSelectProvider {
    private final String mainTable ="m";
    private final String tableName = "modules m left join rolebind rb on rb.moduleId = m.id";
    private final String selects ="m.id, m.name, m.targetUrl, m.parentId, m.created_at, m.updated_at";
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

    public String countPaged(@Param("params") SelectParam selectParam) {
        return Utility.getSql1(this.mainTable, this.tableName, this.counts, selectParam.getWhereParams());
    }

}
