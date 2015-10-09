package com.asiainfo.bdx.datahub.repositorymgr.dao;

import java.util.List;

import com.asiainfo.bdx.datahub.model.Dataitem;

public interface IResSearchDao {
	/**
	 * 根据关键字查询数据宝藏
	 * @param searchContext
	 * @return 数据宝藏集合
	 * @throws Exception
	 */
	public List<Dataitem> resSearch(String searchContext,String userId) throws Exception ;
}
