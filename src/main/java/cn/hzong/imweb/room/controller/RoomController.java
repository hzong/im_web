package cn.hzong.imweb.room.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hzong.imweb.room.bean.Room;
import cn.hzong.imweb.room.bean.layim.LayImRoom;
import cn.hzong.imweb.room.controller.req.RoomRequest;
import cn.hzong.imweb.room.service.RoomService;
import cn.hzong.systech.common.request.AccountRequest;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomService roomService;
	
	/** 
	* getRoomInfo 
	* 获取房间信息
	* @param roomId id
	* @return {@link BaseResult}
	*/ 
	@RequestMapping(value="/getRoomInfo",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Room> getRoomInfo(RoomRequest request){
		BaseResult<Room> br_room =null;
		try{
			if(request == null && request.getRoomId() == null){
				throw new BaseException(ECode.ROOM_NOT_FOUND);
			}
			br_room = roomService.getRoomInfo(request.getRoomId());
		}catch(BaseException be){
			br_room = be.returnResult();
		}
		return br_room;
	}
	
	@RequestMapping(value="/getRooms",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<List<Room>> getRooms(AccountRequest request){
		BaseResult<List<Room>> br_room =null;
		try{
			if(request == null && request.getIm_Account() == null){
				throw new BaseException(ECode.ACCOUNT_NULL);
			}
			br_room = roomService.getRooms(request.getIm_Account());
		}catch(BaseException be){
			br_room = be.returnResult();
		}
		return br_room;
	}
	
	
	
}
