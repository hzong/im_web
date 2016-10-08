package cn.hzong.imweb.room.controller.req;

import cn.hzong.systech.common.request.BaseRequest;

public class RoomRequest extends BaseRequest {
	private Long roomId;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
}
