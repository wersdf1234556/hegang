package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.Company;
import org.tonzoc.Model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/11/11 0011.
 */
public interface CompanyMapper {
    //根据公司名称查询
    @SelectProvider(type = CompanySelectProvider.class, method = "listByName")
//    @Select("SELECT id, name, contact, tel, created_at, updated_at FROM company WHERE name like '%${name}%' and deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "contact", column = "contact"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Company> listByName(@Param("params") SelectParam selectParam);
    //根据条件查询公司列表
    @SelectProvider(type = CompanySelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "contact", column = "contact"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Company> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = CompanySelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);
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
    int listById(@Param("id") long id);
    //添加公司
    @Insert("INSERT INTO company(name, contact, tel)" +
            " values(#{name}, #{contact}, #{tel})")
    int insert(@Param("name") String name,
               @Param("contact") String contact,
               @Param("tel") String tel);

    //修改公司
    @Update("UPDATE company SET name = #{name}, " +
            "contact = #{contact}, " +
            "tel = #{tel} " +
            "WHERE id = #{id}")
    int update(@Param("id") long id,
               @Param("name") String name,
               @Param("contact") String contact,
               @Param("tel") String tel);
    //删除公司
    @Update("UPDATE company SET deleted_at = current_timestamp WHERE id = #{id}")
    int delete(@Param("id") long id);

}

