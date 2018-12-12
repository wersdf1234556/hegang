package org.tonzoc.Common;

public interface Constants {

	// 响应请求成功
	String HTTP_RES_CODE_0_VALUE = "success";
	// 插入错误错误
	String HTTP_RES_CODE_100_VALUE = "insert fial";
	//重名
	String HTTP_RES_CODE_101_VALUE = "insert rep";
	// 更新错误错误
	String HTTP_RES_CODE_200_VALUE = "update fial";
	//更新无结果
	String HTTP_RES_CODE_201_VALUE = "update fruitless";
	// 删除错误错误
	String HTTP_RES_CODE_300_VALUE = "delete fial";
	//删除无结果
	String HTTP_RES_CODE_301_VALUE = "delete fruitless";
	//数据使用中
	String HTTP_RES_CODE_302_VALUE = "delete occupation";
	// 查询错误错误
	String HTTP_RES_CODE_400_VALUE = "select fial";
    // 插入错误错误
    String HTTP_RES_CODE_299_VALUE = "insert";
    // 查询错误错误
    String HTTP_RES_CODE_499_VALUE = "select";
	// 响应请求成功code=0
	Integer HTTP_RES_CODE_0 = 0;
	// 插入错误code=100
	Integer HTTP_RES_CODE_100 = 100;
	// 插入错误（重名）code=101
	Integer HTTP_RES_CODE_101 = 101;
	// 更新错误code=200
	Integer HTTP_RES_CODE_200 = 200;
	// 更新错误（更新无结果）code=201
	Integer HTTP_RES_CODE_201 = 201;
	// 删除错误code=300
	Integer HTTP_RES_CODE_300 = 300;
	// 删除错误（查询无结果）code=301
	Integer HTTP_RES_CODE_301 = 301;
	// 删除错误（数据使用中）code=302
	Integer HTTP_RES_CODE_302 = 302;
	// 查询错误code=400
	Integer HTTP_RES_CODE_400 = 400;

	// 插入错误code=299
	Integer HTTP_RES_CODE_299 = 299;
	// 查询错误code=499
	Integer HTTP_RES_CODE_499 = 499;


}