package cn.hzong.imweb.roster.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.hzong.imweb.roster.bean.RosterGroup;
import cn.hzong.imweb.roster.service.RosterService;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.http.HttpClientUtil;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Service
public class RosterServiceImpl implements RosterService {
	
	private static final String url_getRosters = SysConstant.IM_URL+"roster/getRosters"; 
	public BaseResult<List<RosterGroup>> getRosters(String im_account) throws BaseException {
		BaseResult<List<RosterGroup>> br = null; 
		try{
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("account", im_account);
			String text = HttpClientUtil.HTTP(url_getRosters, SysConstant.TYPE, SysConstant.IM_HEAD, params);
			br  = JSON.parseObject(text, new TypeReference<BaseResult<List<RosterGroup>>>(){});
			
		}catch(Exception be){
			be.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		return br;
	}

	

}
