package cn.hzong.systech.db.mapper;

import java.util.List;

import cn.hzong.imweb.roster.bean.Roster;
import cn.hzong.imweb.roster.bean.RosterGroup;

public interface RosterMapper {
	
	public List<RosterGroup> getRoster(String im_account);
}
