package com.asiainfo.bdx.datahub.repositorymgr.action;

import javax.annotation.Resource;

import com.asiainfo.bdx.datahub.common.action.BaseAction;
import com.asiainfo.bdx.datahub.repositorymgr.service.IResSearchService;

/**
 * Title :
 * <p/>
 * Description :处理搜索结果已登录和未登录请求
 * <p/>
 * CopyRight : CopyRight (c) 2015
 * <p/>
 * <p/>
 * JDK Version Used : JDK 6.0 +
 * <p/>
 * Modification History	:
 * <p/>
 * <pre>NO.    Date    Modified By    Why & What is modified</pre>
 * <pre>1    15.9.26    yanzi        Created</pre>
 * <p/>
 *
 * @author yanzi
 */
public class ResSearchAction extends BaseAction {

	@Resource
	private IResSearchService resSearchService;

	public String search()  {
		// TODO 修改参数
		String searchContext = "手机";// this.getRequest().getParameter("");
		// TODO 修改userId
		String userId = "1002";// this.getSession().getAttribute("user").getUserId();
		
		try {
			String jsonDataitem=resSearchService.resSearch(searchContext, userId);
            sendJson(getResponse(), jsonDataitem);
            System.out.println(jsonDataitem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 

	}

}
