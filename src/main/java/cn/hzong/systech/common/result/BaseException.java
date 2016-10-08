package cn.hzong.systech.common.result;


/** 
* @className  IMException 
* @describe  业务系统抛异常
* @author  hzong
* @datetime  2016年9月30日 下午3:10:32  
*/ 
public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;
	private ECode code;
	public BaseException(ECode code) {
		super(code.getDescribe());
		this.code = code;
	}
	
	public BaseException(ECode code,Throwable throwable) {
		super(code.getDescribe(),throwable);
		this.code = code;
	}
	
	@SuppressWarnings("rawtypes")
	public  BaseResult returnResult(){
		BaseResult result = new BaseResult(code);
		return result;
	}
	
}
