package com.asiainfo.bdx.datahub.login.service.impl;

import com.asiainfo.bdx.datahub.common.DHConstants;
import com.asiainfo.bdx.datahub.common.util.MailUtil;
import com.asiainfo.bdx.datahub.common.util.Md5Util;
import com.asiainfo.bdx.datahub.login.dao.IUserDao;
import com.asiainfo.bdx.datahub.login.service.IUserService;
import com.asiainfo.bdx.datahub.model.User;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by admin on 2015/6/19.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);

	@Autowired 
	private IUserDao userDao;

	public int login(String loginName,String pwd) {
		User user = userDao.getUser(loginName);
		String userPwd = user.getLoginPasswd();
		long userId = user.getUserId();
		log.debug("userId::" + userId);
		int status = DHConstants.Login.SUCESS;
		if (userPwd == null) {
			status = DHConstants.Login.NO_USER;
		} else {
			String pwdMd5 = Md5Util.MD5(pwd);
			if (!userPwd.equals(pwdMd5)) {
				status = DHConstants.Login.WRONG_PWD;
			}
		}
		if (status == DHConstants.Login.SUCESS) {
			Map session = ActionContext.getContext().getSession();
			session.put("DH_SESSION", userId);
			session.put("USERNAME", user.getLoginName());
			log.debug("username:" + user.getLoginName());
		}

		return status;
	}


	public void updatePwd(String loginName, String newPwd, String oldpwd) {
		String newpwd = Md5Util.MD5(newPwd);
		int status = login(loginName, oldpwd);
		log.debug("updaePwd:" + status);
		if (status == 1) {
			userDao.updatePWD(loginName, newpwd);
		} else {

		}
	}

	/**
	 * 重置密码，需要发送邮件。
	 */
	public void restPwd(String loginName) {
		String email = userDao.getEmail(loginName);
		if(StringUtils.isEmpty(email)){
			return;
		}
		String resetPWD="admin123";
		String newPwd = Md5Util.MD5(resetPWD);
		userDao.updatePWD(loginName, newPwd);
		//sendEmail
		MailUtil.sendMail(email, "DataHub平台密码重置", "重置后密码："+resetPWD);
		
	}
	
}
