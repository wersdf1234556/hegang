package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.A15;
import org.tonzoc.Model.FlowPaths;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface A15Mapper {
    //保存save A15表
    @Insert("INSERT INTO a15s(meterageListId, startSection, endSection, part, pictureNo, certNo, imageUrl, formula, num, date, creatorUserId) " +
            "values(#{meterageListId}, #{startSection}, #{endSection}, #{part}, #{pictureNo}, #{certNo}, #{imageUrl}, #{formula}, #{num}, #{date}, #{creatorUserId})")
    int insert(@Param("meterageListId") long meterageListId,
               @Param("startSection") String startSection,
               @Param("endSection") String endSection,
               @Param("part") String part,
               @Param("pictureNo") String pictureNo,
               @Param("certNo") String certNo,
               @Param("imageUrl") String imageUrl,
               @Param("formula") String formula,
               @Param("num") BigDecimal num,
               @Param("date") String date,
               @Param("creatorUserId") long creatorUserId,
               @Param("status") String status);
    //列表查询A15
    @SelectProvider(type = A15SelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "meterageListId", column = "meterageListId"),
            @Result(property = "startSection", column = "startSection"),
            @Result(property = "endSection", column = "endSection"),
            @Result(property = "part", column = "part"),
            @Result(property = "pictureNo", column = "pictureNo"),
            @Result(property = "certNo", column = "certNo"),
            @Result(property = "imageUrl", column = "imageUrl"),
            @Result(property = "formula", column = "formula"),
            @Result(property = "num", column = "num"),
            @Result(property = "date", column = "date"),
            @Result(property = "creatorUserId", column = "creatorUserId"),
            @Result(property = "status", column = "status"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "meterageList", column = "meterageListId",one = @One(select = "org.tonzoc.Mapper.MeterageListMapper.selectById")),
//            @Result(property = "flowPaths", column = "status",one = @One(select = "org.tonzoc.Mapper.A15Mapper.selectLatestStatus"))
    })
    public List<A15> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = A15SelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);

    @Select("select a15Id,status from flowpaths where id in (select maxid from (select max(id) as maxid from flowpaths GROUP BY a15Id)b) and a15Id=#{a15Id}")
    FlowPaths selectLatestStatus(@Param("a15Id") long a15Id);
//    @Update("update a15s set status =#{status},updated_at = current_timestamp WHERE id = #{id}")
//    int update(@Param("id") long id);

    @Update("update a15s set status =#{status},updated_at = current_timestamp WHERE id = #{id}")
    int update(@Param("id") long id,@Param("status") String status);
}
