package cn.hzong.systech.common.http;

import com.squareup.okhttp.MediaType;

public enum EContentType {
	json("application/json; charset=utf-8"),
	form("");
	private String contentType;
	private EContentType(String ct) {
		this.contentType = ct;
	}
	
	public MediaType getContentType() {
		MediaType mt = MediaType.parse(this.contentType);
		return mt;
	}
	
}
