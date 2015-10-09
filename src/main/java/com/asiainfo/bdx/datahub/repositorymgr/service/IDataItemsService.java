package com.asiainfo.bdx.datahub.repositorymgr.service;

import java.util.List;

import com.asiainfo.bdx.datahub.model.Dataitem;

public interface IDataItemsService {
    /**
     * 根据数据宝藏类型：获取我的数据宝藏买卖记录
     * @param dataType
     * @param userId
     * @return
     * @throws Exception
     */
	public List<Dataitem> getDataItems(String userId, String keyWord, String tradeType, String startTime, String endTime) throws Exception; 
 
	/**
	 * 获取我的上传以及下载信息
	 * @param dataType
	 * @param userId
	 * @return
	 * @throws Exception
	 */ 
	public List<Dataitem> dataitemTypeList(String tradeType,String userId,String dataitemId) throws Exception ;
	 
	/**
	 * 根据关键字查询数据宝藏
	 * @return 数据宝藏
	 * @throws Exception
	 */
	public List<Dataitem> resSearch(String keyWord) throws Exception; 
}
