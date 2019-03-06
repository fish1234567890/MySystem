package com.xyz.MySystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.druid.util.StringUtils;
import com.xyz.MySystem.bean.LoginReq;
import com.xyz.MySystem.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService service;
	
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public String index() {
		return "common/loginDemo/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(LoginReq req) {
		if(StringUtils.isEmpty(req.getEmail()) && StringUtils.isEmpty(req.getPassword())) {
			return "common/loginDemo/login";
		}
		boolean flag = service.loginCheck(req.getEmail(),req.getPassword());
		if(flag == true) {
			return "common/indexDemo/index";
		}else {
			return "common/loginDemo/login";
		}
		
	}
}
