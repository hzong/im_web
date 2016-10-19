package cn.hzong.imweb.member.service;

import java.util.List;

import cn.hzong.imweb.member.bean.Member;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

public interface MemberService {
	
	public BaseResult<List<Member>> getMember(Long roomId) throws BaseException;

}
