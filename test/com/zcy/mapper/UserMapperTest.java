package com.zcy.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zcy.pojo.User;
import com.zcy.pojo.UserCustom;
import com.zcy.pojo.UserQueryVo;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;
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
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		User user = userMapper.findUserById(2);
		sqlSession.close();
		System.out.println(user);
	}
	
	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//创建包装对象，设置查询条件
		UserCustom userCustom = new UserCustom();
		//由于这里使用动态sql，如果不设置某个值，条件不会拼接在sql中
//		userCustom.setSex("nv");
		userCustom.setUsername("咸鱼");
		UserQueryVo userQueryVo = new UserQueryVo();
		//传入多个id
		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(2);
		ids.add(3);
		//将ids通过userQueryVo传入statement中
		userQueryVo.setIds(ids);
		userQueryVo.setUserCustom(userCustom);
		//调用userMapper的方法
		List<UserCustom> userCustomlist = userMapper.findUserList(userQueryVo);
		sqlSession.close();
		System.out.println(userCustomlist);
	}
	
	@Test
	public void testFindUserByIdResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		User user = userMapper.findUserByIdResultMap(2);
		System.out.println(user);
	}
	
	//测试二级缓存
	@Test
	public void testCache2() throws Exception
	{
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		//创建代理对象
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		//第一次发起请求，查询id为2的用户
		User user1 = userMapper1.findUserById(2);
		System.out.println(user1);
		//这里执行关闭操作，将sqlSession中的数据写到二级缓存区域
		sqlSession1.close();
		
//		//使用sqlSession3执行commit()操作
//		//创建代理对象
//		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
//		//第一次发起请求，查询id为2的用户
//		User user3 = userMapper3.findUserById(2);
//		user3.setUsername("hebe");
//		userMapper3.updateUser(user3);
//		//执行提交，清空UserMapper下边的二级缓存
//		sqlSession3.commit();
//		System.out.println(user3);
//		sqlSession3.close();
		
		//创建代理对象
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		//第一次发起请求，查询id为2的用户
		User user2 = userMapper2.findUserById(2);
		System.out.println(user2);
		sqlSession2.close();
	}

}
