package org.tonzoc.Mapper;


import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.MemberBind;
import org.tonzoc.Model.Step;
import org.tonzoc.Model.User;

/**
 * Created by Administrator on 2018/11/15.
 */
public interface MemberBindMapper {

    @Select("SELECT u.id,u.code,u.roleId, u.name, u.staffName, u.mobile,  u.birthday,  u.gender,u.email, u.created_at, u.updated_at FROM users u WHERE id in (select mb.userId FROM memberbind mb where mb.stepId=#{stepId})")
    User selectUser(@Param("stepId") long stepId);
    //查询全部
    //(long id, String name, String staffName, String mobile, String birthday, long gender, String email, Timestamp createdAt, Timestamp updatedAt)
    
    @Select("SELECT s.id, s.flowId, s.name,s.nextStepId,s.created_at, s.updated_at FROM steps s WHERE s.deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flowId", column = "flowId"),
            @Result(property = "name", column = "name"),
            @Result(property = "nextStepId", column = "nextStepId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "user", column = "id", one=@One(
                    select="org.tonzoc.Mapper.MemberBindMapper.selectUser")),
            @Result(property = "flow", column = "flowId",one = @One(select = "org.tonzoc.Mapper.FlowMapper.selectById")),
            @Result(property = "nextStep", column = "nextStepId",one = @One(select = "org.tonzoc.Mapper.StepMapper.nextStep"))
    })
    List<Step> list();

  
    
    //分页查询
    @SelectProvider(type = MemberBindProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flowId", column = "flowId"),
            @Result(property = "name", column = "name"),
            @Result(property = "nextStepId", column = "nextStepId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "user", column = "id", one=@One(
                    select="org.tonzoc.Mapper.MemberBindMapper.selectUser")),
            @Result(property = "flow", column = "flowId",one = @One(select = "org.tonzoc.Mapper.FlowMapper.selectById")),
            @Result(property = "nextStep", column = "nextStepId",one = @One(select = "org.tonzoc.Mapper.StepMapper.nextStep"))
    })
    public List<Step> listPaged(@Param("params") SelectParam selectParam);

    //查询总条数
    @SelectProvider(type = MemberBindProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);

    //根据id查询一条
//    @Select("select s.id, s.flowId, s.name, s.nextStepId, s.created_at, s.updated_at, u.name, u.staffName, u.mobile, u.email, u.gender, u.birthday from memberbind mb inner join steps s on mb.stepId=s.id JOIN users u on mb.userId=u.id where mb.id = #{id} and s.deleted_at is null")
//    List<MemberBind> getById(@Param("id") long id);
    @Select("SELECT s.id, s.flowId, s.name,s.nextStepId, s.created_at, s.updated_at FROM steps s left join memberbind mb on mb.stepId = s.id WHERE mb.id=#{id} AND s.deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "stepId", column = "id"),
            @Result(property = "flowId", column = "flowId"),
            @Result(property = "name", column = "name"),
            @Result(property = "nextStepId", column = "nextStepId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "user", column = "id", one=@One(
                    select="org.tonzoc.Mapper.MemberBindMapper.selectUser"))
    })
    List<MemberBind> getById(@Param("id") long id);
    
    
    //根据stepId进行查询
    @Select("select mb.id,s.id, s.flowId, s.name, s.nextStepId, s.created_at, s.updated_at from steps s left join memberbind mb on mb.stepId=s.id where mb.stepId=#{stepId} and s.deleted_at is null")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "stepId", column = "id"),
        @Result(property = "flowId", column = "flowId"),
        @Result(property = "name", column = "name"),
        @Result(property = "nextStepId", column = "nextStepId"),
        @Result(property = "createdAt", column = "created_at"),
        @Result(property = "updatedAt", column = "updated_at"),
        @Result(property = "user", column = "id", one=@One(
                select="org.tonzoc.Mapper.MemberBindMapper.selectUser"))
    })
    List<MemberBind> getByStepId(@Param("stepId") long stepId);

    
    
    //添加   save(memberbind)
    @Insert("insert into memberbind(stepId,userId) values(#{stepId},#{userId})")
    int save(@Param("stepId") long stepId, @Param("userId") long userId);

    //修改   update(memberbind)
//    @Update("UPDATE memberbind SET stepId = #{stepId}, " +
//            "userId = #{userId} " +
//            " WHERE id= #{id}")
//    int update(@Param("id") long id, @Param("stepId") long stepId, @Param("userId") long userId);


    //删除   delete(memberbind)
    @Delete("delete from memberbind where stepId = #{stepId}")
    int delete(@Param("stepId") long stepId);

}