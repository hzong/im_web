package cn.hzong.systech.common.result;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {
	private BaseResult() {
	}
	public BaseResult(ECode code) {
		this.code = code.getCode();
		this.message = code.getDescribe();
		this.result = code.getResult();
	}
	private static final long serialVersionUID = 1L;
	private boolean result;
	private int code;
	private String message;
	private T data;
	
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
