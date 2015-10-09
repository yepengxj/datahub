package com.asiainfo.bdx.datahub.login.dao;


import com.asiainfo.bdx.datahub.model.User;

public interface IUserDao {

	public User getUser(String loginName);
	 
	 void updatePWD(String loginName,String pwd);
	 
	 String getEmail(String loginName);
	 
}
