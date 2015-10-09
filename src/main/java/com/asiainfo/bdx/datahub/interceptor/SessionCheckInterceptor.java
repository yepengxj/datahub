package com.asiainfo.bdx.datahub.interceptor;

import java.util.Map;

import com.asiainfo.bdx.datahub.login.action.UserAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
	public class SessionCheckInterceptor extends AbstractInterceptor {   
	        
		
	     public String intercept(ActionInvocation actionInvocation) throws Exception {   
	          ActionContext actionContext = actionInvocation.getInvocationContext();   
	          Map session = actionContext.getSession();   
	             
	          Object action = actionInvocation.getAction();   
	         if (action instanceof UserAction) {   
	             return actionInvocation.invoke();   
	          }   
	         //check session    
	         if(session.get("DH_SESSION")==null ){   
	             return "logout";   
	          }   
	         return actionInvocation.invoke();//go on    
	      }   
	 }  
	   

