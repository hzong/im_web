package cn.hzong.systech.common.result;

/** 
* @className  ECode 
* @describe  Baseres
* @author  hzong
* @datetime  2016年9月30日 下午2:49:44  
*/ 
public enum ECode {
	SUCCESS(0,"成功",true),
	SYSTAG_NOT_FOUND(50,"没有找到系统标记",false),
	INVALID_REQUEST(51,"无效的请求",false),
	ROOM_NOT_FOUND(201,"没有找到房间",false),
	SERVER_ERROR(500,"服务器错误",false),
	ACCOUNT_NULL(300,"账号为空",false),
	
	TYPE_CODE_NULL(1000,"类型代码为空",false);
	
	
	private int code;
	private String describe;
	private boolean result;
	private ECode(int code,String describe,boolean result) {
		this.code = code;
		this.describe = describe;
		this.result = result;
	}
	
	public int getCode() {
		return code;
	}
	public String getDescribe() {
		return describe;
	}
	
	public boolean getResult(){
		return result;
	}
	
}
