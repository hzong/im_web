package cn.hzong.imweb.member.bean.layim;

import java.util.ArrayList;
import java.util.List;

import cn.hzong.imweb.roster.bean.layim.LayImRoster;

public class LayImMember {
	private LayImRoster owner;
	
	private List<LayImRoster> list = new ArrayList<LayImRoster>();

	public LayImRoster getOwner() {
		return owner;
	}

	public void setOwner(LayImRoster owner) {
		this.owner = owner;
	}

	public List<LayImRoster> getList() {
		return list;
	}

	public void setList(List<LayImRoster> list) {
		this.list = list;
	} 
	
	
}
