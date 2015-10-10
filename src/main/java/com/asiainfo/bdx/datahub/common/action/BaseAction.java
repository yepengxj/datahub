package com.asiainfo.bdx.datahub.common.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Title :
 * <p/>
 * Description :
 * <p/>
 * CopyRight : CopyRight (c) 2014
 * <p/>
 * <p/>
 * JDK Version Used : JDK 5.0 +
 * <p/>
 * Modification History	:
 * <p/>
 * <pre>NO.    Date    Modified By    Why & What is modified</pre>
 * <pre>1    15.9.26    bob        Created</pre>
 * <p/>
 *
 * @author bob
 */
public class BaseAction extends ActionSupport implements SessionAware {

    private static final Log log = LogFactory.getLog(BaseAction.class);
    private Map session;
    /**
     * 返回json信息给客户端
     *
     * @param response
     * @param text
     * @throws Exception
     */
    public void sendJson(HttpServletResponse response, String text)
            throws Exception {
        response.setContentType("text/json; charset=UTF-8");
        response.setHeader("progma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print(text);
    }
    public void sendHtmlJson(HttpServletResponse response, String text){
    	 PrintWriter write = null;  
         //response.setContentType("application/text;charset=UTF-8");  
    this.getResponse().setContentType("text/html;charset=UTF-8");  
    this.getResponse().setHeader("Pragma", "No-cache");  
    this.getResponse().setHeader("Cache-Control", "no-cache");  
    this.getResponse().setDateHeader("Expires", 0);  
     try {  
         write = this.getResponse().getWriter();  
         write.write(text);  
         write.flush();  
     } catch (IOException e) {  
     } finally {  
    	 /*this.getResponse() = null;*/  
         if (write != null)  
             write.close();  
             write = null;  
     }  
    }
    /**
     * Convenience method to get the request
     *
     * @return current request
     */
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * Convenience method to get the response
     *
     * @return current response
     */
    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    /**
     * Convenience method to get the session. This will create a session if one doesn't exist.
     *
     * @return the session from the request (request.getSession()).
     */
    protected String getSessionUserId() {
        log.debug("getsesion");
        /*Map session=this.context.getSession();*/
        Object userId = null;
        try {
            userId = session.get("DH_SESSION");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("base Action sessionuserId222:" + String.valueOf(userId));
        return String.valueOf(userId);
    }

    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
