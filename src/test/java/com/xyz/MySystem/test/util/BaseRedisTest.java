package com.xyz.MySystem.test.util;

import org.junit.Test;

import com.xyz.MySystem.test.base.BaseTest;
import com.xyz.MySystem.util.redis.BaseRedis;

public class BaseRedisTest extends BaseTest{


	@Test
	public void testBaseRedis() {
		BaseRedis redis = new BaseRedis();
		String string = redis.get("count");
		System.out.println(string);
	}
}
