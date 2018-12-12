package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.Step;
import org.tonzoc.Model.User;

import java.math.BigDecimal;
import java.util.List;

public interface StepMapper {
    @Select("SELECT u.id,u.code, u.name, u.staffName, u.mobile,  u.birthday,  u.gender,u.email, u.created_at, u.updated_at FROM users u WHERE id in (select mb.userId FROM memberbind mb where mb.stepId=#{stepId})")
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

//    @Select("SELECT a.id, a.name, a.nextStepId, b.name as nextStepName, a.nextUserId, c.staffName as nextUserStaffName, a.created_at, a.updated_at FROM steps a LEFT JOIN steps b ON a.nextStepId = b.id LEFT JOIN users c ON a.nextUserId = c.id WHERE a.deleted_at IS NULL AND a.id = 1")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "nextStepId", column = "nextStepId"),
//            @Result(property = "nextUserId", column = "nextUserId"),
//            @Result(property = "nextStepName", column = "nextStepName"),
//            @Result(property = "nextUserStaffName", column = "nextUserStaffName"),
//            @Result(property = "createdAt", column = "created_at"),
//            @Result(property = "updatedAt", column = "updated_at"),
//    })
//    List<Step> listFirstStep();
    @Select("select id, flowId, name, nextStepId, created_at, updated_at FROM steps WHERE id=#{id} and deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flowId", column = "flowId"),
            @Result(property = "name", column = "name"),
            @Result(property = "nextStepId", column = "nextStepId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    Step nextStep(@Param("id") long id);

    @SelectProvider(type = StepSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flowId", column = "flowId"),
            @Result(property = "name", column = "name"),
            @Result(property = "nextStepId", column = "nextStepId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "flow", column = "flowId",one = @One(select = "org.tonzoc.Mapper.FlowMapper.selectById")),
            @Result(property = "nextStep", column = "nextStepId",one = @One(select = "org.tonzoc.Mapper.StepMapper.nextStep")),
            @Result(property = "user", column = "id",one = @One(select = "org.tonzoc.Mapper.StepMapper.selectUser"))
    })
    public List<Step> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = StepSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);


    @Select("SELECT a.id, a.flowId, a.name, a.nextStepId, a.created_at, a.updated_at FROM steps a  LEFT JOIN steps b ON a.nextStepId = b.id WHERE a.id=#{id} and a.deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "flowId", column = "flowId"),
            @Result(property = "name", column = "name"),
            @Result(property = "nextStepId", column = "nextStepId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "flow", column = "flowId",one = @One(select = "org.tonzoc.Mapper.FlowMapper.selectById")),
            @Result(property = "nextStep", column = "nextStepId",one = @One(select = "org.tonzoc.Mapper.StepMapper.nextStep"))
    })
    Step selectById(@Param("id") long id);

    //通过flowId获取
    @Select("SELECT a.id, a.flowId, a.name, a.nextStepId, a.created_at, a.updated_at FROM steps a WHERE a.flowId=#{flowId} and a.deleted_at IS NULL")
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
    Step selectByFlowId(@Param("flowId") long flowId);

    @Insert("INSERT INTO steps(flowId, name, nextStepId) " +
            "values(#{flowId}, #{name}, #{nextStepId})")
    int insert(@Param("flowId") long flowId,
               @Param("name") String name,
               @Param("nextStepId") long nextStepId);

    @Update("UPDATE steps SET flowId = #{flowId}, " +
            "name = #{name}, " +
            "nextStepId = #{nextStepId}, " +
            "updated_at = current_timestamp " +
            "WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("flowId") long flowId,
               @Param("name") String name,
               @Param("nextStepId") long nextStepId);

    @Update("UPDATE steps SET deleted_at = current_timestamp WHERE id = #{id}")
    int delete(@Param("id") long id);

}
