package com.xyz.MySystem.dao.interf;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xyz.MySystem.bean.UserBaseInfo;

@Repository
public interface UserMapper {
	
	public UserBaseInfo getUser(String email,String password);
	
	public List<UserBaseInfo> getAllUser() ;
}
