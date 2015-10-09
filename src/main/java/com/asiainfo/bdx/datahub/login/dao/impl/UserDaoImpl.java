package com.asiainfo.bdx.datahub.login.dao.impl;

import com.asiainfo.bdx.datahub.common.dao.BaseJdbcDao;
import com.asiainfo.bdx.datahub.login.dao.IUserDao;
import com.asiainfo.bdx.datahub.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDaoImpl extends BaseJdbcDao implements IUserDao {

	private static final Log log = LogFactory.getLog(UserDaoImpl.class);

	public User getUser(String loginName) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT LOGIN_PASSWD,USER_ID,LOGIN_NAME FROM DH_USER WHERE LOGIN_NAME=?");
			return (User) getJdbcTemplate().queryForObject(sql.toString(), new Object[]{loginName}, BeanPropertyRowMapper.newInstance(User.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public class UserRowMapper implements ParameterizedRowMapper<User>{

	
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			User user = new User();
			user.setLoginName(rs.getString(index++));
			user.setLoginPasswd(rs.getString(index++));
			return user;
		}
	}


	public void updatePWD(String loginName, String pwd) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DH_USER SET LOGIN_PASSWD=? WHERE LOGIN_NAME=?");
		Object[] param = new Object[]{pwd,loginName};
		getJdbcTemplate().update(sql.toString(), param);
	}


	public String getEmail(String loginName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT EMAIL FROM DH_USER WHERE LOGIN_NAME=?");
		return getJdbcTemplate().queryForObject(sql.toString(), String.class,loginName);
	}

	 
	
	

}
