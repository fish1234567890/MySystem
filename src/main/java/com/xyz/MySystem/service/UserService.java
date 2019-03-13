package com.xyz.MySystem.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.MySystem.bean.UserBaseInfo;
import com.xyz.MySystem.dao.interf.UserMapper;
import com.xyz.MySystem.util.redis.BaseRedis;

@Service
public class UserService {
	
	@Resource
	UserMapper mapper;
	@Autowired 
	BaseRedis redis;
	public boolean loginCheck(String email,String password) {
		String email_redis = redis.get(email);
		if(password.equals(email_redis)) {
			return true;
		}
		if(email_redis == null) {
			UserBaseInfo user = mapper.getUser(email, password);
			redis.set(email, password);
			if(user == null) {
				return false; 
			}
			return true;
		}
		
		return false;
	}
}
