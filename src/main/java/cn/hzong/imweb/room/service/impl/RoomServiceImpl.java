package cn.hzong.imweb.room.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.hzong.imweb.room.bean.Room;
import cn.hzong.imweb.room.service.RoomService;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.http.HttpClientUtil;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Service
public class RoomServiceImpl implements RoomService {
	Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
	
	
	private static final String url_getRooms = SysConstant.IM_URL+"room/getRooms";
	public BaseResult<List<Room>> getRooms(String im_account) throws BaseException{
		BaseResult<List<Room>> br = null; 
		try{
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("account", im_account);
			String text = HttpClientUtil.HTTP(url_getRooms, SysConstant.TYPE, SysConstant.IM_HEAD, params);
			br  = JSON.parseObject(text, new TypeReference<BaseResult<List<Room>>>(){});
			
		}catch(Exception be){
			be.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		return br;
	}
	
	public BaseResult<Room> getRoomInfo(Long roomId) throws BaseException{
		BaseResult<Room> br = null; 
		try{
//			Room rooms = roomMapper.getRoomInfo(roomId);
			br = new BaseResult<Room>(ECode.SUCCESS);
//			br.setData(rooms);
		}catch(Exception be){
			be.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		return br;
	}
	

}
