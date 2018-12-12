package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.*;
import org.tonzoc.Common.SelectParam;
import org.tonzoc.Model.A15;
import org.tonzoc.Model.Company;
import org.tonzoc.Model.Province;
import org.tonzoc.Model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/11/19 0019.
 */
public interface ProvinceMapper {
    //根据
    @Select("SELECT id, name, code, created_at, updated_at FROM provinces WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Province> list();

    @SelectProvider(type = ProvinceSelectProvider.class, method = "listPaged")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<Province> listPaged(@Param("params") SelectParam selectParam);

    @SelectProvider(type = ProvinceSelectProvider.class, method = "countPaged")
    int countPaged(@Param("params") SelectParam selectParam);
    //根据
    @Select("SELECT id, name, code FROM provinces WHERE id=#{id} and deleted_at is null")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    int listById(@Param("id") long id);
}
