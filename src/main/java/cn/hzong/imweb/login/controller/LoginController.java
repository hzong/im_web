package cn.hzong.imweb.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hzong.imweb.account.service.AccountService;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.result.BaseException;

@Controller
//@RequestMapping("/")
public class LoginController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpSession hs, Model model,String account,String password){
		hs.setAttribute("account", account);
		try {
			model.addAttribute("account", account);
			model.addAttribute("password", password);
			hs.setAttribute(SysConstant.SESSION_USER, accountService.getAccount(account));
		} catch (BaseException e) {
		}
		
		return "/chat/demo.jsp";
	}
}
