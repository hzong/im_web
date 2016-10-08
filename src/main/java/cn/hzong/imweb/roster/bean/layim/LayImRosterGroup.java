package cn.hzong.imweb.roster.bean.layim;

import java.util.ArrayList;
import java.util.List;

public class LayImRosterGroup {
	private String groupname;
	private Long id;
	private List<LayImRoster> list = new ArrayList<LayImRoster>();
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<LayImRoster> getList() {
		return list;
	}
	public void setList(List<LayImRoster> list) {
		this.list = list;
	}
	
	
}
