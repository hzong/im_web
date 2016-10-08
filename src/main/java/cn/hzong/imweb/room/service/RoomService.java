package cn.hzong.imweb.room.service;

import java.util.List;

import cn.hzong.imweb.room.bean.Room;
import cn.hzong.imweb.room.bean.layim.LayImRoom;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;

/** 
* @className  RoomService 
* @describe  房间业务接口
* @author  hzong
* @datetime  2016年10月2日 上午10:35:01  
*/ 
public interface RoomService {
	
	/** 
	* getRooms 
	* 获取房间
	* @param im_account 房间id
	* @return {@link BaseResult}
	* @throws BaseException 抛出自定义异常
	*/ 
	public BaseResult<List<Room>> getRooms(String im_account) throws BaseException;
	
	/** 
	* getRoomInfo 
	* 获取房间详情
	* @param roomId 房间id
	* @return {@link BaseResult}
	* @throws BaseException 抛出自定义异常
 	*/ 
	public BaseResult<Room> getRoomInfo(Long roomId) throws BaseException;
	
	
}
