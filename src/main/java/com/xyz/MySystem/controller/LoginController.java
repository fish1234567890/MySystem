package com.xyz.MySystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.xyz.MySystem.bean.LoginReq;
import com.xyz.MySystem.bean.LoginResp;
import com.xyz.MySystem.service.UserService;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping(value="/user")
public class LoginController {

	@Autowired
	UserService service;
	
	@ResponseBody
	@RequestMapping(value="/login")
	public LoginResp login(@RequestBody LoginReq req) {
		LoginResp resp = new LoginResp();
		//校验用户传入的用户名和密码格式
		if(StringUtils.isEmpty(req.getEmail()) && StringUtils.isEmpty(req.getPassword())) {
			resp.setCode("101");
			resp.setMsg("必填项为空");
			return resp;
		}
		//校验用户名和密码的正确性
		boolean flag = service.loginCheck(req.getEmail(),req.getPassword());
		
		if(flag == true) {
			resp.setCode("200");
			resp.setMsg("成功");
			return resp;
		}
		return resp;
	}
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("106.14.219.152",6379);
		jedis.auth("YFY0922.");
		jedis.set("bill", "100");
		String string = jedis.get("bill");
		System.out.println(string);
	}
}
