package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.Tender;

import java.math.BigDecimal;
import java.util.List;

public interface TenderMapper {
    @Select("SELECT t.id, t.name, t.projectId, t.code, t.startSection, t.endSection, t.length, t.startDate, t.endDate, t.ownerCompany, t.contractCompany, t.contractTel, t.superviseCompany, t.superviseTel, t.testCompany, t.defaultApprovalDate, t.note, t.created_at, t.updated_at FROM tenders t LEFT JOIN projects p ON t.projectId = p.id WHERE t.deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "code", column = "code"),
            @Result(property = "startSection", column = "startSection"),
            @Result(property = "endSection", column = "endSection"),
            @Result(property = "length", column = "length"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "endDate", column = "endDate"),
            @Result(property = "ownerCompany", column = "ownerCompany"),
            @Result(property = "contractCompany", column = "contractCompany"),
            @Result(property = "contractTel", column = "contractTel"),
            @Result(property = "superviseCompany", column = "superviseCompany"),
            @Result(property = "superviseTel", column = "superviseTel"),
            @Result(property = "testCompany", column = "testCompany"),
            @Result(property = "defaultApprovalDate", column = "defaultApprovalDate"),
            @Result(property = "note", column = "note"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Tender> list();

    @Insert("INSERT INTO tenders(name, projectId，code，startSection, endSection, length, startDate, endDate, ownerCompany, contractCompany, contractTel, superviseCompany, superviseTel, testCompany, defaultApprovalDate, note)" +
            " values(#{name}, #{projectId}， #{code}， #{startSection}, #{endSection}, #{length}, #{startDate}, #{endDate}, #{ownerCompany}, #{contractCompany}, #{contractTel}, #{superviseCompany}, #{superviseTel}, #{testCompany}, #{defaultApprovalDate}, #{note})")
    int insert(@Param("name") String name,
               @Param("projectId") long projectId,
               @Param("code") String code,
               @Param("startSection") String startSection,
               @Param("endSection") String endSection,
               @Param("length") BigDecimal length,
               @Param("startDate") String startDate,
               @Param("endDate") String endDate,
               @Param("ownerCompany") String ownerCompany,
               @Param("contractCompany") String contractCompany,
               @Param("contractTel") String contractTel,
               @Param("superviseCompany") String superviseCompany,
               @Param("superviseTel") String superviseTel,
               @Param("testCompany") String testCompany,
               @Param("defaultApprovalDate") String defaultApprovalDate,
               @Param("note") String note);

    @SelectProvider(type = TenderSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "code", column = "code"),
            @Result(property = "startSection", column = "startSection"),
            @Result(property = "endSection", column = "endSection"),
            @Result(property = "length", column = "length"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "endDate", column = "endDate"),
            @Result(property = "ownerCompany", column = "ownerCompany"),
            @Result(property = "contractCompany", column = "contractCompany"),
            @Result(property = "contractTel", column = "contractTel"),
            @Result(property = "superviseCompany", column = "superviseCompany"),
            @Result(property = "superviseTel", column = "superviseTel"),
            @Result(property = "testCompany", column = "testCompany"),
            @Result(property = "defaultApprovalDate", column = "defaultApprovalDate"),
            @Result(property = "note", column = "note"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    public List<Tender> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = TenderSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);

    @Update("UPDATE tenders SET deleted_at = current_timestamp WHERE id = #{id}")
    int delete(@Param("id") long id);

    @Update("UPDATE tenders SET name = #{name}, " +
            "projectId = #{projectId}, " +
            "code = #{code}, " +
            "startSection = #{startSection}, " +
            "endSection = #{endSection}, " +
            "length = #{length}, " +
            "startDate = #{startDate}, " +
            "endDate = #{endDate}, " +
            "ownerCompany = #{ownerCompany}, " +
            "contractCompany = #{contractCompany}, " +
            "contractTel = #{contractTel}, " +
            "superviseCompany = #{superviseCompany}, " +
            "superviseTel = #{superviseTel}, " +
            "testCompany = #{testCompany}, " +
            "defaultApprovalDate = #{defaultApprovalDate}, " +
            "note = #{note}, " +
            "updated_at = current_timestamp " +
            "WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("name") String name,
               @Param("projectId") long projectId,
               @Param("code") String code,
               @Param("startSection") String startSection,
               @Param("endSection") String endSection,
               @Param("length") BigDecimal length,
               @Param("startDate") String startDate,
               @Param("endDate") String endDate,
               @Param("ownerCompany") String ownerCompany,
               @Param("contractCompany") String contractCompany,
               @Param("contractTel") String contractTel,
               @Param("superviseCompany") String superviseCompany,
               @Param("superviseTel") String superviseTel,
               @Param("testCompany") String testCompany,
               @Param("defaultApprovalDate") String defaultApprovalDate,
               @Param("note") String note);
}
