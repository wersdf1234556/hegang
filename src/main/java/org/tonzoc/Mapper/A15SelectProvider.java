package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.Param;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Common.Utility;
import org.tonzoc.Model.A15;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class A15SelectProvider {
    private final String mainTable = "a";
    private final String tableName = "a15s a LEFT JOIN meterageList m ON a.meterageListId = m.id";
    private final String selects = "a.id, a.meterageListId, a.startSection, a.endSection, a.part, a.pictureNo, a.certNo, a.imageUrl, a.formula, a.num, a.date, a.creatorUserId,a.status,a.created_at, a.updated_at";
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
    public String updateAll(Map map) {
        List<A15> configs = (List<A15>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("update a15s ");
        sb.append("set cron=case id ");
        MessageFormat mfCron = new MessageFormat("#'{'list[{0}].cron'}'");
        for (int i = 0; i < configs.size(); i++) {
            sb.append("when ");
            sb.append(configs.get(i).getId());
            sb.append("then ");
            sb.append(mfCron.format(new Object[]{i}));
        }
        sb.append("end ");
        sb.append("where id in");
        sb.append("(");
        for (int i = 0; i < configs.size(); i++)
        {
            sb.append(configs.get(i).getId());
            if (i < configs.size() - 1) { sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    
}
