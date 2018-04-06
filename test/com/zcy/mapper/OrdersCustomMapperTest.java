package com.zcy.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zcy.pojo.Orders;
import com.zcy.pojo.OrdersCustom;
import com.zcy.pojo.User;

public class OrdersCustomMapperTest {

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
	public void testFindOrdersUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建代理对象
		OrdersCustomMapper ordersCustomMapper = 
				sqlSession.getMapper(OrdersCustomMapper.class);
		//调用mapper的方法
		List<OrdersCustom> list = ordersCustomMapper.findOrdersUser();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindOrdersUserResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建代理对象
		OrdersCustomMapper ordersCustomMapper = 
				sqlSession.getMapper(OrdersCustomMapper.class);
		//调用mapper的方法
		List<Orders> list = ordersCustomMapper.findOrdersUserResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建代理对象
		OrdersCustomMapper ordersCustomMapper = 
				sqlSession.getMapper(OrdersCustomMapper.class);
		//调用mapper的方法
		List<Orders> list = ordersCustomMapper.findOrdersAndOrderDetailResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建代理对象
		OrdersCustomMapper ordersCustomMapper = 
				sqlSession.getMapper(OrdersCustomMapper.class);
		//调用mapper的方法
		List<User> list = ordersCustomMapper.findUserAndItemsResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	
	//查询订单关联查询用户，用户信息使用延迟加载
	@Test
	public void testFindOrdersUserLazyLoading() throws Exception
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建代理对象
		OrdersCustomMapper ordersCustomMapper =
				sqlSession.getMapper(OrdersCustomMapper.class);
		//查询订单信息（单表）
		List<Orders> list = ordersCustomMapper.findOrdersUserLazyLoading();
		//遍历上边的订单列表
		for(Orders order:list)
		{
			//执行getUser()去查询用户信息，这里实现按需加载
			User user = order.getUser();
			System.out.println(user);
		}
	}

}
