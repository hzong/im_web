package cn.hzong.imweb.find.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hzong.imweb.account.bean.Account;
import cn.hzong.imweb.account.service.AccountService;
import cn.hzong.imweb.find.bean.Find;
import cn.hzong.imweb.find.define.FindType;
import cn.hzong.imweb.find.service.FindService;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Service
public class FindServiceImpl implements FindService {
	@Autowired
	private AccountService accountService;
	
	public BaseResult<List<Find>> findTypeRelust(Integer code,String text) throws BaseException{
		BaseResult<List<Find>> reslut = new BaseResult<List<Find>>(ECode.SUCCESS);
		try{
			FindType ft = FindType.paseFindType(code);
			
			List<Find> l_find = new ArrayList<Find>();
			Find f = null;
			
			if(ft == FindType.User){
				List<Account> l_act = accountService.findAccount(text).getData();
				for(Account act : l_act){
					f = new Find();
					f.setAccount(act.getAccount());
					f.setImg(act.getHeadPortrait());
					f.setNick(act.getNick());
					f.setRemark(act.getSign());
					l_find.add(f);
				}
			}else{
				
			}
			
			reslut.setData(l_find);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		return reslut;
	}
}
