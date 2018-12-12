package org.tonzoc.Common;

import org.springframework.stereotype.Component;


@Component
public class BaseApiService {
	
	//插入错误可传msg
    public ResponseBase setInsertResultError() {
    	return setResult(Constants.HTTP_RES_CODE_100,Constants.HTTP_RES_CODE_100_VALUE,null);
    }
	//插入错误可传msg
	public ResponseBase setInsertResultError1() {
		return setResult(Constants.HTTP_RES_CODE_101,Constants.HTTP_RES_CODE_101_VALUE,null);
	}
    //错误可传msg
    public ResponseBase setUpdateResultError() {
    	return setResult(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,null);
    }
	//错误可传msg
	public ResponseBase setUpdateResultError1() {
		return setResult(Constants.HTTP_RES_CODE_201,Constants.HTTP_RES_CODE_201_VALUE,null);
	}
  //删除错误可传msg
    public ResponseBase setDeleteResultError() {
    	return setResult(Constants.HTTP_RES_CODE_300,Constants.HTTP_RES_CODE_300_VALUE,null);
    }
	//删除错误可传msg
	public ResponseBase setDeleteResultError1() {
		return setResult(Constants.HTTP_RES_CODE_301,Constants.HTTP_RES_CODE_301_VALUE,null);
	}
	//删除错误可传msg
	public ResponseBase setDeleteResultError2() {
		return setResult(Constants.HTTP_RES_CODE_302,Constants.HTTP_RES_CODE_302_VALUE,null);
	}
  //查询错误可传msg
    public ResponseBase setSelectResultError() {
    	return setResult(Constants.HTTP_RES_CODE_400,Constants.HTTP_RES_CODE_400_VALUE,null);
    }
	//插入错误可传msg
	public ResponseBase setInsertResultError2() {
		return setResult(Constants.HTTP_RES_CODE_299,Constants.HTTP_RES_CODE_299_VALUE,null);
	}
	//查询错误可传msg
	public ResponseBase setSelectResultError1() {
		return setResult(Constants.HTTP_RES_CODE_499,Constants.HTTP_RES_CODE_499_VALUE,null);
	}
    
    
    
    
   //返回失败信息可传code
    public ResponseBase setResultError(Integer code,String msg) {
    	return setResult(code,msg,null);
		
    }
	
	//返回成功可以传data值
	public ResponseBase setResultSuccessWithData(Object data) {
		return setResult(Constants.HTTP_RES_CODE_0,Constants.HTTP_RES_CODE_0_VALUE,data);
		
	}
	
	//返回成功可以传msg值
	public ResponseBase setResultSuccess(String msg) {
		return setResult(Constants.HTTP_RES_CODE_0,msg,null);
		
	}
	
	//返回成功但是没有data值
	public ResponseBase setResultSuccess() {
		return setResult(Constants.HTTP_RES_CODE_0,Constants.HTTP_RES_CODE_0_VALUE,null);
		
	}
	
	
	//通用封装
	public ResponseBase setResult(Integer code,String msg,Object data) {
		return new ResponseBase(code,msg,data);
	}
}
