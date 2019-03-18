package com.xyz.MySystem.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.MySystem.bean.UserBaseInfo;
import com.xyz.MySystem.dao.interf.UserMapper;
import com.xyz.MySystem.util.filter.LoginBloomFilter;
import com.xyz.MySystem.util.redis.BaseRedis;

@Service
public class UserService {
	
	@Resource
	UserMapper mapper;
	@Autowired 
	BaseRedis redis;
	public boolean loginCheck(String email,String password) {
		//先经过一层布隆过滤器
		LoginBloomFilter.search(email);
		//经过布隆过滤器之后打到redis中
		String email_redis = redis.get(email);
		if(password.equals(email_redis)) {
			return true;
		}
		//如果redis中不存在直接去数据库中搜索
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
