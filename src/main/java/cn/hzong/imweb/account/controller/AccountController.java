package cn.hzong.imweb.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hzong.imweb.account.bean.Account;
import cn.hzong.imweb.account.service.AccountService;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

//@Controller
//@RequestMapping(SysConstant.URI_ROOT+"acct")
public class AccountController {
	
	@Autowired 
	private AccountService accountService;
	
	@RequestMapping(value="/getRoomInfo",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<List<Account>> findAccount(String account){
		BaseResult<List<Account>> br_room =null;
		try{
			if(account == null ){
				throw new BaseException(ECode.ACCOUNT_NULL);
			}
			
			br_room = accountService.findAccount(account);
		}catch(BaseException be){
			br_room = be.returnResult();
		}
		return br_room;
	}
}
