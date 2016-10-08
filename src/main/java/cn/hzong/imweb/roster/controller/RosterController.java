package cn.hzong.imweb.roster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hzong.imweb.roster.bean.Roster;
import cn.hzong.imweb.roster.bean.RosterGroup;
import cn.hzong.imweb.roster.bean.layim.LayImRoster;
import cn.hzong.imweb.roster.bean.layim.LayImRosterGroup;
import cn.hzong.imweb.roster.service.RosterService;
import cn.hzong.systech.common.request.AccountRequest;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Controller
@RequestMapping("/roster")
public class RosterController {
	@Autowired
	private RosterService rosterService;
	
	@RequestMapping(value="/getRosters",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<List<RosterGroup>> getRosters(AccountRequest request){
		BaseResult<List<RosterGroup>> br_roster =null;
		try{
			if(request == null && request.getIm_Account() == null){
				throw new BaseException(ECode.ACCOUNT_NULL);
			}
			br_roster = rosterService.getRosters(request.getIm_Account());
		}catch(BaseException be){
			br_roster = be.returnResult();
		}
		return br_roster;
	}
	
	
}
