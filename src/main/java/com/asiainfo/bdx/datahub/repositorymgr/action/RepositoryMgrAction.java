package com.asiainfo.bdx.datahub.repositorymgr.action;

import com.asiainfo.bdx.datahub.common.action.BaseAction;
import com.asiainfo.bdx.datahub.repositorymgr.service.IRepositoryMgrService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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
public class RepositoryMgrAction extends BaseAction {

    private static Log log = LogFactory.getLog(RepositoryMgrAction.class);

    @Resource
    private IRepositoryMgrService repositoryMgrService;

    /**
     * 获取宝藏及交易信息
     *
     * @return
     */

    public String getTradeInfo() {
        String userId = getSessionUserId();
        String keyword = getRequest().getParameter("keyword");//关键字
        String tradeType = getRequest().getParameter("tradType");//交易类型
        String startTime = getRequest().getParameter("startTime");//起始时间
        String endTime = getRequest().getParameter("endTime");//结束时间


        List list = repositoryMgrService.getRepositories(userId, keyword, tradeType, startTime, endTime);

        log.debug("size:" + list.size());
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", list.size());
        jsonMap.put("rows", list);
        String listJson = JSONObject.fromObject(jsonMap).toString();
        try {
            sendJson(getResponse(), listJson.toString());
            log.debug(listJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String list() {
        String userId = getSessionUserId();
        String keyword = getRequest().getParameter("keyword");//关键字
        String tradeType = getRequest().getParameter("tradType");//交易类型
        String startTime = getRequest().getParameter("startTime");//起始时间
        String endTime = getRequest().getParameter("endTime");//结束时间


        List list = repositoryMgrService.getRepositories(userId, keyword, tradeType, startTime, endTime);

        log.debug("size:" + list.size());
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", list.size());
        jsonMap.put("rows", list);
        String listJson = JSONObject.fromObject(jsonMap).toString();
        try {
            sendJson(getResponse(), listJson.toString());
            log.debug(listJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
