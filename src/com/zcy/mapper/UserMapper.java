package com.zcy.mapper;

import java.util.List;

import com.zcy.pojo.User;
import com.zcy.pojo.UserCustom;
import com.zcy.pojo.UserQueryVo;

/**
 * 用户管理mapper接口,相当于dao接口
 * @author Administrator
 *
 */
public interface UserMapper {
	
	//根据id查询用户信息，使用resultMap输出
	public User findUserByIdResultMap(int id) throws Exception;
	
	//用户信息综合查询
	public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
	//查询综合查询的总数
	public int findUserCount(UserQueryVo userQueryVo) throws Exception;
	
	//根据id查询用户信息
	public User findUserById(int id) throws Exception;
	//更新用户信息
	public void updateUser(User user) throws Exception;
	//根据用户名列查询用户列表
	public List<User> findUserByName(String name) throws Exception;
	//添加用户信息
	public void insertUser(User user) throws Exception;
	//删除用户信息
	public void deleteUser(int id) throws Exception;
}
