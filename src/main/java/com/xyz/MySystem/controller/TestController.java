package com.xyz.MySystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@RequestMapping(value = "/myTest")
	public String test() {
		System.out.println("我进入controller了，下面准备跳转");
		return "common/login";
	}
}
