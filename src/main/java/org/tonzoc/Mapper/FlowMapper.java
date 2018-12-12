package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.Flow;
import org.tonzoc.Model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/11/15 0015.
 */
public interface FlowMapper {
    //查询列表
    @Select("SELECT id, name, projectId, created_at, updated_at FROM flow WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "project", column = "projectId",one = @One(select = "org.tonzoc.Mapper.PeriodMapper.listProjectById"))
    })
    List<Flow> list();
    //分页查询
    @SelectProvider(type = FlowSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "project", column = "projectId",one = @One(select = "org.tonzoc.Mapper.PeriodMapper.listProjectById"))

    })
    public List<Flow> listPaged(@Param("params") SelectParam selectParam);
    //查询在该页第几条
    @SelectProvider(type = FlowSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);

    //根据id查询
    @Select("SELECT id,name, projectId, created_at, updated_at FROM flow WHERE id=#{id} and deleted_at is null")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "project", column = "projectId",one = @One(select = "org.tonzoc.Mapper.PeriodMapper.listProjectById"))
    })
    Flow selectById(@Param("id") long id);

    @Select("SELECT id,name, projectId, created_at, updated_at FROM flow WHERE projectId=#{projectId} and deleted_at is null")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "projectId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "project", column = "projectId",one = @One(select = "org.tonzoc.Mapper.PeriodMapper.listProjectById"))
    })
    List<Flow> selectByProjectId(@Param("projectId") long projectId);
    //保存
    @Insert("INSERT INTO flow(name,projectId)" +
            " values(#{name},#{projectId})")
    int insert(@Param("name") String name,@Param("projectId") long projectId);

    //通过id和name修改
    @Update("UPDATE flow SET name = #{name}, " +
            "projectId = #{projectId}," +
            "updated_at = current_timestamp " +
            "WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("name") String name,
               @Param("projectId") long projectId );
    //通过id软删除
    @Update("UPDATE flow SET deleted_at = current_timestamp WHERE id = #{id}")
    int delete(@Param("id") long id);


    @Insert("INSERT INTO flow(name,projectId)" +
            " values(#{name},#{projectId})")
    int insertFlow(Flow flow);


}
