package com.asiainfo.bdx.datahub.login.service;

/**
 * Created by admin on 2015/6/19.
 */
public interface IUserService {
	/**
	 * ��¼
	 * @return
	 */
    int login(String loginName,String pwd);

    void updatePwd(String loginName, String pwd, String npwd);
    
    void restPwd(String loginName);
}
