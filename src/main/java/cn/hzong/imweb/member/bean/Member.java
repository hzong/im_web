package cn.hzong.imweb.member.bean;

/** 
* Member 
* 群成员
* @author  hzong
* @version 1.0
* @datetime  2016年10月11日 下午4:52:50  
*/ 
public class Member {
	/** 
	* userId(String)
	* 成员账号
	*/ 
	private String userId;
	/** 
	* type(String)
	* 成员岗位
	*/ 
	private String type;
	/** 
	* remark(String)
	* 成员 备注
	*/ 
	private String remark;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
