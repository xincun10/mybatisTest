package com.zcy.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.zcy.pojo.User;
/**
 * 用户接口的实现类
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {
	//需要向dao实现类中注入SqlSessionFactory
	//这里通过构造方法注入
	private SqlSessionFactory sqlSessionFactory;
	
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory)
	{
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public User findUserById(int id) throws Exception {
		//sqlSession是线程不安全的，不能定义在方法外面
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("test.findUserById", id);
		//释放资源
		sqlSession.close();
		return user;
	}

	@Override
	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.insert("test.insertUser", user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void deleteUser(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("test.deleteUser", id);
		sqlSession.commit();
		sqlSession.close();
	}

}
