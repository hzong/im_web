package cn.hzong.imweb.find.service;

import java.util.List;

import cn.hzong.imweb.find.bean.Find;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

/** 
* FindService （类型查询）
* @author  hzong
* @version 1.0
* @datetime  2016年10月7日 下午6:35:24  
*/ 
public interface FindService {
	public BaseResult<List<Find>> findTypeRelust(Integer code,String text) throws BaseException;
}
