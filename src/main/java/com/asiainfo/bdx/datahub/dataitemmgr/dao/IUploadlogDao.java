package com.asiainfo.bdx.datahub.dataitemmgr.dao;

import com.asiainfo.bdx.datahub.model.Uploadlog;


public interface IUploadlogDao {
	/**
	 * 记录下载数据文件日志
	 * @param uploadlog
	 * @throws Exception
	 */
	public void insertUploadlog(Uploadlog uploadlog)throws Exception;
}
