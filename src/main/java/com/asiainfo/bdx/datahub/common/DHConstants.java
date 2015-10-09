package com.asiainfo.bdx.datahub.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * datahub静态变量类
 * @author hpa
 *
 */
public class DHConstants {
	
    private static final Log log = LogFactory.getLog(DHConstants.class);
    
    //邮件发送配置
    public static String MAIL_HOST;
    public static String MAIL_USERNAME;
    public static String MAIL_PASSWORD;
    public static String MIAL_FROM;
    
    //数据文件上传路径
    public static String DATAFILE_UPLOADPATH;
    //允许上传数据文件类型
    public static String PERMIT_UPLOAD_FILE_TYPE;
	// 加载的配置文件
    public static Properties properties = new Properties();
    
	static {
		try {
			InputStream inStream = DHConstants.class.getClassLoader()
					.getResourceAsStream(
							"." + File.separator + "config.properties");
			properties.load(inStream);
			MAIL_HOST = properties.getProperty("MAIL_HOST");
			MAIL_USERNAME = properties.getProperty("MAIL_USERNAME");
			MAIL_PASSWORD = properties.getProperty("MAIL_PASSWORD");
			MIAL_FROM = properties.getProperty("MIAL_FROM");
			//数据文件上传路径（应用服务器本地硬盘）
			DATAFILE_UPLOADPATH = properties.getProperty("DATAFILE_UPLOADPATH");
			//允许上传数据文件类型
			PERMIT_UPLOAD_FILE_TYPE = properties.getProperty("PERMIT_UPLOAD_FILE_TYPE");
		} catch (IOException e) {
			log.error("Error loading configuration file", e);
		}    
	 }
	/**
	 * 数据对象(Dataitem)的获取方式(supplystyle)
	 * @author hpa
	 *
	 */
	public interface SUPPLYSTYLE{
		/**
		 * 批量
		 */
		Integer BATCH = 1;
		
		/**
		 * 小批
		 */
		Integer SUBLOTS = 2;
		
		/**
		 * 流数据		
		 */
		Integer STREAMDATA = 3;
		
		/**
		 * 单条查询
		 */
		Integer SINGLESELECT = 4;
	}
	
	public static class Login{
		public static int SUCESS = 1; //成功
		public static String SUCESS_DSC="成功";
		public static int WRONG_PWD = 2; //密码错误
		public static String WRONG_PWD_DSC="密码错误" ;
		public static int NO_USER = 3; //用户不存在
		public static String NO_USER_DSC = "用户不存在"; 
	}
	
	public static final 	Integer PERMIT_TYPE_ALL = 2; // 数据对象的授权方式
	public static final    String  UPLOAD_FLAG = "1";//数据宝藏类型：传家宝藏
	public static final    String  DOWNDLOAD_FLAG= "2";//数据宝藏类型：收购宝藏
		
	/**
     * 字段之间的分割符
     */
    public static final String FIELD_SEPARATOR = "_FIELD_SEPARATOR_";

    /**
     * 一组代码之间的分割符
     */
    public static final String GROUP_SEPARATOR = "_GROUP_SEPARATOR_";
    
    /**
     * properties配置文件名
     */
    public static final String CONFIG_PROPERTIES = "config.properties";
 
    /**
	 * 数据对象的更新周期
	 * @author hpa
	 *
	 */
	public interface REFRESHTYPE{
		Integer PER_YEAR = 1;
		//每季度
		Integer PER_QUARTER = 2;
		Integer PER_MONTH = 3;
		Integer PER_WEEK = 4;
		Integer PER_DAY = 5;
	}
}
