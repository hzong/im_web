package cn.hzong.member;


import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.hzong.BaseJunit4Test;
import cn.hzong.imweb.member.service.MemberService;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.result.BaseException;

public class MemberServiceTest extends BaseJunit4Test {
	@Resource
	private MemberService memberService;
	
	
	@Test // 标明是测试方法
	public void getMember() {
		String room = "";
//		SysConstant.IM_URL = "127.0.0.1:8888/im_web/";
		try {
			room = JSON.toJSONString(memberService.getMember(266l));
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(room);
	}
}
