package org.tonzoc.Common;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Utility {
    public static String formatTimestamp(Timestamp timeStamp) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(timeStamp);
    }

    public static String getSql1(String mainTable, String tableName, String selects, List<WhereParam> whereParams) {
        SQL sql = new SQL().SELECT(selects).FROM(tableName);

        for (WhereParam whereParam : whereParams) {

            if (StringUtils.hasText(whereParam.getParam())) {
                switch (whereParam.getOperator()) {
                    case "=":
                        sql.WHERE(whereParam.getName() + " = '" + whereParam.getParam() + "'");
                        break;
                    case ">":
                        sql.WHERE(whereParam.getName() + " > " + whereParam.getParam());
                        break;
                    case "like":
                        sql.WHERE(whereParam.getName() + " like '%" + whereParam.getParam() + "%'");
                        break;
                    default:
                        break;
                }

            }
        }

        sql.WHERE(mainTable + ".deleted_at IS NULL");

        System.out.println(sql.toString());

        return sql.toString();
    }
    public static String getSql(String tableName, String selects, List<WhereParam> whereParams) {
        SQL sql = new SQL().SELECT(selects).FROM(tableName);

        for (WhereParam whereParam : whereParams) {

            if (StringUtils.hasText(whereParam.getParam())) {
                switch (whereParam.getOperator()) {
                    case "=":
                        sql.WHERE(whereParam.getName() + " = '" + whereParam.getParam() + "'");
                        break;
                    case ">":
                        sql.WHERE(whereParam.getName() + " > " + whereParam.getParam());
                        break;
                    case "like":
                        sql.WHERE(whereParam.getName() + " like '%" + whereParam.getParam() + "%'");
                        break;
                    default:
                        break;
                }

            }
        }


        sql.WHERE(tableName + ".deleted_at IS NULL");

        System.out.println(sql.toString());

        return sql.toString();
    }
    public static String getSql2(String tableName, String selects, List<WhereParam> whereParams) {
        SQL sql = new SQL().SELECT(selects).FROM(tableName);

        for (WhereParam whereParam : whereParams) {

            if (StringUtils.hasText(whereParam.getParam())) {
                switch (whereParam.getOperator()) {
                    case "=":
                        sql.WHERE(whereParam.getName() + " = '" + whereParam.getParam() + "'");
                        break;
                    case ">":
                        sql.WHERE(whereParam.getName() + " > " + whereParam.getParam());
                        break;
                    case "like":
                        sql.WHERE(whereParam.getName() + " like '%" + whereParam.getParam() + "%'");
                        break;
                    default:
                        break;
                }

            }
        }

        System.out.println(sql.toString());

        return sql.toString();
    }

    public static String getSql3(String mainTable, String tableName, String selects, List<WhereParam> whereParams) {
        SQL sql = new SQL().SELECT(selects).FROM(tableName);
        sql.WHERE(mainTable+".id in (select maxid from (select max(id) as maxid from flowpaths GROUP BY a15Id,currentUserId,`status`)b)");

        for (WhereParam whereParam : whereParams) {

            if (StringUtils.hasText(whereParam.getParam())) {
                switch (whereParam.getOperator()) {
                    case "=":
                        sql.WHERE(whereParam.getName() + " = '" + whereParam.getParam() + "'");
                        break;
                    case ">":
                        sql.WHERE(whereParam.getName() + " > " + whereParam.getParam());
                        break;
                    case "like":
                        sql.WHERE(whereParam.getName() + " like '%" + whereParam.getParam() + "%'");
                        break;
                    default:
                        break;
                }

            }
        }

        sql.WHERE(mainTable + ".deleted_at IS NULL ");

        System.out.println(sql.toString());

        return sql.toString();
    }
}
