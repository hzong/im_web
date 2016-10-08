package cn.hzong.imweb.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hzong.imweb.account.bean.Account;
import cn.hzong.imweb.account.service.AccountService;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;
import cn.hzong.systech.db.mapper.AccountMapper;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	
	public BaseResult<List<Account>> findAccount(String account) throws BaseException{
		BaseResult<List<Account>> reslut = new BaseResult<List<Account>>(ECode.SUCCESS);
		try{
			reslut.setData(accountMapper.findAccount(account));
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		
		return reslut;
	}
	
	public BaseResult<Account> getAccount(String account) throws BaseException{
		BaseResult<Account> reslut = new BaseResult<Account>(ECode.SUCCESS);
		try{
			reslut.setData(accountMapper.getAccount(account));
			if(reslut.getData() == null){
				throw new BaseException(ECode.ACCOUNT_NULL);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		
		return reslut;
	}
}
