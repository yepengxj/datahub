package com.asiainfo.bdx.datahub.repositorymgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.repositorymgr.dao.IResSearchDao;
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
@Service
public class ResSearchServiceImpl  implements IResSearchService{
private static final Log log = LogFactory.getLog(ResSearchServiceImpl.class);
	
	@Resource
	private IResSearchDao resSearchDao ;
	
	/*
	 *  (non-Javadoc)
	 * @see com.asiainfo.bdx.datahub.repositorymgr.service.IResSearchService#resSearch(java.lang.String, java.lang.String)
	 */
	public String resSearch(String searchContext,String userId) throws Exception {
		log.debug("ResSearchServiceImpl resSearch。。。。。"); 
	    //根据搜索关键字和userId查询数据对象
		List<Dataitem> list = resSearchDao.resSearch(searchContext, userId); 
		return JSONArray.fromObject(list).toString();
	 
	}
 
	
 
}
