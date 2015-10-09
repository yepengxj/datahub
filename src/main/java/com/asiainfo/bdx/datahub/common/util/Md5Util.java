package com.asiainfo.bdx.datahub.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: Asiainfo Technologies(China),Inc
 * </p>
 * <p>
 * Date: 2006-6-11 20:42:03
 * </p>
 * 
 * @version 1.0
 */

public class Md5Util {
	public static String MD5(String str) {
		return DigestUtils.md5Hex(str);
	}
	public static void main(String[] args) {
		String test = "admin";
		System.err.println(Md5Util.MD5(test));
	}
}
