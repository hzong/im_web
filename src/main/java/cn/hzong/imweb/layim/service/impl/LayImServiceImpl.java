package cn.hzong.imweb.layim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hzong.imweb.account.bean.Account;
import cn.hzong.imweb.account.service.AccountService;
import cn.hzong.imweb.layim.service.LayImService;
import cn.hzong.imweb.room.bean.Room;
import cn.hzong.imweb.room.bean.layim.LayImRoom;
import cn.hzong.imweb.room.service.RoomService;
import cn.hzong.imweb.roster.bean.RosterGroup;
import cn.hzong.imweb.roster.bean.layim.LayImRoster;
import cn.hzong.imweb.roster.bean.layim.LayImRosterGroup;
import cn.hzong.imweb.roster.service.RosterService;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Service
public class LayImServiceImpl implements LayImService {
	@Autowired
	private RoomService roomService;
	@Autowired
	private RosterService rosterService;
	@Autowired
	private AccountService accountService;
	
	
	public BaseResult<Map<String,Object>> init(String im_account) throws BaseException{
		BaseResult<Map<String,Object>> result = new BaseResult<Map<String,Object>>(ECode.SUCCESS);
		try{
			Map<String,Object> m_so = new HashMap<String, Object>();
			m_so.put("friend", getLayImRosters(im_account));
			m_so.put("group", getLayImRoom(im_account));
			result.setData(m_so);
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		return result;
	}
	
	public List<LayImRosterGroup> getLayImRosters(String im_account) throws BaseException{
		List<LayImRosterGroup> l_lirg = new ArrayList<LayImRosterGroup>();
		try{
			 List<RosterGroup> l_rooms = rosterService.getRosters(im_account).getData();
			
			
			
			LayImRosterGroup lirg = null;
			Long val = null;
			for(RosterGroup r : l_rooms){
				if(val != r.getRgId()){
					lirg = new LayImRosterGroup();
					lirg.setGroupname(r.getGroupName());
					lirg.setId(r.getRgId());
					l_lirg.add(lirg);
					val = r.getRgId();
				}
			}
			
			
			LayImRoster lir = null;
			RosterGroup rg = null;
			for(LayImRosterGroup o_lirg : l_lirg){
				Iterator<RosterGroup> irg = l_rooms.iterator();
				
				while(irg.hasNext()){
					rg = irg.next();
					if( rg.getRosterUserId() != null &&rg.getRgId() == o_lirg.getId()){
						Account act = accountService.getAccount(rg.getRosterUserId().split("_")[2]).getData();
						lir = new LayImRoster();
						lir.setId(rg.getRosterUserId());
						lir.setUsername(act.getNick());
						lir.setAvatar(act.getHeadPortrait());
						lir.setSign(act.getSign());
						o_lirg.getList().add(lir);
						irg.remove();
					}
				}
			}
			
		}catch(Exception be){
			be.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		return l_lirg;
	}
	
	public List<LayImRoom> getLayImRoom(String im_account) throws BaseException{
		List<LayImRoom> l_lir = new ArrayList<LayImRoom>();
		try{
			
			List<Room> l_rooms = roomService.getRooms(im_account).getData();
			
			for(Room r : l_rooms){
				LayImRoom lir = new LayImRoom();
				lir.setId(r.getId());
				lir.setAvatar("http://tp4.sinaimg.cn/1345566427/180/5730976522/0");
				lir.setGroupname(r.getName());
				l_lir.add(lir);
			}
			
		}catch(Exception be){
			be.printStackTrace();
			throw new BaseException(ECode.SERVER_ERROR);
		}
		return l_lir;
	}
}
