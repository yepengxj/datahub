package com.asiainfo.bdx.datahub.repositorymgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.asiainfo.bdx.datahub.common.DHConstants;
import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.repositorymgr.dao.IDataItemsDao;
import com.asiainfo.bdx.datahub.repositorymgr.service.IDataItemsService;

/**
 * Title :
 * <p/>
 * Description :处理传家宝藏，收购宝藏service
 * <p/>
 * CopyRight : CopyRight (c) 2015
 * <p/>
 * <p/>
 * JDK Version Used : JDK 6.0 +
 * <p/>
 * Modification History :
 * <p/>
 * 
 * <pre>
 * NO.    Date    Modified By    Why & What is modified
 * </pre>
 * 
 * <pre>
 * 1    15.9.26    yanzi        Created
 * </pre>
 * <p/>
 * 
 * @author yanzi
 */
@Service
public class DataItemsServiceImpl implements IDataItemsService {
	private static final Log log = LogFactory
			.getLog(DataItemsServiceImpl.class);

	@Resource
	private IDataItemsDao dataItems;

	/*
	 * * (non-Javadoc)
	 * 
	 * @see com.asiainfo.bdx.datahub.repositorymgr.service.IDataAssetsService#
	 * getDataAssets(java.lang.String, java.lang.String)
	 */
	public List<Dataitem> getDataItems(String userId, String keyWord,
			String tradeType, String startTime, String endTime)
			throws Exception {
		log.debug("DataAssetsServiceImpl getDataAssets。。。。。");
		// 根据数据宝藏类型：获取我的数据宝藏
		List<Dataitem> list = dataItems.getDataItems(userId, keyWord,
				tradeType, startTime, endTime);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asiainfo.bdx.datahub.repositorymgr.service.IDataItemsService#
	 * dataitemTypeList(java.lang.String, java.lang.String)
	 */
	public List<Dataitem> dataitemTypeList(String tradeType, String userId,String dataitemId)
			throws Exception {
		List<Dataitem> list = null;
		if (tradeType.equals(DHConstants.DOWNDLOAD_FLAG)) {
			list = dataItems.getDataItemsByDownload(userId,dataitemId);
		} else {
			list = dataItems.getDataItemsByUpload(userId);
		}
		return list;
	}
   
	public List<Dataitem> resSearch(String keyWord) throws Exception {
		log.debug("DataItemsServiceImpl resSearch。。。。。"); 
	    //根据搜索关键字查询数据对象
		List<Dataitem> list = dataItems.resSearch(keyWord); 
		return  list;
	  
	}
 
 

}
