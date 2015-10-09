package com.asiainfo.bdx.datahub.dataitemmgr.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.bdx.datahub.common.DHConstants;
import com.asiainfo.bdx.datahub.common.action.BaseAction;
import com.asiainfo.bdx.datahub.dataitemmgr.model.custom.DataitemDto;
import com.asiainfo.bdx.datahub.dataitemmgr.service.IDataItemMgrService;
import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.model.Field;

public class DataItemMgrAction extends BaseAction{
	Log log = LogFactory.getLog(DataItemMgrAction.class);
	/**
	 * 仓库的维护的功能还未开发
	 * 默认选择一个仓库
	 */
	private Long repositoryId = 1l;
	
	private Long dataitemId = 1l;
	
	private Long userId = 1l;
	
	/**
	 * 数据对象实体类
	 */
	private Dataitem dataitem;
	
	/**
	 * 数据项
	 */
	private Field field;
	
	/**
	 * 数据项集合
	 */
	private List<Field> fieldList;
	
	@Resource
	private IDataItemMgrService dataItemMgrService;
	
	private void initDataitem(){
		dataitem = new Dataitem();
		dataitem.setRepositoryId(repositoryId);
		dataitem.setUserId(1l);
		dataitem.setDataitemId(1010l);
		if(StringUtils.isEmpty(dataitem.getDataitemName())){
			dataitem.setDataitemName("重庆终端数据3");
		}
		if(StringUtils.isEmpty(dataitem.getSampleFilename())){
			dataitem.setSampleFilename("sample3.jpg");
		}
		if(StringUtils.isEmpty(dataitem.getComment())){
			dataitem.setComment("描述描述描述描述描述描述描述描述描述3");
		}
		//文件格式
		dataitem.setFileType("excel3");
		
		//更新周期
		dataitem.setRefreshType(DHConstants.REFRESHTYPE.PER_MONTH);
		
		//最新周期
		dataitem.setRefreshDate(new Date());
				
		//获取方式
		dataitem.setSupplyStyle(DHConstants.SUPPLYSTYLE.BATCH);
		
		
		fieldList = new ArrayList<Field>();
		Field field = new Field();
		field.setDataitemId(dataitem.getDataitemId());
		field.setFieldId(7l);
		field.setFieldRawname("phone_id3");
		field.setFieldName("手机型号3");
		fieldList.add(field);
		
		field = new Field();
		field.setDataitemId(dataitem.getDataitemId());
		field.setFieldId(7l);
		field.setFieldRawname("phone_brand3");
		field.setFieldName("手机品牌3");
		fieldList.add(field);
		
		field = new Field();
		field.setDataitemId(dataitem.getDataitemId());
		field.setFieldId(7l);
		field.setFieldRawname("company3");
		field.setFieldName("出品厂商3");
		fieldList.add(field);
	}
	
	/*
	 * #############################################################################
	 * business method portion start
	 */
	/**
	 * 新增数据对象 start
	 * @return
	 * @throws Exception 
	 */
	public String addDataitem(){
		/**
		 * 模拟从前端获取数据
		 */
		initDataitem();
		DataitemDto dataitemDto = new DataitemDto();
		dataitemDto.setDataitem(dataitem);
		dataitemDto.setFields(fieldList);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			dataItemMgrService.addDataitem(dataitemDto);
			jsonMap.put("result", "success");
		} catch (Exception e) {
			String message = e.getMessage();
			if("insert_dataitem_error".equals(message)){
				jsonMap.put("result","dataitem_error");
				log.error("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.addDataitem:新增数据对象失败！");
			}else if("insert_field_error".equals(message)){
				jsonMap.put("result","field_error");
				log.error("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.addDataitem:新增数据项失败！");
			}
		}
		
        String listJson = JSONObject.fromObject(jsonMap).toString();
        try {
			sendJson(getResponse(), listJson.toString());
		} catch (Exception e) {
			System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.addDataitem:向前端传输json格式的结果失败！");
			System.out.println("result: " + jsonMap.get("result"));
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.addDataitem:向前端传输json格式的结果失败！");
			log.error(e.getMessage());
		}
		return null;
	}
	/**
	 * 数据项详细信息展示
	 * @return
	 */
	public String updateDataitem(){
		DataitemDto dataitemDto = new DataitemDto();
		try {
			dataitemDto = dataItemMgrService.queryDataitemDtoById(userId,repositoryId, dataitemId);
		} catch (Exception e) {          
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.updateDataitem:查询数据对象结果失败！");
			e.printStackTrace();
		}
		
		String listJson = JSONObject.fromObject(dataitemDto).toString();
        try {
			sendJson(getResponse(), listJson.toString());
		} catch (Exception e) {
		
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.updateDataitem:查询数据对象结果失败！");
			log.error(e.getMessage());
		}
		return null;
		
	}
	/**
	 * 数据项详细信息展示 
	 */
    public void queryDataitem(){
    	this.userId = 0l;
    	this.repositoryId = 0l;
    	this.updateDataitem();
    }
	
	/**
	 * 修改数据对象
	 * @return
	 */
	public String modifyDataitem(){
		/**
		 * 模拟从前端获取数据
		 */
		initDataitem();
		DataitemDto dataitemDto = new DataitemDto();
		dataitemDto.setDataitem(dataitem);
		dataitemDto.setFields(fieldList);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			dataItemMgrService.updateDataitem(dataitemDto);
			jsonMap.put("result","success");
		} catch (Exception e) {
			String message = e.getMessage();
			if("update_dataitem_error".equals(message)){
				jsonMap.put("result","dataitem_error");
				log.error("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.modifyDataitem:修改数据对象失败！");
			}else if("update_field_error".equals(message)){
				jsonMap.put("result","field_error");
				log.error("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.modifyDataitem:修改数据项失败！");
			}
		}
		
        String listJson = JSONObject.fromObject(jsonMap).toString();
        try {
			sendJson(getResponse(), listJson.toString());
		} catch (Exception e) {
			System.out.println("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.addDataitem:向前端传输json格式的结果失败！");
			System.out.println("result: " + jsonMap.get("result"));
			log.error("com.asiainfo.bdx.datahub.dataitemmgr.action.DataItemMgrAction.addDataitem:向前端传输json格式的结果失败！");
			log.error(e.getMessage());
		}
		return null;
	}
	/*
	 * #############################################################################
	 * business method portion end
	 */
	
	/*
	 * #############################################################################
	 * getting、setter portion start
	 */
	
	/**
	 * @return the repositoryId
	 */
	public Long getRepositoryId() {
		return repositoryId;
	}

	/**
	 * @param repositoryId the repositoryId to set
	 */
	public void setRepositoryId(Long repositoryId) {
		this.repositoryId = repositoryId;
	}
	
	/**
	 * @return the dataitemId
	 */
	public Long getDataitemId() {
		return dataitemId;
	}

	/**
	 * @param dataitemId the dataitemId to set
	 */
	public void setDataitemId(Long dataitemId) {
		this.dataitemId = dataitemId;
	}
	
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the dataitem
	 */
	public Dataitem getDataitem() {
		return dataitem;
	}

	/**
	 * @param dataitem the dataitem to set
	 */
	public void setDataitem(Dataitem dataitem) {
		this.dataitem = dataitem;
	}

	/**
	 * @return the field
	 */
	public Field getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * @return the fieldList
	 */
	public List<Field> getFieldList() {
		return fieldList;
	}

	/**
	 * @param fieldList the fieldList to set
	 */
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}
	
	/*
	 * #############################################################################
	 * getting、setter portion end
	 */
}
