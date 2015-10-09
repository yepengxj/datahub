package com.asiainfo.bdx.datahub.repositorymgr.service;

import java.util.List;

/**
 * Title :
 * <p/>
 * Description :
 * <p/>
 * CopyRight : CopyRight (c) 2014
 * <p/>
 * <p/>
 * JDK Version Used : JDK 5.0 +
 * <p/>
 * Modification History	:
 * <p/>
 * <pre>NO.    Date    Modified By    Why & What is modified</pre>
 * <pre>1    15.9.26    bob        Created</pre>
 * <p/>
 *
 * @author bob
 */
public interface IRepositoryMgrService {

    public List getRepositories(String userid, String keyWord, String tradeType, String startTime, String endTime);
}
