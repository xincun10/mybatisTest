package com.zcy.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.zcy.pojo.User;

public class MybatisFirst {

	//根据id查询用户信息，得到一条记录结果
	@Test
	public void findUserByIdTest() throws IOException
	{
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		
		//通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//通过SqlSession操作数据库
		//第一个参数：映射文件中的statement的id，等于=namespace+"."+statement的id
		//第二个参数：指定和映射文件中所匹配的parameterType类型的参数
		//sqlSession.selectOne的结果是与映射文件中所匹配的resultType类型的对象
		User user = sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		//释放资源
		sqlSession.close();
	}
	
	//根据用户名称模糊查询用户列表
	@Test
	public void findUserByNameTest() throws IOException
	{
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		//通过工厂得到sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//模糊查询，结果可能为多条
		//list中的User和映射文件中resultType指定的类型一致
		List<User> list = sqlSession.selectList("test.findUserByName", "%小明%");
		System.out.println(list);
		sqlSession.close();
	}
	
	//添加用户
	@Test
	public void insertUserTest() throws IOException
	{
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		//通过工厂得到sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//插入用户对象
		User user = new User();
		user.setUsername("咸鱼2");
		user.setBirthday(new Date());
		user.setSex("女");
		user.setAddress("高雄");
		//插入数据
		sqlSession.insert("test.insertUser", user);
		//提交事务
		sqlSession.commit();
		System.out.println(user.getId());
		//释放资源
		sqlSession.close();
	}
	
	//删除用户
	@Test
	public void deleteUserTest() throws IOException
	{
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		//通过工厂得到sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//传入id删除用户
		sqlSession.delete("test.deleteUser", 1);
		//提交事务
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	
	//更新用户
	@Test
	public void updateUserTest() throws IOException
	{
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂,传入mybatis的配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		//通过工厂得到sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//更新用户信息
		User user = new User();
		//必须设置id
		user.setId(5);
		user.setUsername("憨人");
		user.setBirthday(new Date());
		user.setSex("女");
		user.setAddress("高雄");
		
		//传入id删除用户
		sqlSession.update("test.updateUser", user);
		//提交事务
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
}
