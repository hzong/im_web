package cn.hzong.imweb.roster.bean;

import java.io.Serializable;

public class Roster extends BaseRoster implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 
	* create_time(String)
	* 好友创建时间
	*/ 
	private String create_time;
	/** 
	* update_time(String)
	* 更新时间
	*/ 
	private String update_time;
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	
}
