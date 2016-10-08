package cn.hzong.room;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.hzong.BaseJunit4Test;
import cn.hzong.common.IBL;
import cn.hzong.imweb.room.bean.Room;
import cn.hzong.imweb.room.service.RoomService;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

public class RoomServiceTest extends BaseJunit4Test {
	@Resource
	private RoomService roomService;
	
	
	@Test // 标明是测试方法
//	@Transactional // 标明此方法需使用事务
//	@Rollback(true) // 标明使用完此方法后事务不回滚,true时为回滚
	public void getRoomInfo() {
		BaseResult<Room> room = null;
		try {
			room = roomService.getRoomInfo(IBL.roomId);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(room));
	}
	
	@Test // 标明是测试方法
	public void getRooms() {
		BaseResult<List<Room>> room = null;
		try {
			room = roomService.getRooms(IBL.im_account);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(room));
	}
}
