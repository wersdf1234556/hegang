package org.tonzoc.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.tonzoc.Model.MeterageList;

import java.util.List;

public interface MeterageListMapper {

    @Select("SELECT id, listNo, itemName, unit, total, price, balance, parentId, chapter, created_at, updated_at FROM meterageList WHERE deleted_at IS NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "listNo", column = "listNo"),
            @Result(property = "itemName", column = "itemName"),
            @Result(property = "unit", column = "unit"),
            @Result(property = "total", column = "total"),
            @Result(property = "price", column = "price"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "chapter", column = "chapter"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<MeterageList> list();

    @Select("SELECT id, listNo, itemName, unit, total, price, balance, parentId, chapter, created_at, updated_at FROM meterageList WHERE deleted_at IS NULL AND parentId = #{parentId} ORDER BY listNo asc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "listNo", column = "listNo"),
            @Result(property = "itemName", column = "itemName"),
            @Result(property = "unit", column = "unit"),
            @Result(property = "total", column = "total"),
            @Result(property = "price", column = "price"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "chapter", column = "chapter"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<MeterageList> listByParentId(@Param("parentId") String parentId);

    @Select("SELECT id, listNo, itemName, unit, total, price, balance, parentId, chapter, created_at, updated_at FROM meterageList WHERE id in(select meterageListId from a15s WHERE meterageListId=#{meterageListId}) AND deleted_at IS NULL ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "listNo", column = "listNo"),
            @Result(property = "itemName", column = "itemName"),
            @Result(property = "unit", column = "unit"),
            @Result(property = "total", column = "total"),
            @Result(property = "price", column = "price"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "parentId", column = "parentId"),
            @Result(property = "chapter", column = "chapter"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    List<MeterageList> selectById(@Param("id") long id);
}
