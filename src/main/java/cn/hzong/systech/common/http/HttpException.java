package cn.hzong.systech.common.http;


/** 
* @className  IMException 
* @describe  业务系统抛异常
* @author  hzong
* @datetime  2016年9月30日 下午3:10:32  
*/ 
public class HttpException extends Exception {
	private static final long serialVersionUID = 1L;
	public HttpException(String message) {
		super("okHttp异常："+message);
	}
	
	public HttpException(String message,Throwable throwable) {
		super("okHttp异常："+message,throwable);
	}
	
}
