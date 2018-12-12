package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.Period;
import org.tonzoc.Model.Project;
import org.tonzoc.Model.Province;

import java.util.List;

public interface PeriodMapper {
    @Select("SELECT id, name, projectId, startDate, endDate, created_at, updated_at FROM periods WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "endDate", column = "endDate"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "project", column = "projectId",one = @One(select = "org.tonzoc.Mapper.PeriodMapper.listProjectById"))

    })
    List<Period> list();

    @Insert("INSERT INTO periods(name, projectId, startDate, endDate) values(#{name},#{projectId}, #{startDate}, #{endDate})")
    int insert(@Param("name") String name,
               @Param("projectId") long projectId,
               @Param("startDate") String startDate,
               @Param("endDate") String endDate);

    @SelectProvider(type = PeriodSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "endDate", column = "endDate"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "project", column = "projectId",one = @One(select = "org.tonzoc.Mapper.PeriodMapper.listProjectById"))
    })
    public List<Period> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = PeriodSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);

    @Select("SELECT id, name, projectId, startDate, endDate, created_at, updated_at FROM periods WHERE projectId=#{projectId} and deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "endDate", column = "endDate"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "project", column = "projectId",one = @One(select = "org.tonzoc.Mapper.PeriodMapper.listProjectById"))

    })
    List<Period> listByProjectId(@Param("projectId") long projectId);

    @Update("UPDATE periods SET deleted_at = current_timestamp WHERE id = #{id}")
    int delete(@Param("id") long id);

    @Update("UPDATE periods SET name = #{name},projectId = #{projectId}, startDate = #{startDate}, endDate = #{endDate}, updated_at = current_timestamp WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("name") String name,
               @Param("projectId") long projectId,
               @Param("startDate") String startDate,
               @Param("endDate") String endDate);

    @Select("SELECT p.id, p.name, p.code, p.provinceId, p.ownerCompanyId, p.length, p.startSection, p.endSection, p.startDate, p.note, p.created_at, p.updated_at FROM projects p WHERE p.id =#{id} and p.deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "provinceId", column = "provinceId"),
            @Result(property = "ownerCompanyId", column = "ownerCompanyId"),
            @Result(property = "length", column = "length"),
            @Result(property = "startSection", column = "startSection"),
            @Result(property = "endSection", column = "endSection"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "note", column = "note"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "company", column = "ownerCompanyId",one = @One(select = "org.tonzoc.Mapper.ProjectMapper.listCompanyById")),
            @Result(property = "province", column = "provinceId",one = @One(select = "org.tonzoc.Mapper.ProjectMapper.listProvinceById"))
    })
    Project listProjectById(@Param("id") long id);
}
