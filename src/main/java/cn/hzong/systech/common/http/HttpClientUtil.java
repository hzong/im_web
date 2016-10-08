package cn.hzong.systech.common.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import cn.hzong.imweb.roster.bean.RosterGroup;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.result.BaseResult;

public class HttpClientUtil {
	private static final String encoded = "UTF-8";
	private static OkHttpClient client = new OkHttpClient();

	public final static String  HTTP(String uri,EContentType type,final Map<String, String> heads,
			final Map<String, Object> params) throws HttpException {
		String result = null;
	    Request request = null;
	    RequestBody body = null;
	    
	    if(type == EContentType.json){
	    	body = RequestBody.create(type.getContentType(),JSON.toJSONString(params));
	    }else if(type == EContentType.form){
	    	FormEncodingBuilder form = new FormEncodingBuilder();
	    	for(Map.Entry<String, Object> eso : params.entrySet()){
	    		if(eso.getValue() != null){
	    			form.addEncoded(eso.getKey(), eso.getValue().toString());
	    		}
	    	}
	    	body = form.build();
	    }
	    
	    Builder b = new Request.Builder()
	            .url(uri)
	            .post(body);
	    if(heads != null){
	    	for(Map.Entry<String, String> ess: heads.entrySet()){
	    		b.addHeader(ess.getKey(), ess.getValue());
	    	}
	    }
	    request=b.build();
	    try{
		    Response response = client.newCall(request).execute();
		    if (!response.isSuccessful()) {
		        throw new HttpException("服务器端错误: " + response);
		    }else if(response.code() == 200){
		    	result = response.body().string();
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		
	}
	
}
