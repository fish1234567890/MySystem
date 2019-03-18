package com.xyz.MySystem.util.filter;

import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.xyz.MySystem.bean.UserBaseInfo;
import com.xyz.MySystem.dao.interf.UserMapper;

@Component
public class LoginBloomFilter {

	@Resource
	UserMapper mapper;
	private static final BloomFilter<String> FILTER = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
	  
	public LoginBloomFilter(){
		List<UserBaseInfo> allUser = mapper.getAllUser();
		for(int i = 0 ; i < allUser.size() ; i++) {
			UserBaseInfo userBaseInfo = allUser.get(i);
			FILTER.put(userBaseInfo.getEmail());
		}
		
	}
	
	public static void put(String input) {
		FILTER.put(input);
	}
	
	public static boolean search(String input ) {
		return FILTER.mightContain(input);
	}
	
}
