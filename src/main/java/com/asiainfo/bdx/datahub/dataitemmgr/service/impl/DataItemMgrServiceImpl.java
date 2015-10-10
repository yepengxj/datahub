package com.asiainfo.bdx.datahub.dataitemmgr.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.asiainfo.bdx.datahub.common.DHConstants;
import com.asiainfo.bdx.datahub.dataitemmgr.dao.IDataItemMgrDao;
import com.asiainfo.bdx.datahub.dataitemmgr.dao.IFieldDao;
import com.asiainfo.bdx.datahub.dataitemmgr.dao.IUploadlogDao;
import com.asiainfo.bdx.datahub.dataitemmgr.model.custom.DataitemDto;
import com.asiainfo.bdx.datahub.dataitemmgr.service.IDataItemMgrService;
import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.model.Field;
import com.asiainfo.bdx.datahub.model.Uploadlog;

@Service
public class DataItemMgrServiceImpl implements IDataItemMgrService{
	Log log = LogFactory.getLog(DataItemMgrServiceImpl.class);
	@Resource
	private IDataItemMgrDao dataItemMgrDao;
	
	@Resource
	private IFieldDao fieldDao;
	
	@Resource
	private IUploadlogDao uploadlogDao;

	
	public void addDataitem(DataitemDto dataitemDto) throws Exception{
		Dataitem dataitem = dataitemDto.getDataitem();
		List<Field> fieldList = dataitemDto.getFields();
		
		try {
			dataItemMgrDao.addDataitem(dataitem);
		} catch (Exception e) {
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.service.impl.DataItemMgrServiceImpl.addDataitem():insert dataitem error！");
			log.error("失败原因："+e.getMessage());
			throw new Exception("insert_dataitem_error",e);
		}
		
		try {
			if(fieldList != null && fieldList.size() > 0){
				fieldDao.addField(fieldList);
			}
		} catch (Exception e) {
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.service.impl.DataItemMgrServiceImpl.addDataitem():insert field error！");
			log.error("失败原因："+e.getMessage());
			throw new Exception("insert_field_error",e);
		}
	}
	
	

	
	public void updateDataitem(DataitemDto dataitemDto) throws Exception {
		Dataitem dataitem = dataitemDto.getDataitem();
		List<Field> fieldList = dataitemDto.getFields();
		
		try {
			dataItemMgrDao.updateDataitem(dataitem);
		} catch (Exception e) {
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.service.impl.DataItemMgrServiceImpl.updateDataitem():update dataitem error！");
			log.error("失败原因："+e.getMessage());
			throw new Exception("update_dataitem_error",e);
		}
		
		try {
			if(fieldList != null && fieldList.size() > 0){
				//fieldDao.updateField(fieldList);
				fieldDao.deleteField(dataitem.getDataitemId());
				fieldDao.addField(fieldList);
			}
		} catch (Exception e) {
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.service.impl.DataItemMgrServiceImpl.updateDataitem():update field error！");
			log.error("失败原因："+e.getMessage());
			throw new Exception("update_field_error",e);
		}
	}

	
	public DataitemDto queryDataitemDtoById(Long userId,Long repositoryId,
			Long dataitemId) throws Exception {
		DataitemDto dataitemDto = new DataitemDto();
		dataitemDto.setDataitem(dataItemMgrDao.queryDateitemById(userId,repositoryId,dataitemId));
		Dataitem dataitem=dataitemDto.getDataitem();
		//更新周期
		dataitem.setRefreshType(DHConstants.REFRESHTYPE.PER_MONTH);
		
		//最新周期
		dataitem.setRefreshDate(new Date());
				
		//获取方式
		dataitem.setSupplyStyle(DHConstants.SUPPLYSTYLE.BATCH);
		dataitemDto.setFields(fieldDao.queryFieldById(dataitemId));
		return dataitemDto;
	}


	/* (non-Javadoc)
	 * @see com.asiainfo.bdx.datahub.dataitemmgr.service.IDataItemMgrService#queryDataitemDtoById(java.lang.Long)
	 */
	
	public String queryDataitemById(Long dataitemId,String downDate) throws Exception {
		return dataItemMgrDao.queryDateitemById(dataitemId,downDate);
	}



	/* (non-Javadoc)
	 * @see com.asiainfo.bdx.datahub.dataitemmgr.service.IDataItemMgrService#writeUploadLog()
	 */
	
	public void insertUploadLog(Dataitem dataitem,String filePath) throws Exception {
		dataItemMgrDao.afterUploadFile(dataitem);
		
		Uploadlog uploadlog = new Uploadlog();
		uploadlog.setDataitemId(dataitem.getDataitemId());
		uploadlog.setDataDate(Timestamp.valueOf(dataitem.getDataDate()));
		uploadlog.setFileName(filePath);
		uploadlogDao.insertUploadlog(uploadlog);
	}
}
