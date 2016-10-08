package cn.hzong.imweb.roster.service;

import java.util.List;

import cn.hzong.imweb.roster.bean.Roster;
import cn.hzong.imweb.roster.bean.RosterGroup;
import cn.hzong.imweb.roster.bean.layim.LayImRosterGroup;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

/** 
* @className  RosterService 
* @describe  好友业务接口
* @author  hzong
* @datetime  2016年10月2日 上午11:02:40  
*/ 
public interface RosterService {
	/** 
	* getRosters 
	* 获取好友列表
	* @param im_account 用户关联的好友账号
	* @throws BaseException 自定义异常
	* @return
	*/ 
	BaseResult<List<RosterGroup>> getRosters(String im_account) throws BaseException;
	
	
	
}
