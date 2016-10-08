package cn.hzong.imweb.find.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hzong.imweb.find.bean.Find;
import cn.hzong.imweb.find.service.FindService;
import cn.hzong.systech.common.constant.SysConstant;
import cn.hzong.systech.common.result.BaseException;
import cn.hzong.systech.common.result.BaseResult;
import cn.hzong.systech.common.result.ECode;

@Controller
@RequestMapping(SysConstant.URI_ROOT)
public class FindController {
	@Autowired
	private FindService findService;
	
	@RequestMapping(value="toFindMain",method=RequestMethod.GET)
	public String toFindMain(Model model){
		return SysConstant.ROOT+"find/find.jsp";
	}
	
	@RequestMapping(value="find",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<List<Find>> findService(Integer code,String text){
		BaseResult<List<Find>> br_lf = null;
		try {
			if(code == null){
				throw new BaseException(ECode.TYPE_CODE_NULL);
			}
			br_lf = findService.findTypeRelust(code, text);
		} catch (BaseException e) {
			br_lf = e.returnResult();
		}
		return br_lf;
	}
}
