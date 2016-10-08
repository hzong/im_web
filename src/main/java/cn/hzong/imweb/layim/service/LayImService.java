package cn.hzong.imweb.layim.service;

import java.util.Map;

import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

public interface LayImService {
	public BaseResult<Map<String,Object>> init(String im_account) throws BaseException;
}
