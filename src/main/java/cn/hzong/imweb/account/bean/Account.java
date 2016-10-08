package cn.hzong.imweb.account.bean;

/** 
* Account (账号)
* @author  hzong
* @version 1.0
* @datetime  2016年10月7日 下午6:06:35  
*/ 
public class Account {
	private Long id;
	private String account;
	private String nick;
	private String headPortrait;
	/** 
	* sign(String)
	* 签名
	*/ 
	private String sign;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	
	
}
