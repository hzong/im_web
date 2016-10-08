package cn.hzong.systech.common.constant;

import java.util.HashMap;
import java.util.Map;

import cn.hzong.common.config.ConfigFactory;
import cn.hzong.common.config.Configuration;
import cn.hzong.systech.common.encrypt.MD5Util;
import cn.hzong.systech.common.http.EContentType;

public class SysConstant {
	private static final String SYS_TAG = "hz_chatim";
	private static final String CUR_TIME = "hz_chatim";
	private static final String CTIME = "1475742789000";
	
	
	private static final Configuration conf = ConfigFactory.CreateSysconFig("configure/config.properties", false);
	
	public static final String IM_URL = conf.getValue("im_url");
	public static final EContentType TYPE = EContentType.form;
	public static final Map<String,String> IM_HEAD = getHead();
	
	
	public static final String SESSION_USER = "IM_USER";
	public static final String URI_ROOT ="/hz/";
	public static final String ROOT= "/chat/";
	
	private static Map<String,String> getHead(){
		Map<String,String> m_ss = new HashMap<String, String>();
		m_ss.put("sysTag", SYS_TAG);
		m_ss.put("CurTime", CUR_TIME);
		m_ss.put("CTime",CTIME);
		m_ss.put("SCCM", MD5Util.calMd5(SYS_TAG+CUR_TIME+CTIME));
		return m_ss;
	}
}
