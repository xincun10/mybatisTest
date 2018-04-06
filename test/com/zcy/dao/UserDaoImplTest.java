package com.zcy.dao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zcy.pojo.User;

public class UserDaoImplTest {
	
	private SqlSessionFactory sqlSessionFactory;

	//此方法是在执行testFindUserById之前执行的
	@Before
	public void setUp() throws Exception {
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
	}

	@Test
	public void testFindUserById() throws Exception {
		//创建UserDao的对象
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		//创建UserDao的方法
		User user = userDao.findUserById(2);
		System.out.println(user);
	}

}
