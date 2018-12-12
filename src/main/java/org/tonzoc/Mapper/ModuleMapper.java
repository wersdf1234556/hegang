package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.tonzoc.Model.Module;

import java.util.List;

public interface ModuleMapper {

    @Select("SELECT id, name, targetUrl, parentId, created_at, updated_at FROM modules WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Module> list();

    @Select("SELECT id, name, targetUrl, parentId, created_at, updated_at FROM modules WHERE deleted_at IS NULL AND parentId = #{parentId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Module> listByParentId(@Param("parentId") long parentId);
}
