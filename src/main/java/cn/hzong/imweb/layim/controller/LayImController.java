package cn.hzong.imweb.layim.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hzong.imweb.layim.service.LayImService;
import cn.hzong.imweb.member.bean.layim.LayImMember;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Controller
@RequestMapping("/layim")
public class LayImController {
	@Autowired
	private LayImService layImService;
	
	@RequestMapping(value="/LayInit",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Map<String,Object>> init(String account) {
		BaseResult<Map<String,Object>> br = null;
		try {
			if(account == null){
				throw new BaseException(ECode.ACCOUNT_NULL);
			}
			br = layImService.init(account);
		} catch (BaseException e) {
			e.printStackTrace();
			br =e.returnResult();
		}
		return br;
	}
	
	@RequestMapping(value="/getLayImMember",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<LayImMember> getLayImMember(Long id) {
		BaseResult<LayImMember> br = null;
		try {
			if(id == null){
				throw new BaseException(ECode.ROOM_NOT_FOUND);
			}
			br = layImService.getLayImMember(id);
		} catch (BaseException e) {
			e.printStackTrace();
			br =e.returnResult();
		}
		return br;
	}
}
