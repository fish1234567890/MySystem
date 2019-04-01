package com.xyz.MySystem.util.redis;

import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author XuChongGuang
 *
 */
@Component
public class BaseRedis {

	private final Logger logger = LoggerFactory.getLogger(BaseRedis.class);
	
	private JedisPool pool ;
	
	public BaseRedis () {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		pool = new JedisPool(config, "106.14.219.152", 6379,3000,"YFY0922.");
		logger.info("redis 连接成功");
	}
	

	/**
	 * 
	 * @param key  根据这个key值取出想要的value值
	 * @return     根据给定的key取出来的value值
	 * 
	 * 获取字符串类型的键值对
	 */
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.get(key);
		}catch(Exception e) {
			logger.error("redis [get "+key+"] error",e);
			return "-1";
		}finally {
			jedis.close();
		}
	}
	
	
	/**
	 * @param key      新增键值对的key
	 * @param value    新增键值对的value
	 * 
	 * 新增一个String类型的键值对，如果key值重复会进行覆盖更新
	 */
	public String set(String key , String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String set = jedis.set(key,value);
			return set;
		}catch(Exception e) {
			logger.error("redis [set "+key+" : " +value+"] error",e);
			return "-1";
		}finally {
			jedis.close();
		}
	}

	
	/**
	 * @param key      新增键值对的key
	 * @param value    新增键值对的value
	 * @return         是否设置成功     0为失败，1为成功，报错为-1
	 * 
	 *	只在键 key 不存在的情况下， 将键 key 的值设置为 value 。
	 *	若键 key 已经存在， 则 SETNX 命令不做任何动作。 
	 *
	 */
	public long setNX (String key , String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			long set = jedis.setnx(key,value);
			return set;
		}catch(Exception e) {
			logger.error("redis [setNX "+key+" : " +value+"] error",e);
			return -1L;
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * @param key
	 * @param time     过期时间（秒）
	 * @param value
	 * @return
	 * 
	 * 将键 key 的值设置为 value ， 并将键 key 的生存时间设置为 seconds 秒钟。
	 * 如果键 key 已经存在， 那么 SETEX 命令将覆盖已有的值。
	 * SETEX 命令的效果和以下两个命令的效果类似：	
	 *	SET key value
	 *	EXPIRE key seconds  # 设置生存时间
	 * SETEX 和这两个命令的不同之处在于 SETEX 是一个原子（atomic）操作， 它可以在同一时间内完成设置值和设置过期时间这两个操作， 因此 SETEX 命令在储存缓存的时候非常实用。
	 * 
	 */
	public String setEX (String key , int seconds , String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String set = jedis.setex(key,seconds,value);
			return set;
		}catch(Exception e) {
			logger.error("redis [setEX "+key+" : " +value+" | "+seconds+"] error",e);
			return "-1";
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * @param key
	 * @param value
	 * @return 返回给定键 key 的旧值。如果键 key 没有旧值， 也即是说， 键 key 在被设置之前并不存在， 那么命令返回 nil 。
	 * 
	 * 将键 key 的值设为 value ， 并返回键 key 在被设置之前的旧值。
	 * 
	 */
	public String getSet (String key , String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String set = jedis.getSet(key,value);
			return set;
		}catch(Exception e) {
			logger.error("redis [getSet] [ "+key+" : " +value+"] error",e);
			return "-1";
		}finally {
			jedis.close();
		}
	}
	
	/**
	 * 返回hash数据中某个hkey下的所有value
	 * 
	 * */
	public List<String> hvals(String hkey){
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			List<String> hvals = jedis.hvals(hkey);
			return hvals;
		}catch(Exception e) {
			logger.error("redis [hvals] [ "+hkey+" ] error",e);
			return null;
		}finally {
			jedis.close();
		}
	}
}
