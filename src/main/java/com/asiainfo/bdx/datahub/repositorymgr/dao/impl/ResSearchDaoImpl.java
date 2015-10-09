package com.asiainfo.bdx.datahub.repositorymgr.dao.impl;

import com.asiainfo.bdx.datahub.common.DHConstants;
import com.asiainfo.bdx.datahub.common.dao.BaseJdbcDao;
import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.repositorymgr.dao.IResSearchDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Title :
 * <p/>
 * Description :搜索功能查询实现
 * <p/>
 * CopyRight : CopyRight (c) 2015
 * <p/>
 * <p/>
 * JDK Version Used : JDK 6.0 +
 * <p/>
 * Modification History	:
 * <p/>
 * <pre>NO.    Date    Modified By    Why & What is modified</pre>
 * <pre>1    15.9.26    yanzi        Created</pre>
 * <p/>
 *
 * @author yanzi
 */
@Repository
public class ResSearchDaoImpl  extends BaseJdbcDao implements IResSearchDao {
	private static final Log log = LogFactory.getLog(ResSearchDaoImpl.class);
   
	/*
	 * (non-Javadoc)
	 * @see com.asiainfo.bdx.datahub.repositorymgr.dao.IResSearchDao#resSearch(java.lang.String)
	 */
	public List<Dataitem> resSearch(String searchContext,String userId) throws Exception {
		log.debug("=====开始查询数据对象");
		List<Object> params = new ArrayList<Object>(); 
        StringBuffer sql = new StringBuffer(); 
        
        sql.append(" SELECT  T2.* ");
        sql.append(" FROM   DH_REPOSITORY T1 ");
        sql.append(" INNER JOIN    ( ");
        sql.append("         SELECT  * ");
        sql.append("         FROM  DH_DATAITEM ");
        if(StringUtils.isNotEmpty(searchContext)){
        	sql.append("         WHERE DATAITEM_NAME LIKE '%").append(searchContext);
        	sql.append("%'  OR COMMENT LIKE '%").append(searchContext).append("%' ) T2");
        }
         
        sql.append(" ON  T1.REPOSITORY_ID = T2.REPOSITORY_ID ");
        
        if(StringUtils.isNotEmpty(searchContext)){
        	sql.append(" WHERE  ( T2.PERMIT_TYPE= ");
            sql.append(DHConstants.PERMIT_TYPE_ALL);
        	sql.append(" OR T2.USER_ID ='").append(userId).append("' )");
        }else{
        	sql.append(" WHERE  T2.PERMIT_TYPE= ");
        	sql.append(DHConstants.PERMIT_TYPE_ALL);
        }
        List<Dataitem> list ; 
        try {
            log.debug("ResSearchDaoImpl resSearch() sql.toString(): " + sql.toString());          
            list = getJdbcTemplate().query(sql.toString(), new RowMapper() {

				public Object mapRow(ResultSet rs, int arg1)
						throws SQLException {
					Dataitem item = new Dataitem();
					item.setComment(rs.getString("comment"));
					item.setRepositoryId(rs.getLong("repository_id"));
					item.setDataitemId(rs.getLong("dataitem_id"));
					item.setTag("tag");
					return item;
				}
				 
			});
            log.debug("=======结束查询数据对象");
            return list;
        } catch (Exception e) {
            log.debug("查询数据对象出错,出错原因:" + e.getMessage());
            throw new Exception("查询出错", e);
        }
	 
	}
}
