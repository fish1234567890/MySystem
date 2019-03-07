package com.xyz.MySystem.bean;

import org.springframework.stereotype.Component;

@Component
public class LoginResp {
	private String code;
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public LoginResp() {}
	
	public LoginResp(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	
}
