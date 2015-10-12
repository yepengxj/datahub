package com.asiainfo.bdx.datahub.repositorymgr.dao.impl;

import com.asiainfo.bdx.datahub.common.DHConstants;
import com.asiainfo.bdx.datahub.common.dao.BaseJdbcDao;
import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.repositorymgr.dao.IDataItemsDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Title :
 * <p/>
 * Description :查询收购宝藏，传家宝藏以及相关记录
 * <p/>
 * CopyRight : CopyRight (c) 2015
 * <p/>
 * <p/>
 * JDK Version Used : JDK 6.0 +
 * <p/>
 * Modification History :
 * <p/>
 * 
 * <pre>
 * NO.    Date    Modified By    Why & What is modified
 * </pre>
 * 
 * <pre>
 * 1    15.9.26    yanzi        Created
 * </pre>
 * <p/>
 * 
 * @author yanzi
 */
@Repository
public class DataItemsDaoImpl extends BaseJdbcDao implements IDataItemsDao {
	private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
			.getLog(DataItemsDaoImpl.class);

	/**
	 * 获取我的交易记录信息 (non-Javadoc)
	 */
	public List<Dataitem> getDataItems(String userId, String keyWord,
			String tradeType, String startTime, String endTime)
			throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DATAITEM_ID,USER_ID,DATAITEM_NAME,REFRESH_TYPE,DATA_DATE OPTIME,TRADETYPE FROM ");
		sb.append("( ");
		sb.append("SELECT A.DATAITEM_ID, A.USER_ID,A.DATAITEM_NAME,A.REFRESH_TYPE,B.DATA_DATE,'1' TRADETYPE FROM DH_DATAITEM A,DH_UPLOADLOG B WHERE A.DATAITEM_ID=B.DATAITEM_ID ");
		sb.append("UNION ");
		sb.append("SELECT  C.DATAITEM_ID, C.USER_ID,C.DATAITEM_NAME,C.REFRESH_TYPE,D.DATA_DATE,'2' TRADETYPE FROM DH_DATAITEM C,DH_DOWNLOADLOG D WHERE C.DATAITEM_ID=D.DATAITEM_ID ");
		sb.append(") T ");
		sb.append("WHERE 1=1 ");
		if (StringUtils.isNotEmpty(userId)) {
			sb.append("AND  USER_ID=?  ");
			params.add(userId);
		}
		if (StringUtils.isNotEmpty(keyWord)) {
			sb.append("AND DATAITEM_NAME like '%" + keyWord + "%' ");
			// params.add(keyWord);
		}
		log.debug("tradeType:" + tradeType);
		if (!"0".equals(tradeType)) {
			sb.append("AND TRADETYPE=? ");
			params.add(tradeType);
		}
		if (StringUtils.isNotEmpty(startTime)) {
			sb.append("AND  DATA_DATE >=?  ");
			params.add(startTime);
		}
		if (StringUtils.isNotEmpty(endTime)) {
			sb.append("AND DATA_DATE<=?    ");
			params.add(endTime);
		}
		log.debug("DataItemsDaoImpl:getDataItems:sql::" + sb.toString());
		log.debug("DataItemsDaoImpl:getDataItems:param::"
				+ params.toArray().toString());
		List list = getJdbcTemplate().query(sb.toString(),
				ParameterizedBeanPropertyRowMapper.newInstance(Dataitem.class),
				params.toArray());
		return list;
	}

	/**
	 * 获取我的上传（传家、卖出）信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	public List<Dataitem> getDataItemsByUpload(String userId) throws Exception {
		List params = new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT  DISTINCT A.DATAITEM_ID, A.ICO_NAME,A.DATAITEM_NAME,A.COMMENT FROM DH_DATAITEM A ");
		if (StringUtils.isNotEmpty(userId)) {
			sb.append(" WHERE A.USER_ID=? ");
			params.add(userId);
		}
		log.debug("DataItemsDaoImpl:getDataItemsByUpload:sql::" + sb.toString());
		log.debug("DataItemsDaoImpl:getDataItemsByUpload:param::"
				+ params.toArray().toString());
		List<Dataitem> list = getJdbcTemplate().query(sb.toString(),
				ParameterizedBeanPropertyRowMapper.newInstance(Dataitem.class),
				params.toArray());
		return list;
	}

	/**
	 * 获取我的下载（收购、买入）信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	public List<Dataitem> getDataItemsByDownload(String userId,String dataitemId)
			throws Exception {
		List params = new ArrayList();
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(dataitemId)) {
			sb.append("SELECT DISTINCT B.DATA_DATE ,A.DATAITEM_ID, A.DATAITEM_NAME,A.ICO_NAME, A.COMMENT FROM DH_DATAITEM A,DH_UPLOADLOG B WHERE A.DATAITEM_ID=B.DATAITEM_ID ");
		}else{
			sb.append("SELECT DISTINCT B.DATA_DATE ,A.DATAITEM_ID, A.DATAITEM_NAME,A.ICO_NAME, A.COMMENT FROM DH_DATAITEM A,DH_DOWNLOADLOG B WHERE A.DATAITEM_ID=B.DATAITEM_ID ");
		}
		
		if (StringUtils.isNotEmpty(userId)) {
			sb.append("AND B.DOWN_USER=?  ");
			params.add(userId);
		}
		if (StringUtils.isNotEmpty(dataitemId)) {
			sb.append("AND A.DATAITEM_ID=?  ");
			params.add(dataitemId);
		}
		
		log.debug("DataItemsDaoImpl:getDataItemsByDownload:sql::"
				+ sb.toString());
		log.debug("DataItemsDaoImpl:getDataItemsByDownload:param::"
				+ params.toArray().toString());
		List<Dataitem> list = getJdbcTemplate().query(sb.toString(),
				ParameterizedBeanPropertyRowMapper.newInstance(Dataitem.class),
				params.toArray());
		return list;
	}
    
	public List<Dataitem> resSearch(String keyWord) throws Exception {

		log.debug("=====开始查询数据对象");
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();

		sql.append("   SELECT  * ");
		sql.append("   FROM  DH_DATAITEM ");

		if (StringUtils.isNotEmpty(keyWord)) {
			sql.append("   WHERE DATAITEM_NAME LIKE '%").append(keyWord);
			sql.append("%'  OR COMMENT LIKE '%").append(keyWord).append("%' ");
		}

		log.debug("DataItemsDaoImpl resSearch() sql.toString(): "
				+ sql.toString());
		List<Dataitem> list = getJdbcTemplate().query(sql.toString(),
				new RowMapper() {

					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Dataitem item = new Dataitem();
						item.setIcoName(rs.getString("ico_name"));
					    item.setDataitemName(rs.getString("dataitem_name"));
						item.setComment(rs.getString("comment"));
						item.setRepositoryId(rs.getLong("repository_id"));
						item.setDataitemId(rs.getLong("dataitem_id"));
						item.setTag("tag");
						return item;
					}

				});
		log.debug("=======结束查询数据对象");
		return list;
	}
}
