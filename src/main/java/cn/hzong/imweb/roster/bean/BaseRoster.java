package cn.hzong.imweb.roster.bean;

import java.io.Serializable;

public class BaseRoster  implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 
	* rosterUserId(String)
	* 好友IM账号
	*/ 
	private String rosterUserId;
	/** 
	* remark(String)
	* 还有备注名
	*/ 
	private String remark;
	public String getRosterUserId() {
		return rosterUserId;
	}
	public void setRosterUserId(String rosterUserId) {
		this.rosterUserId = rosterUserId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
