package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.A15;
import org.tonzoc.Model.FlowPaths;

import java.util.List;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
public interface FlowPathsMapper {
    
    //保存
    @Insert("INSERT INTO flowpaths(a15Id,stepId,currentUserId,status,created_at) values(#{a15Id},#{stepId},#{currentUserId},#{status},#{createdAt})")
    int insert(FlowPaths flowPaths);

    @Update("UPDATE flowpaths SET status = #{status}, updated_at = current_timestamp WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("status") String status);

    @Select("select fp.id,fp.a15Id,fp.stepId,fp.currentUserId,fp.status,fp.created_at,fp.updated_at " +
            "from flowpaths fp left join a15s a on fp.a15Id = a.id LEFT JOIN meterageList m ON a.meterageListId = m.id " +
            "where fp.a15Id = #{a15Id} and fp.deleted_at is null")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "a15Id", column = "a15Id"),
            @Result(property = "stepId", column = "stepId"),
            @Result(property = "currentUserId", column = "currentUserId"),
            @Result(property = "status", column = "status"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "a15", column = "a15Id",one = @One(select = "org.tonzoc.Mapper.FlowPathsMapper.listA15")),
            @Result(property = "step", column = "stepId",one = @One(select = "org.tonzoc.Mapper.StepMapper.selectById"))
    })
    List<FlowPaths> listByA15Id(@Param("a15Id") long a15Id);

    @Select("Select a.id, a.meterageListId, a.startSection, a.endSection, a.part, a.pictureNo, a.certNo, a.imageUrl, a.formula, a.num, a.date, a.creatorUserId,a.status,a.created_at, a.updated_at from a15s a where a.id=#{id} and a.deleted_at is null")
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
            @Result(property = "meterageList", column = "meterageListId",one = @One(select = "org.tonzoc.Mapper.MeterageListMapper.selectById"))
    })
    A15 listA15(@Param("id") long id);

//    @Select("Select id,a15Id,stepId,currentUserId,status,created_at, updated_at from flowpaths where currentUserId=#{currentUserId} and status='已提交' and deleted_at is null")

    @SelectProvider(type = FlowPathsSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "a15Id", column = "a15Id"),
            @Result(property = "stepId", column = "stepId"),
            @Result(property = "currentUserId", column = "currentUserId"),
            @Result(property = "status", column = "status"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "a15", column = "a15Id",one = @One(select = "org.tonzoc.Mapper.FlowPathsMapper.listA15")),
            @Result(property = "step", column = "stepId",one = @One(select = "org.tonzoc.Mapper.StepMapper.selectById"))
    })
    List<FlowPaths> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = FlowPathsSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);

//    @Select("select id,a15Id,stepId,currentUserId,status,created_at, updated_at from flowpaths where id in (select maxid from (select max(id) as maxid from flowpaths GROUP BY a15Id,currentUserId,`status`)b)")
//   int selectMaxId();

//    @Select("select id,a15Id,stepId,currentUserId,status,created_at, updated_at from flowpaths where a15Id = #{a15Id} and currentUserId = #{currentUserId} and deleted_at is null")
//   int selectUserId(@Param("a15Id") long a15Id,@Param("currentUserId") long currentUserId);
//    @Delete("delete from flowpaths fp where (fp.a15Id,fp.currentUserId) IN " +
//            "(select a15Id,currentUserId from flowpaths GROUP BY a15Id,currentUserId HAVING count(*) > 1) " +
//            "AND id NOT IN (select min(id) from flowpaths GROUP BY a15Id = #{a15Id},currentUserId = #{currentUserId} HAVING count(*)>1)")
//    int delete(@Param("a15Id") long a15Id,@Param("currentUserId") long currentUserId);
//     @Select("select min(id) from flowpaths GROUP BY a15Id=#{a15Id},currentUserId=#{currentUserId} HAVING count(*) > 1")
//     int selectId(@Param("a15Id") long a15Id,@Param("currentUserId") long currentUserId);


//    @Delete("delete from flowpaths where id not in (select minid from (select min(id) as minid from flowpaths GROUP BY a15Id,currentUserId,`status`)b)")
//    int delete();
}
