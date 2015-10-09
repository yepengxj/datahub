package com.asiainfo.bdx.datahub.common;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Pattern;

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
 * <pre>1    15.9.28    bob        Created</pre>
 * <p/>
 *
 * @author bob
 */
public class AuthorityInterceptor extends AbstractInterceptor {

    private static final Log log = LogFactory.getLog(AuthorityInterceptor.class);

    private String excludedUrl;

    private String[] unproectedUrl;

    @Override
    public void init() {
        String excludedUrl = getExcludedUrl();

        log.debug("getExcludedUrl:" + excludedUrl);

        unproectedUrl = excludedUrl == null ? null : excludedUrl.split(";");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        log.debug("AuthorityInterceptor:intercept start....");
        HttpServletRequest request = (HttpServletRequest) actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
        Map session = actionInvocation.getInvocationContext().getSession();

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        log.debug("url:" + contextPath + ":" + requestURI);
        log.debug("session:" + session.get("DH_SESSION"));
        log.debug("exclude:" + isExclude(contextPath, requestURI));
        if (isExclude(contextPath, requestURI)) {
            return actionInvocation.invoke();
        } else {
            if (session != null && session.get("DH_SESSION") != null) {
                return actionInvocation.invoke();
            } else {
                return "login";
            }
        }


    }


    protected final boolean isExclude(final String contextPath,
                                      final String uri) {
        Pattern p = Pattern.compile(contextPath + "/?");
        if (p.matcher(uri).matches()) {
            return true;
        }
        if (unproectedUrl == null) {
            return false;
        }
        for (String t : unproectedUrl) {
            if (t.startsWith("^")) {
                p = Pattern.compile(contextPath + "/" + t.substring(1));
            } else {
                p = Pattern.compile(t);
            }
            if (p.matcher(uri).matches()) {
                return true;
            }
        }
        return false;
    }

    public String getExcludedUrl() {
        return excludedUrl;
    }

    public void setExcludedUrl(String excludedUrl) {
        this.excludedUrl = excludedUrl;
    }
}
