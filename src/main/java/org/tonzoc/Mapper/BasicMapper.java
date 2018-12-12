package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.Basic;
import org.tonzoc.Model.Company;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/11/11.
 */
public interface BasicMapper {

    //添加基础设置
    @Insert("insert into basic(name,companyid,length,startSection,endSection,startDate,materialRatio,materialPeriod,startRatio,startPeriod,retentionRatio,retentionPeriod,note)" +
            "values(#{name},#{companyid},#{lcompanyidength}.#{startSection},#{endSection},#{startDate},#{materialRatio},#{materialPeriod},#{startRatio},#{startPeriod},#{retentionRatio},#{retentionPeriod},#{note})")
    int addBasic(@Param("name") String name,
                 @Param("companyid") long companyid,
                 @Param("length") BigDecimal length,
                 @Param("startSection") String startSection,
                 @Param("endSection") String endSection,
                 @Param("startDate") String startDate,
                 @Param("materialRatio") BigDecimal materialRatio,
                 @Param("materialPeriod") long materialPeriod,
                 @Param("startRatio") BigDecimal startRatio,
                 @Param("startPeriod") long startPeriod,
                 @Param("retentionRatio") BigDecimal retentionRatio,
                 @Param("retentionPeriod") long retentionPeriod,
                 @Param("note") String note);

    //根据公司id查询公司
    @Select("SELECT id, name, contact, tel, created_at, updated_at FROM company WHERE id=#{id} and deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "contact", column = "contact"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    Company listCompanyById(@Param("id") long id);
    //查询基础设置,分页
    @SelectProvider(type = BasicSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "companyid", column = "companyid"),
            @Result(property = "length", column = "length"),
            @Result(property = "startSection", column = "startSection"),
            @Result(property = "endSection", column = "endSection"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "materialRatio", column = "materialRatio"),
            @Result(property = "materialPeriod", column = "materialPeriod"),
            @Result(property = "startRatio", column = "startRatio"),
            @Result(property = "startPeriod", column = "startPeriod"),
            @Result(property = "retentionRatio", column = "retentionRatio"),
            @Result(property = "retentionPeriod", column = "retentionPeriod"),
            @Result(property = "note", column = "note"),
            @Result(property = "company", column = "companyid",one = @One(select = "org.tonzoc.Mapper.BasicMapper.listCompanyById"))
    })
    List<Basic> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = BasicSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);
}
