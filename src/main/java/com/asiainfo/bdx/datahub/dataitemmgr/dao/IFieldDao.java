package com.asiainfo.bdx.datahub.dataitemmgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.asiainfo.bdx.datahub.model.Field;

public interface IFieldDao {
	/**
	 * 增加数据
	 * @param field
	 */
	public void addField(Field field)
			throws Exception;
	
	/**
	 * 增加数据集合
	 * @param fieldList
	 */
	public boolean addField(List<Field> fieldList)
			throws Exception;
	
	/**
	 * 修改数据对象
	 * @param dataitemId
	 * @return
	 * @throws Exception
	 */
	public List<Field> queryFieldById(Long dataitemId)
			throws Exception;
	
	/**
	 * 修改数据项
	 * @param field
	 */
	public void updateField(Field field)
			throws Exception;
	
	/**
	 * 修改数据项集合
	 * @param fieldList
	 * @throws Exception
	 */
	public boolean updateField(List<Field> fieldList)
		throws Exception;
	
	/**
	 * 删除数据对象ID对象的所有数据项
	 * @param dataitemId
	 */
	public void deleteField(Long dataitemId);
}
