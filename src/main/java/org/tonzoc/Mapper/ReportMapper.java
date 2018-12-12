package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.tonzoc.Model.Report;
import org.tonzoc.Model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/12/11 0011.
 */
public interface ReportMapper {
    @Select("SELECT id, name, targetUrl, created_at, updated_at FROM reports WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Report> list();
}
