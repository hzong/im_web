package cn.hzong.imweb.roster.bean;

import java.io.Serializable;

/** 
* @className  RosterGroup 
* @describe  好友分组
* @author  hzong
* @datetime  2016年10月3日 下午4:28:17  
*/ 
public class RosterGroup extends BaseRoster implements Serializable {
	/** 
	* serialVersionUID(long)
	* TODO
	*/ 
	private static final long serialVersionUID = 1L;
	/** 
	* remark(String)
	* 好友备注
	*/ 
	private String groupName;
	public String getGroupName() {
		return groupName == null ? "" : groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
