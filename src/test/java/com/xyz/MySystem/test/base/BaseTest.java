package com.xyz.MySystem.test.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试 
@ContextConfiguration(locations={"classpath:core/**",""})
@Rollback(value=true)
@Transactional(transactionManager="tx")
public class BaseTest {
	

}
