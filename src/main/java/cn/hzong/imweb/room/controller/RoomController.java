package cn.hzong.imweb.room.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hzong.imweb.account.service.AccountService;
import cn.hzong.imweb.room.service.RoomService;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.result.BaseException;

@Controller
@RequestMapping(SysConstant.URI_ROOT)
public class RoomController {
	@Autowired
	RoomService roomService;
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="toRoomSetMain",method=RequestMethod.GET)
	public String toRoomSetMain(Model model){
		try {
			model.addAttribute("user_account", accountService.findAccount("")) ;
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SysConstant.ROOT+"group/group_set.jsp";
	}
	
	
}
