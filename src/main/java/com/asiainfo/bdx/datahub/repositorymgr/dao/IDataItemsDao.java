package com.asiainfo.bdx.datahub.repositorymgr.dao;

import java.util.List;

import com.asiainfo.bdx.datahub.model.Dataitem;

public interface IDataItemsDao {
	/**
	 * 获取传家宝藏或者收购宝藏
	 * @param dataType
	 * @param userId
	 * @throws Exception
	 */
	public List<Dataitem> getDataItems(String userId, String keyWord, String tradeType, String startTime, String endTime) throws Exception;
   
	/**
	 * 获取我的上传（传家、卖出）信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Dataitem> getDataItemsByUpload(String userId) throws Exception;

	/**
	 *  获取我的下载（收购、买入）信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Dataitem> getDataItemsByDownload(String userId,String dataitemId) throws Exception;
	
	/**
	 * 根据关键字查询数据宝藏
	 * @return 数据宝藏
	 * @throws Exception
	 */
	public List<Dataitem> resSearch(String keyWord) throws Exception; 
}
