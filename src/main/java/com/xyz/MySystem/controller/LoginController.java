package com.xyz.MySystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.xyz.MySystem.bean.LoginReq;
import com.xyz.MySystem.bean.LoginResp;
import com.xyz.MySystem.service.UserService;

@Controller
@RequestMapping(value="/user")
public class LoginController {

	@Autowired
	UserService service;
	
/*	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public String index() {
		return "";
	}*/
	@ResponseBody
	@RequestMapping(value="/login")
	public LoginResp login(@RequestBody LoginReq req) {
		LoginResp resp = new LoginResp();
		if(StringUtils.isEmpty(req.getEmail()) && StringUtils.isEmpty(req.getPassword())) {
			resp.setCode("101");
			resp.setMsg("必填项为空");
			return resp;
		}
		boolean flag = service.loginCheck(req.getEmail(),req.getPassword());
		if(flag == true) {
			resp.setCode("200");
			resp.setMsg("成功");
			return resp;
		}
		return resp;
	}
	
	public static void main(String[] args) {
		LoginReq req = new LoginReq();
		req.setEmail("74641174@qq.com");
		req.setPassword("123123");
		String string = JSON.toJSONString(req);
		System.out.println(string);
	}
}
