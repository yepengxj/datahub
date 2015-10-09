package com.asiainfo.bdx.datahub.common.util;

import org.apache.commons.lang3.RandomStringUtils;

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
 * <pre>1    15.9.27    bob        Created</pre>
 * <p/>
 *
 * @author bob
 */
public class RandomUtils extends RandomStringUtils {
    public static String getNumeric(int i) {
        return randomNumeric(i);
    }

    public static void main(String[] args) {
        System.out.println(RandomUtils.getNumeric(8));
    }
}

