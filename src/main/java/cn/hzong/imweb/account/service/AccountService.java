package cn.hzong.imweb.account.service;

import java.util.List;

import cn.hzong.imweb.account.bean.Account;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

public interface AccountService {
	public BaseResult<List<Account>> findAccount(String account) throws BaseException;
	public BaseResult<Account> getAccount(String account) throws BaseException;
}
