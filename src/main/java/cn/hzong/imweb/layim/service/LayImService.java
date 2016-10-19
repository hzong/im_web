package cn.hzong.imweb.layim.service;

import java.util.Map;

import cn.hzong.imweb.member.bean.layim.LayImMember;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

public interface LayImService {
	public BaseResult<Map<String,Object>> init(String im_account) throws BaseException;
	
	public BaseResult<LayImMember> getLayImMember(Long roomId) throws BaseException;
}
