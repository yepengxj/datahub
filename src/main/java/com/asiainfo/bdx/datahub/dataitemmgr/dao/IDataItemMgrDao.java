package com.asiainfo.bdx.datahub.dataitemmgr.dao;

import com.asiainfo.bdx.datahub.model.Dataitem;

public interface IDataItemMgrDao {
	/**
	 * 增加数据对象
	 * @param dataitem
	 */
	public void addDataitem(Dataitem dataitem) 
			throws Exception;
	
	/**
	 * 修改数据对象
	 * @param userId
	 * @param repositoryId
	 * @param dataitemId
	 * @throws Exception
	 */
	public Dataitem queryDateitemById(Long userId,Long repositoryId,Long dataitemId)
			throws Exception;
	
	/**
	 * 修改数据对象
	 * @param dataitemId
	 * @throws Exception
	 */
	public String queryDateitemById(Long dataitemId,String downDate)
			throws Exception;
	
	/**
	 * 修改数据对象
	 * @param dataitem
	 */
	public void updateDataitem(Dataitem dataitem) 
			throws Exception;
	
	/**
	 * 根据file_type和sample_filename字段
	 * @param dataitem
	 * @throws Exception
	 */
	public void afterUploadFile(Dataitem dataitem)
			throws Exception;
}
