package cn.hzong.imweb.find.define;

public enum FindType {
	Group(1,"群"),
	User(0,"用户");
	private int code;
	private String msg;
	private FindType(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
	public static FindType paseFindType(int code){
		for(FindType ft : FindType.values()){
			if(ft.code == code){
				return ft;
			}
		}
		return null;
	}
}
