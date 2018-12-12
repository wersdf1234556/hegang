package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.Role;

import java.util.List;

public interface RoleMapper {
    @Select("SELECT id, name, created_at, updated_at FROM roles WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Role> list();

    @Insert("INSERT INTO roles(name) values(#{name})")
    int insert(@Param("name") String name);
    @Select("select id from roles where name = #{name} and deleted_at is null")
    Role selectId(@Param("name") String name);

    @SelectProvider(type = RoleSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    public List<Role> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = RoleSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);

    @Select("select r.id, r.name, r.created_at, r.updated_at from rolebind rb JOIN roles r ON rb.roleId = r.id where rb.moduleId = #{moduleId}")
    public List<Role> getRoleByModuleId(long moduleId);

    @Update("UPDATE roles SET deleted_at = current_timestamp WHERE id = #{id}")
    int delete(@Param("id") long id);

    @Update("UPDATE roles SET name = #{name}, " +
            "updated_at = current_timestamp " +
            "WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("name") String name);





}
