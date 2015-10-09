package com.asiainfo.bdx.datahub.repositorymgr.action;

import com.asiainfo.bdx.datahub.common.action.BaseAction;
import com.asiainfo.bdx.datahub.model.Dataitem;
import com.asiainfo.bdx.datahub.repositorymgr.service.IDataItemsService;
import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * <p/>
 * Description :收购宝藏呈现页面
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
public class DataItemsAction extends BaseAction {
	private static final Log log = LogFactory.getLog(DataItemsAction.class);
	@Resource
	private IDataItemsService dataItemsService;

	public List<Dataitem> listDataItem;
	public String detailDataitemId;
 
 
	public String getDetailDataitemId() {
		return detailDataitemId;
	}

	public void setDetailDataitemId(String detailDataitemId) {
		this.detailDataitemId = detailDataitemId;
	}

	public List<Dataitem> getListDataItem() {
		return listDataItem;
	}

	public void setListDataItem(List<Dataitem> listDataItem) {
		this.listDataItem = listDataItem;
	}

	/**
	 * 获取我的宝藏页面：我的记录 列表
	 * 
	 * @return
	 */
	public String dataitemList() {
		String userId = getSessionUserId();
		String keyWord1 = getRequest().getParameter("keyword");// 关键字
		String keyWord = null;
		try {
			keyWord = java.net.URLDecoder.decode(keyWord1, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String tradeType = getRequest().getParameter("tradeType");// 交易类型:传家宝藏和收购宝藏
		String startTime = getRequest().getParameter("startTime");// 起始时间
		String endTime = getRequest().getParameter("endTime");// 结束时间
		log.debug("userId = " + userId + " ; " + "keyWord = " + keyWord + " ; "
				+ "tradeType = " + tradeType + " ; " + "startTime = "
				+ startTime + " ; " + "endTime = " + endTime);

		try {
			List<Dataitem> list = dataItemsService.getDataItems(userId,
					keyWord, tradeType, startTime, endTime);

			List<Dataitem> nlist = new ArrayList<Dataitem>();

			for (Dataitem dataitem : list) {
				if (dataitem.getTradeType().equals("1")) {
					dataitem.setTradeType("买入");
				} else {
					dataitem.setTradeType("卖出");
				}
				if (dataitem.getRefreshType() == 1) {
					dataitem.setStrRefreshtype("日");
				} else if (dataitem.getRefreshType() == 2) {
					dataitem.setStrRefreshtype("月");
				} else {
					dataitem.setStrRefreshtype("年");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dataDate = sdf.format(dataitem.getOptime());
				dataitem.setDataDate(dataDate);

				nlist.add(dataitem);
			}

			log.debug("dataItem list:" + nlist.size());

			if (nlist != null) {
				Map map = new HashMap();
				map.put("datas", nlist);
				String jsonStr = JSONArray.fromObject(map).toString();
				log.debug("获取的数据宝藏 列表：" + jsonStr);
				sendJson(getResponse(), jsonStr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取传家宝藏或者收购宝藏列表
	 * 
	 * @return
	 */
	public String dataitemTypeList() {
		// TODO 測試用1002
		String userId = getSessionUserId();
		String tradeType = getRequest().getParameter("tradeType");// 交易类型:传家宝藏和收购宝藏

		log.debug("获取宝藏类型：" + tradeType + ", 用户ID: " + userId);
		try {
			List<Dataitem> list = dataItemsService.dataitemTypeList(tradeType,
					userId,"");
			Map map = new HashMap();
			map.put("datas", list);
			String jsonStr = JSONArray.fromObject(map).toString();
			log.debug("获取的我的宝藏记录：" + jsonStr);
			sendJson(getResponse(), jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String dataitemDown() {
	 
		String dataitemId = getRequest().getParameter("dataitemId"); 
 
		log.debug("获取宝藏类型："+ dataitemId);
		try {
			List<Dataitem> list = dataItemsService.dataitemTypeList("2",
					"",dataitemId);
			Map map = new HashMap();
			map.put("datas", list);
			String jsonStr = JSONArray.fromObject(map).toString();
			log.debug("获取的我的宝藏记录：" + jsonStr);
			sendJson(getResponse(), jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 处理数据项下载请求
	 * 
	 * @return
	 */
	public InputStream downDataitem() {
		// 调用普安的接口
		return null;
	}

	/**
	 * 数据宝藏搜索页面
	 * 
	 * @return
	 */
	public String search() {
		try {
			this.getRequest().setCharacterEncoding("UTF-8");
			String keyWord = getRequest().getParameter("keyword");// 关键字
			/*
			 * if (StringUtils.isNotEmpty(keyWord)) { keyWord =
			 * java.net.URLDecoder.decode(keyWord, "UTF-8"); }
			 */
			List<Dataitem> list = dataItemsService.resSearch(keyWord);
			Map map = new HashMap();
			map.put("datas", list);
			String jsonStr = JSONArray.fromObject(map).toString();
			log.debug("获取搜索后数据宝藏：" + jsonStr);
			sendJson(getResponse(), jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	 
	public String baozangDetail() {
		log.debug("查询宝藏明细信息");
		//this.detailDataitemId= this.getRequest().getParameter("");
		try {
			this.listDataItem = dataItemsService.resSearch("");
			return "success";
			// Map map = new HashMap();
			// map.put("datas", list);
			// String jsonStr = JSONArray.fromObject(map).toString();
			// log.debug("获取搜索后数据宝藏：" + jsonStr);
			// sendJson(getResponse(), jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	 
}
