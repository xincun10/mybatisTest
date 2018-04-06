package com.zcy.pojo;

import java.util.List;

/*
 * 用户包装类型
 */
public class UserQueryVo {

	//这里包装所需要的查询条件
	private UserCustom userCustom;
	//传入多个id
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	
	//可以包装其他的查询条件，订单、商品等
}
