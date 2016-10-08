package cn.hzong.systech.db.mapper;

import java.util.List;

import cn.hzong.imweb.account.bean.Account;

public interface AccountMapper {
	public List<Account> findAccount(String account);
	public Account getAccount(String account);
}
