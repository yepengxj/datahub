package com.asiainfo.bdx.datahub.dataitemmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.asiainfo.bdx.datahub.common.dao.BaseJdbcDao;
import com.asiainfo.bdx.datahub.dataitemmgr.dao.IDataItemMgrDao;
import com.asiainfo.bdx.datahub.model.Dataitem;

@Repository
public class DataItemMgrDaoImpl extends BaseJdbcDao implements IDataItemMgrDao{
	Log log = LogFactory.getLog(DataItemMgrDaoImpl.class);
	
	public void addDataitem(Dataitem dataitem) throws Exception {
		String sql = "INSERT INTO DH_DATAITEM(" +
				"REPOSITORY_ID," +
				"USER_ID," + 
				"DATAITEM_ID," +
				"DATAITEM_NAME," +
				"SAMPLE_FILENAME," +
				"COMMENT," +
				"FILE_TYPE," +
				"REFRESH_TYPE," +
				"REFRESH_DATE," +
				"SUPPLY_STYLE) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] params  = {dataitem.getRepositoryId(),
				dataitem.getUserId(),
				dataitem.getDataitemId(),
				dataitem.getDataitemName(),
				dataitem.getSampleFilename(),
				dataitem.getComment(),
				dataitem.getFileType(),
				dataitem.getRefreshType(),
				dataitem.getRefreshDate(),
				dataitem.getSupplyStyle()};
		try{
			getJdbcTemplate().update(sql, params);
		}catch(Exception e){
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.DataItemMgrDaoImpl.addDataitem():insert dataitem error!");
			log.error("失败原因：" + e.getMessage());
			System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.DataItemMgrDaoImpl.addDataitem():insert dataitem error!");
			System.out.println("失败原因：" + e.getMessage());
			throw new Exception("insert dataitem error!",e);
			
		}
	}

	
	public void updateDataitem(Dataitem dataitem) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE DH_DATAITEM");
		sql.append(" SET DATAITEM_NAME = ?,");
		sql.append(" SAMPLE_FILENAME = ?,");
		sql.append(" COMMENT = ?,");
		sql.append(" FILE_TYPE = ?,");
		sql.append(" REFRESH_TYPE = ?,");
		sql.append(" REFRESH_DATE = ?,");
		sql.append(" SUPPLY_STYLE = ? ");
		sql.append(" WHERE REPOSITORY_ID = ?");
		sql.append(" AND USER_ID = ?");
		sql.append(" AND DATAITEM_ID = ?");
		Object[] params = {dataitem.getDataitemName(),dataitem.getSampleFilename(),
				dataitem.getComment(),dataitem.getFileType(),dataitem.getRefreshType(),
				dataitem.getRefreshDate(),dataitem.getSupplyStyle(),
				dataitem.getRepositoryId(),dataitem.getUserId(),dataitem.getDataitemId()};
		try{
			getJdbcTemplate().update(sql.toString(), params);
		}catch(Exception e){
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.DataItemMgrDaoImpl.updateDataitem():update dataitem error!");
			log.error("失败原因：" + e.getMessage());
			System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.DataItemMgrDaoImpl.updateDataitem():update dataitem error!");
			System.out.println("失败原因：" + e.getMessage());
			throw new Exception("update dataitem error!",e);
		}
	}

	
	public Dataitem queryDateitemById(Long userId,Long repositoryId,Long dataitemId) throws Exception {
		List params = new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM DH_DATAITEM A WHERE 1=1 ");
		if (userId != 0l) {
			sb.append("AND A.DOWN_USER = ? ");                                                                                                         
			params.add(userId);
		}
		if (repositoryId != 0l) {
			sb.append("AND A.REPOSITORY_ID = ? ");
			params.add(repositoryId);
		}
		if (dataitemId != 0l) {
			sb.append("AND A.DATAITEM_ID = ? ");
			params.add(dataitemId);
		}
		log.debug("DataItemMgrDaoImpl:queryDateitemById:sql::" + sb.toString());
		log.debug("DataItemMgrDaoImpl:queryDateitemById:param::" + params.toArray().toString());
		List<Dataitem> list = getJdbcTemplate().query(sb.toString(), ParameterizedBeanPropertyRowMapper.newInstance(Dataitem.class), params.toArray());
		return list.get(0);
	}

	
	public String queryDateitemById(Long dataitemId) throws Exception {
		//List params = new ArrayList();
		StringBuilder sb = new StringBuilder();
		//sb.append("SELECT DISTINCT A.DATAITEM_ID, A.DATAITEM_NAME,A.COMMENT, A.SAMPLE_FILENAME FROM DH_DATAITEM A WHERE A.DATAITEM_ID = ? ");
		sb.append("SELECT  DISTINCT  FILENAME FROM datahub.DH_UPLOADLOG  A  WHERE A.DATAITEM_ID =  ? ");
		//params.add(dataitemId);
		log.debug("DataItemMgrDaoImpl:queryDateitemById:sql::" + sb.toString());
		log.debug("DataItemMgrDaoImpl:queryDateitemById:param::" + dataitemId);
		return getJdbcTemplate().queryForObject(sb.toString(), new Object[] {dataitemId}, java.lang.String.class );
		//List<String> list = getJdbcTemplate().query(sb.toString(), ParameterizedBeanPropertyRowMapper.newInstance(String.class), params.toArray());
		//return list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.asiainfo.bdx.datahub.dataitemmgr.dao.IDataItemMgrDao#afterUploadFile(com.asiainfo.bdx.datahub.model.Dataitem)
	 */
	
	public void afterUploadFile(Dataitem dataitem) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE DH_DATAITEM");
		sql.append(" SET FILE_TYPE = ?,");
		sql.append(" SAMPLE_FILENAME = ?,");
		sql.append(" REFRESH_DATE = ?");
		sql.append(" WHERE DATAITEM_ID = ?");
		Object[] params = {dataitem.getFileType(),
				dataitem.getSampleFilename(),
				dataitem.getRefreshDate(),
				dataitem.getDataitemId()};
		try{
			getJdbcTemplate().update(sql.toString(), params);
		}catch(Exception e){
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.DataItemMgrDaoImpl.afterUploadFile():recorde message of new file dataitem error!");
			log.error("失败原因：" + e.getMessage());
			System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.DataItemMgrDaoImpl.afterUploadFile():recorde message of new file dataitem error!");
			System.out.println("失败原因：" + e.getMessage());
			throw new Exception("recorde message of new file dataitem error!",e);
		}
	}
	
}
