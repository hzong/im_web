package cn.hzong.imweb.roster.bean.layim;

public class LayImRoster {
	/** 
	* username(String)
	* 用户名称
	*/ 
	private String username;
	/** 
	* id(String)
	* 唯一
	*/ 
	private String id;
	/** 
	* avatar(String)
	* 头像
	*/ 
	private String avatar;
	/** 
	* sign(String)
	* 签名
	*/ 
	private String sign;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
