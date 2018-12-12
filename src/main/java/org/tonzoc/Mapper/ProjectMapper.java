package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/11/19 0019.
 */
public interface ProjectMapper {
//    @Select("SELECT p.id, p.name, p.code, p.ownerCompanyId, p.length, p.startSection, p.endSection, p.startDate, p.note, p.created_at, p.updated_at FROM projects p LEFT JOIN company c ON p.ownerCompanyId=c.id WHERE p.deleted_at IS NULL")
    @Select("SELECT p.id, p.name, p.code, p.provinceId, p.ownerCompanyId, p.length, p.startSection, p.endSection, p.startDate, p.note, p.created_at, p.updated_at FROM projects p WHERE p.deleted_at IS NULL")
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
    List<Project> list();

    @Insert("INSERT INTO projects(name, code, provinceId, ownerCompanyId, length, startSection, endSection, startDate, note) " +
            "values(#{name}, #{code}, #{provinceId}, #{ownerCompanyId}, #{length}, #{startSection}, #{endSection}, #{startDate}, #{note})")
    int insert(@Param("name") String name,
               @Param("code") String code,
               @Param("provinceId") long provinceId,
               @Param("ownerCompanyId") long ownerCompanyId,
               @Param("length") BigDecimal length,
               @Param("startSection") String startSection,
               @Param("endSection") String endSection,
               @Param("startDate") String startDate,
               @Param("note") String note);

    @SelectProvider(type = ProjectSelectProvider.class, method = "listPaged")
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
    public List<Project> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = ProjectSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);



    //LEFT JOIN company c ON p.ownerCompanyId = c.id
    @Select("SELECT p.id, p.name, p.code, p.provinceId, p.ownerCompanyId, p.length, p.startSection, p.endSection, p.startDate, p.note, p.created_at, p.updated_at FROM projects p WHERE p.id=#{id} and p.deleted_at is null")
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
            @Result(property = "companyName", column = "companyName"),
            @Result(property = "contact", column = "contact"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "company", column = "ownerCompanyId",one = @One(select = "org.tonzoc.Mapper.ProjectMapper.listCompanyById")),
            @Result(property = "province", column = "provinceId",one = @One(select = "org.tonzoc.Mapper.ProjectMapper.listProvinceById"))
    })
    List<Project> selectById(@Param("id") long id);

    @Select("SELECT p.id, p.name, p.code, p.provinceId, p.ownerCompanyId, p.length, p.startSection, p.endSection, p.startDate, p.note, p.created_at, p.updated_at FROM projects p WHERE p.provinceId=#{provinceId} and p.deleted_at is null")
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
            @Result(property = "companyName", column = "companyName"),
            @Result(property = "contact", column = "contact"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "company", column = "ownerCompanyId",one = @One(select = "org.tonzoc.Mapper.ProjectMapper.listCompanyById")),
            @Result(property = "province", column = "provinceId",one = @One(select = "org.tonzoc.Mapper.ProjectMapper.listProvinceById"))
    })
    List<Project> selectByProvinceId(@Param("provinceId") long provinceId);


    @Update("UPDATE projects SET name = #{name}, " +
            "code = #{code}, " +
            "provinceId = #{provinceId}, " +
            "ownerCompanyId = #{ownerCompanyId}, " +
            "length = #{length}, " +
            "startSection = #{startSection}, " +
            "endSection = #{endSection}, " +
            "startDate = #{startDate}, " +
            "note = #{note} " +
            "WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("name") String name,
               @Param("code") String code,
               @Param("provinceId") long provinceId,
               @Param("ownerCompanyId") long ownerCompanyId,
               @Param("length") BigDecimal length,
               @Param("startSection") String startSection,
               @Param("endSection") String endSection,
               @Param("startDate") String startDate,
               @Param("note") String note);

    @Update("UPDATE projects SET deleted_at = current_timestamp WHERE id = #{id}")
    int delete(@Param("id") long id);

    @Select("SELECT id, name, contact, tel, created_at, updated_at FROM company WHERE id =#{id} and deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "contact", column = "contact"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    Company listCompanyById(@Param("id") long id);

    @Select("SELECT id, name, code, created_at, updated_at FROM provinces WHERE id =#{id} and deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    Province listProvinceById(@Param("id") long id);


}
