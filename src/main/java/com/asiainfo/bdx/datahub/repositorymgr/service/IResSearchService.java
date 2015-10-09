package com.asiainfo.bdx.datahub.repositorymgr.service;

public interface IResSearchService {
	
	/**
	 * 根据关键字查询数据宝藏
	 * @return 数据宝藏
	 * @throws Exception
	 */
	public String resSearch(String searchContext,String userId) throws Exception; 
	
}
