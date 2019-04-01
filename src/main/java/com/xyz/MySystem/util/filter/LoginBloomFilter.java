package com.xyz.MySystem.util.filter;

import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.xyz.MySystem.bean.UserBaseInfo;
import com.xyz.MySystem.dao.interf.UserMapper;
import com.xyz.MySystem.util.redis.BaseRedis;

@Component
public class LoginBloomFilter implements InitializingBean{

	private static final BloomFilter<String> FILTER = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);

	private final Logger logger = LoggerFactory.getLogger(LoginBloomFilter.class);
	
	@Autowired
	UserMapper mapper;
	
	public LoginBloomFilter() {
		logger.info("LoginBloomFilter初始化成功");
	}
	public static void put(String input) {
		FILTER.put(input);
	}
	
	public static boolean search(String input ) {
		return FILTER.mightContain(input);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		/*List<UserBaseInfo> allUser = mapper.getAllUser();
		int i = 0;
		for(i = 0 ; i < allUser.size() ; i++) {
			UserBaseInfo userBaseInfo = allUser.get(i);
			FILTER.put(userBaseInfo.getEmail());
		}
		logger.info("布隆过滤器初始化成功,一共包含"+i+"条数据");*/
	}
	
}
