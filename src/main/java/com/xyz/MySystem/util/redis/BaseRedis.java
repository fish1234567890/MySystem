package com.xyz.MySystem.util.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class BaseRedis {

	private final Logger logger = LoggerFactory.getLogger(BaseRedis.class);
	
	private JedisPool pool ;
	
	public BaseRedis () {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		pool = new JedisPool(config, "106.14.219.152", 6379,3000,"YFY0922.");
		logger.info("redis 连接成功");
	}
	
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.get(key);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			jedis.close();
		}
	}
	public void set(String key , String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String set = jedis.set(key,value);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
	}

	
	
	
}
