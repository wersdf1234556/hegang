package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.*;

import java.util.List;

/**
 * Created by Administrator on 2018/11/15.
 */
public interface RoleBindMapper {

    @Select("SELECT m.id, m.name, m.targetUrl, m.parentId, m.created_at, m.updated_at FROM modules m inner join rolebind rb ON m.id=rb.moduleId WHERE m.deleted_at IS NULL AND rb.roleId=#{roleId} AND m.parentId = #{parentId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Module> listByParentIdAndRoleId(@Param("parentId") long parentId,@Param("roleId") long roleId);

    @Select("select r.id,r.name,r.created_at, r.updated_at FROM roles r WHERE id in(select rb.roleId from rolebind rb where rb.moduleId=#{moduleId})")
    List<Role> selectRoles(@Param("moduleId") long moduleId);
    //查询所有
    @Select("SELECT m.id, m.name, m.targetUrl, m.parentId, m.created_at, m.updated_at FROM modules m WHERE m.deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "roles", column = "id", many=@Many(
            select="org.tonzoc.Mapper.RoleBindMapper.selectRoles"))
    })
    List<Module> list();

    //根据角色id查询所有模块
    @Select("SELECT m.id, m.name, m.targetUrl, m.parentId, m.created_at, m.updated_at FROM modules m inner join rolebind rb ON m.id=rb.moduleId WHERE m.deleted_at IS NULL AND rb.roleId=#{roleId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "children", column = "id", many=@Many(
                    select="org.tonzoc.Mapper.RoleBindMapper.selectChildren")),
    })
    List<Module> selectModulesByRoleId(@Param("roleId") long  roleId);

    @Select("SELECT m1.id, m1.name, m1.targetUrl, m1.parentId, m1.created_at, m1.updated_at FROM modules m1 inner join modules m ON m.id=m1.parentId WHERE m1.deleted_at IS NULL AND m1.parentId=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Module> selectChildren(@Param("parentId") long  parentId);

//    @Select("select m1.id,m1.name,m2.id as id2,m2.name as name2,m3.id as id3,m3.name as name3 from modules m1,modules m2,modules m3 where m1.id=m2.parentId and m2.id=m3.parentId")
//    List<Module> menuTree();
//
//    @Select("SELECT moduleId from rolebind WHERE roleId=#{roleId}")
//    List<Long> getMenusByRid(Long rid);



    //根据父级id查询所有模块
    @Select("SELECT m.id, m.name, m.targetUrl, m.parentId, m.created_at, m.updated_at FROM modules m  WHERE m.deleted_at IS NULL AND m.parentId=#{parentId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Module> selectModulesByParentId(@Param("parentId") long  parentId);


   //分页查询
   @SelectProvider(type = RoleBindSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "targetUrl", column = "targetUrl"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "roles", column = "id", many=@Many(
                    select="org.tonzoc.Mapper.RoleBindMapper.selectRoles"))
    })
    public List<Module> listPaged(@Param("params") SelectParam selectParam);


    //查询总条数
    @SelectProvider(type = RoleBindSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);

    //modules批量绑定role权限



    //添加
    @Insert("INSERT INTO rolebind(roleId, moduleId) " +
            " values(#{roleId}, #{moduleId})")
    int insert(@Param("roleId") long roleId,
               @Param("moduleId") long moduleId);

    //修改
    @Update("UPDATE rolebind  SET " +
            "roleId =#{roleId},moduleId = #{moduleId} " +
            "WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("roleId") long roleId,
               @Param("moduleId") long moduleId);
    //删除
    @Delete("delete from rolebind where roleId = #{roleId}")
    int delete(@Param("roleId") long roleId);


}