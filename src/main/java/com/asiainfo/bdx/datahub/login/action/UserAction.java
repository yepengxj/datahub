package com.asiainfo.bdx.datahub.login.action;


import com.asiainfo.bdx.datahub.common.DHConstants;
import com.asiainfo.bdx.datahub.common.action.BaseAction;
import com.asiainfo.bdx.datahub.login.service.IUserService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class UserAction  extends BaseAction{
	private static final Log log = LogFactory.getLog(UserAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private IUserService userService;

    public String index() {
        return "index";
    }
  
    /**
     *处理登录请求 
     */
    public void login(){
    	String loginName = getRequest().getParameter("loginName");
    	String pwd = getRequest().getParameter("pwd");
		LOG.debug("loginName::pwd==" + loginName + ":" + pwd);
		loginName = "admin";
		int status = userService.login(loginName, pwd);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
    	jsonMap.put("status", status);
		if (status == DHConstants.Login.SUCESS) {
			jsonMap.put("result", DHConstants.Login.SUCESS_DSC + ":" + loginName);
		} else if (status == DHConstants.Login.WRONG_PWD) {
			jsonMap.put("result",DHConstants.Login.WRONG_PWD_DSC);
    	}else{
    		jsonMap.put("result",DHConstants.Login.NO_USER_DSC);
    	}
    	
    	String json = JSONObject.fromObject(jsonMap).toString();
    	 try {
             sendJson(getResponse(), json);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    /**
     * 修改密码
     */
    public void changePwd(){
    	String loginName = getRequest().getParameter("loginName");
		String newPwd = getRequest().getParameter("newPwd");
		String pwd = getRequest().getParameter("pwd");
		userService.updatePwd(loginName, newPwd, pwd);
	}
    
    /**
     * 重置密码，发送邮件
     */
    public void resetPwd(){
    	String loginName = getRequest().getParameter("loginName");
    	userService.restPwd(loginName);
    }
}
