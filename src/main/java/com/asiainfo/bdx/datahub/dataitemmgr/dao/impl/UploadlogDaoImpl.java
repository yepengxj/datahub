package com.asiainfo.bdx.datahub.dataitemmgr.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.asiainfo.bdx.datahub.common.dao.BaseJdbcDao;
import com.asiainfo.bdx.datahub.dataitemmgr.dao.IUploadlogDao;
import com.asiainfo.bdx.datahub.model.Uploadlog;

@Repository
public class UploadlogDaoImpl extends BaseJdbcDao  implements IUploadlogDao {
	Log log = LogFactory.getLog(UploadlogDaoImpl.class);
	
	
	public void insertUploadlog(Uploadlog uploadlog) throws Exception {
		String sql = "INSERT INTO DH_UPLOADLOG(" +
				"DATAITEM_ID," +
				"DATA_DATE,FILENAME) " +
				"VALUES(?,?,?)";
		Object[] params  = {uploadlog.getDataitemId(),
				uploadlog.getDataDate(),uploadlog.getFileName()};
		try{
			getJdbcTemplate().update(sql, params);
		}catch(Exception e){
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.UploadlogDaoImpl.insertUploadlog():insert uploadlog error!");
			log.error("失败原因：" + e.getMessage());
			System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.UploadlogDaoImpl.insertUploadlog():insert uploadlog error!");
			System.out.println("失败原因：" + e.getMessage());
			throw new Exception("insert uploadlog error!",e);
		}
	}
	
}
