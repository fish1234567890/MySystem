package com.xyz.MySystem.controller;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.xyz.MySystem.bean.LoginReq;
import com.xyz.MySystem.bean.LoginResp;
import com.xyz.MySystem.service.UserService;
import com.xyz.MySystem.util.redis.BaseRedis;

@Controller
@RequestMapping(value="/user")
public class LoginController {

	@Autowired
	UserService service;
	
	private final Logger logger = LoggerFactory.getLogger(BaseRedis.class);
	
	@ResponseBody
	@RequestMapping(value="/login")
	public LoginResp login(@RequestBody LoginReq req) {
		logger.info("用户登陆" +req);
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
		 List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
	        for(GarbageCollectorMXBean b : l) {
	            System.out.println(b.getName());
	        }

	}
}
