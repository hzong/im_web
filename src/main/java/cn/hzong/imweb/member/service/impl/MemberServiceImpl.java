package cn.hzong.imweb.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.hzong.imweb.member.bean.Member;
import cn.hzong.imweb.member.service.MemberService;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.http.HttpClientUtil;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Service
public class MemberServiceImpl implements MemberService {
	
	private static final String url_Members= SysConstant.IM_URL+"member/getMembers";
	public BaseResult<List<Member>> getMember(Long roomId) throws BaseException{
		BaseResult<List<Member>> br = null; 
		try{
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("roomId", roomId);
			String text = HttpClientUtil.HTTP(url_Members, SysConstant.TYPE, SysConstant.IM_HEAD, params);
			br  = JSON.parseObject(text, new TypeReference<BaseResult<List<Member>>>(){});
			if(!br.getResult()){
				throw new BaseException(ECode.SERVER_ERROR);
			}
		}catch(Exception be){
			be.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		return br;
	}
}
