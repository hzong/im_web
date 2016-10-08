package cn.hzong.imweb.room.bean;

import java.io.Serializable;

public class Room implements Serializable {
	/** 
	* serialVersionUID(long)
	* TODO
	*/ 
	private static final long serialVersionUID = 1L;
	/** 
	* id(String)
	* 房间id
	*/ 
	private String id;
	/** 
	* name(String)
	* 房间名称
	*/ 
	private String name;
	/** 
	* userId(String)
	* 创建人
	*/ 
	private String userId;
	/** 
	* create_date(String)
	* 创建时间
	*/ 
	private String createDate;
	/** 
	* announcement(String)
	* 公共
	*/ 
	private String announcement;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
}
