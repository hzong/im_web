package cn.hzong.roster;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.hzong.BaseJunit4Test;
import cn.hzong.common.IBL;
import cn.hzong.imweb.roster.bean.Roster;
import cn.hzong.imweb.roster.service.RosterService;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

public class RosterServiceTest extends BaseJunit4Test {
	@Autowired
	private RosterService rosterService;
	@Test
	public void getRoster(){
//		BaseResult<List<Roster>> room = null;
//		try {
////			room = rosterService.getRosters(IBL.im_account);
//		} catch (BaseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(JSON.toJSONString(room));
	}
}
