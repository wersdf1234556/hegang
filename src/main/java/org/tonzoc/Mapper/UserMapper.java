package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.Role;
import org.tonzoc.Model.User;

import java.util.List;

public interface UserMapper {
    @Select("SELECT id, name, created_at, updated_at FROM roles WHERE id = #{id} and deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    Role selectRole(@Param("id") long id);

    @Select("SELECT id, code, roleId, name, staffName, mobile, birthday, gender, email, created_at, updated_at FROM users WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "code", column = "code"),
            @Result(property = "roleId", column = "roleId"),
            @Result(property = "name", column = "name"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "email", column = "email"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "role", column = "roleId",one = @One(select = "org.tonzoc.Mapper.UserMapper.selectRole"))
    })
    List<User> list();

    @Select("SELECT id, code, roleId, name, staffName, mobile, birthday, gender, email, created_at, updated_at FROM users WHERE deleted_at IS NULL AND name = #{name} AND password = #{password}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "code", column = "code"),
            @Result(property = "roleId", column = "roleId"),
            @Result(property = "name", column = "name"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "email", column = "email"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<User> validate(@Param("name") String name, @Param("password") String password);

    @Update("UPDATE users SET password = #{password} WHERE deleted_at IS NULL AND name = #{name}")
    int setPassword(@Param("name") String name, @Param("password") String password);

    @Insert("INSERT INTO users(code, roleId, name, staffName, mobile, birthday, gender, email)" +
            " values(#{code}, #{roleId}, #{name}, #{staffName}, #{mobile}, #{birthday}, #{gender}, #{email})")
    int insert(@Param("code") String code,
               @Param("roleId") long roleId,
               @Param("name") String name,
               @Param("staffName") String staffName,
               @Param("mobile") String mobile,
               @Param("birthday") String birthday,
               @Param("gender") long gender,
               @Param("email") String email);

    @SelectProvider(type = UserSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "code", column = "code"),
            @Result(property = "roleId", column = "roleId"),
            @Result(property = "name", column = "name"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "email", column = "email"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    public List<User> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = UserSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);
    @Select("SELECT code FROM users WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "code", column = "code"),
    })
    List<User> listCode();

    @Update("UPDATE users SET deleted_at = current_timestamp WHERE id = #{id}")
    int delete(@Param("id") long id);

    @Update("UPDATE users SET code = #{code}, " +
            "roleId = #{roleId}, " +
            "name = #{name}, " +
            "staffName = #{staffName}, " +
            "mobile = #{mobile}, " +
            "birthday = #{birthday}, " +
            "gender = #{gender}, " +
            "email = #{email}, " +
            "updated_at = current_timestamp " +
            "WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("code") String code,
               @Param("roleId") long roleId,
               @Param("name") String name,
               @Param("staffName") String staffName,
               @Param("mobile") String mobile,
               @Param("birthday") String birthday,
               @Param("gender") long gender,
               @Param("email") String email);

//    @Update("UPDATE users SET code = #{code}, " +
//            "updated_at = current_timestamp " +
//            "WHERE id = #{id}")
//    User update1(
//               @Param("code") String code);

}
