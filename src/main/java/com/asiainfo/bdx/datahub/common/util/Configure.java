package com.asiainfo.bdx.datahub.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configure {

    private static final Configure CONFIGURE = new Configure();

    private static Properties props;

    private static final String TRANSFER_PROP = "../resources/config.properties";

    private Configure() {
    }

    public static Configure getInstance() {
        return CONFIGURE;
    }

    public static String getProperty(String key) {
        if (props == null) {
            initProp();
        }

        String value = props.getProperty(key);
        return value.trim();
    }
    
    public static String getProperty(String key,String defval) {
        if (props == null) {
            initProp();
        }

        String value = props.getProperty(key);
        if(value == null || "".equals(value)) {
            value = defval;
        } else {
            value = value.trim();
        }
        return value;
    }

    private static void initProp() {
        InputStream in = null;
        try {
            in = new FileInputStream(TRANSFER_PROP);
        } catch (FileNotFoundException e) {
            in = Configure.class.getResourceAsStream("/config.properties");
        }
        props = new Properties();
        try {
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
