package com.asiainfo.bdx.datahub.dataitemmgr.model.custom;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.model.Field;


/**
 * Dataitem业务类
 * @author hpa
 *
 */
public class DataitemDto{
	private Dataitem dataitem = new Dataitem();
	
	private List<Field> fields = new ArrayList<Field>();
	
	public DataitemDto(){
	}
	
	public DataitemDto(Dataitem dataitem, List<Field> fields) {
		super();
		this.dataitem = dataitem;
		this.fields = fields;
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
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
