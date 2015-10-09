package com.asiainfo.bdx.datahub.dataitemmgr.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.asiainfo.bdx.datahub.common.dao.BaseJdbcDao;
import com.asiainfo.bdx.datahub.dataitemmgr.dao.IFieldDao;
import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.model.Field;

@Repository
public class FieldDaoImpl extends BaseJdbcDao implements IFieldDao {
	Log log = LogFactory.getLog(FieldDaoImpl.class);
	public void addField(Field field) {
		String sql = "INSERT INTO DH_FIELD(" +
				"DATAITEM_ID," +
				"FIELD_ID," + 
				"FIELD_RAWNAME," +
				"FIELD_NAME) " +
				"VALUES(?,?,?,?)";
		Object[] params  = {field.getDataitemId(),
				field.getFieldId(),
				field.getFieldRawname(),
				field.getFieldName(),
				};
		try{
			getJdbcTemplate().update(sql, params);
		}catch(Exception e){
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():向dh_field表中插入数据失败！");
			System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():向dh_field表中插入数据失败！");
			System.out.println("失败原因：" + e.getMessage());
		}
	}

	public void updateField(Field field) throws Exception {
		StringBuffer sql = 
				new StringBuffer("UPDATE FIELD ");
		sql.append("SET FIELD_RAWNAME = ?,FIELD_NAME=? ");
		sql.append("WHERE DATAITEM_ID = ? AND FIELD_ID = ? ");
		Object[] params  = {field.getDataitemId(),
				field.getFieldId(),
				field.getFieldRawname(),
				field.getFieldName(),
				};
		try{
			getJdbcTemplate().update(sql.toString(), params);
		}catch(Exception e){
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():update field error!");
			log.error("失败原因：" + e.getMessage());
			System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():update field error!");
			System.out.println("失败原因：" + e.getMessage());
			throw new Exception("update field error!",e);
		}
	}

	
	
	/* (non-Javadoc)
	 * @see com.asiainfo.bdx.datahub.dataitemmgr.dao.IFieldDao#updateField(java.util.List)
	 */
	public boolean updateField(final List<Field> fieldList) throws Exception {
		/*StringBuffer sql = 
				new StringBuffer("UPDATE FIELD ");
		sql.append("SET FIELD_RAWNAME = ?,FIELD_NAME=? ");
		sql.append("WHERE DATAITEM_ID = ? AND FIELD_ID = ? ");
		try{
			if(fieldList != null && fieldList.size() > 0){
				int[] counts = getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){

					@Override
					public int getBatchSize() {
						return fieldList.size();
					}

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Field field = fieldList.get(i);
						ps.setString(1, field.getFieldRawname());
						ps.setString(2, field.getFieldName());
						ps.setLong(3, field.getDataitemId());
						ps.setLong(4, field.getFieldId());
					}
					
				});
				if (counts.length != 0) {
					log.debug("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():批量修改dh_field表中的"+ counts.length +"条数据！");
					System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():批量修改dh_field表中的"+ counts.length +"条数据！");
					return true;
	            }
				log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():批量修改dh_field表中的数据失败！");
				System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():批量修改dh_field表中的数据失败！");
				return false;
	            
			}else {
				log.debug("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():没有数据需要修改dh_field表！");
				System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():没有数据需要修改dh_field表！");
				return true;
	        }
		}catch(Exception e){
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.updateField():修改dh_field表中的数据异常！");
			log.error("异常原因：" + e.getMessage());
			return false;
		}*/
		return true;
	}
	
	public boolean addField(final List<Field> fieldList) {
		StringBuffer sql = 
				new StringBuffer("INSERT INTO DH_FIELD(" +
						"DATAITEM_ID," +
						"FIELD_ID," + 
						"FIELD_RAWNAME," +
						"FIELD_NAME) " +
						"VALUES(?,?,?,?)");
		try{
			if(fieldList != null && fieldList.size() > 0){
				int[] counts = getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter(){

					public int getBatchSize() {
						return fieldList.size();
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Field field = fieldList.get(i);
						ps.setLong(1, field.getDataitemId());
						ps.setLong(2, field.getFieldId());
						ps.setString(3, field.getFieldRawname());
						ps.setString(4, field.getFieldName());
					}
					
				});
				if (counts.length != 0) {
					log.debug("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():向dh_field表中批量插入"+ counts.length +"条数据！");
					System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():向dh_field表中批量插入"+ counts.length +"条数据！");
					return true;
	            }
				log.error("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():向dh_field表中批量插入数据失败！");
				System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():向dh_field表中批量插入数据失败！");
				return false;
	            
			}else {
				log.debug("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():没有数据需要插入dh_field表！");
				System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():没有数据需要插入dh_field表！");
				return true;
	        }
		}catch(Exception e){
			log.debug("com.asiainfo.bdx.datahub.dataitemmgr.dao.impl.FieldDaoImpl.addDataitem():向dh_field表中插入数据异常！");
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.bdx.datahub.dataitemmgr.dao.IFieldDao#deleteField(java.lang.String)
	 */
	public void deleteField(Long dataitemId) {
		getJdbcTemplate()
		.update(" DELETE FROM DH_FIELD WHERE DATAITEM_ID = ? ",
				new Object[] { dataitemId });
	}

	public List<Field> queryFieldById(Long dataitemId) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  DISTINCT F.DATAITEM_ID, F.FIELD_ID,F.FIELD_RAWNAME,F.FIELD_NAME FROM DH_FIELD F WHERE F.DATAITEM_ID = ? ");
		Object[] params  = new Object[]{dataitemId};
		log.debug("FieldDaoImpl:queryFieldById:sql::" + sb.toString());
		log.debug("FieldDaoImpl:queryFieldById:param::" + params);
		List<Field> list = getJdbcTemplate().query(sb.toString(), ParameterizedBeanPropertyRowMapper.newInstance(Field.class), params);
		return list;
	}
	
}
