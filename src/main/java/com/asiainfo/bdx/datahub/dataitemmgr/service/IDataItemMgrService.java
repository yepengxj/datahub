package com.asiainfo.bdx.datahub.dataitemmgr.service;

import com.asiainfo.bdx.datahub.dataitemmgr.model.custom.DataitemDto;
import com.asiainfo.bdx.datahub.model.Dataitem;

public interface IDataItemMgrService {
	/**
	 * 增加数据对象
	 * @param dataitem
	 */
	public void addDataitem(DataitemDto dataitemDto) throws Exception;
	
	/**
	 * 修改数据对象和数据项
	 * @param userId
	 * @param repositoryId
	 * @param dataitemId
	 * @return
	 * @throws Exception
	 */
	public DataitemDto queryDataitemDtoById(Long userId,Long repositoryId,Long dataitemId)
			throws Exception;
	
	/**
	 * 修改数据对象和数据项
	 * @param repositoryId
	 * @return
	 * @throws Exception
	 */
	public String queryDataitemById(Long dataitemId,String downDate)
			throws Exception;
	
	/**
	 * 修改数据对象
	 * @param dataitemDto
	 * @throws Exception
	 */
	public void updateDataitem(DataitemDto dataitemDto) throws Exception;
	
	/**
	 * 记录上传数据文件日志
	 * @throws Exception
	 */
	public void insertUploadLog(Dataitem dataitem,String fileFileName)
			throws Exception;
}
