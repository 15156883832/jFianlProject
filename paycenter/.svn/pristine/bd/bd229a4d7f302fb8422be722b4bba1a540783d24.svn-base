package com.sf.jfinal.qs.config;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

public class ConfigKit {
    public static final String CONFIG_DB = "db_config_mysql.txt";
    private static final String CONFIG_MAIN = "qs_config.txt";
    private static final String CONFIG_EMAIL = "email_config.txt";

    public static boolean isWeixinDevEnabled() {
        return PropKit.getBoolean("dev.weixin.enabled", false);
    }

    public static boolean isDevMode() {
        return PropKit.getBoolean("devMode", true);
    }

    public static String getSqlStorePath() {
        return PathKit.getWebRootPath() + PropKit.get("sql.path");
    }

    public static EmailConfig getEmailConfig() {
        Prop props = PropKit.use(CONFIG_EMAIL);
        EmailConfig config = new EmailConfig();
        config.setHost(props.get("host"));
        config.setUsername(props.get("username"));
        config.setPassword(props.get("password"));
        config.setSalt(props.get("salt"));
        return config;
    }

    public static void init() {
        PropKit.use(CONFIG_MAIN);
        PropKit.use(CONFIG_DB);
    }
}
