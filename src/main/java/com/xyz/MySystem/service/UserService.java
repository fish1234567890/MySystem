package com.xyz.MySystem.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xyz.MySystem.dao.interf.UserMapper;

@Service
public class UserService {
	
	@Resource
	UserMapper mapper;
	public boolean loginCheck(String email,String password) {
		mapper.getUser(email, password);
		return false;
	}
}
