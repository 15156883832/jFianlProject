package com.sf.jfinal.qs.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.alipay.demo.trade.config.Configs;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.sf.jfinal.qs.config.routes.QSRoutes;
import com.sf.jfinal.qs.model._MappingKit;
import net.dreamlu.event.EventPlugin;
import net.jojowo.sqlit.SqlPlugin;

import javax.servlet.http.HttpServletRequest;

/**
 * API引导式配置
 */
public class QSConfig extends JFinalConfig {
    /**
     * 微信路由
     */
    private WeixinConfig weixinConfig = new WeixinConfig();

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        ConfigKit.init();
        me.setDevMode(ConfigKit.isDevMode());
        me.setViewType(ViewType.JSP);
        me.setBaseViewPath("/WEB-INF/views");

        // 微信配置
        weixinConfig.doConfigConstant(me);
        me.setError500View("/WEB-INF/views/error/Error500.jsp");
        me.setError404View("/WEB-INF/views/error/Error404.jsp");
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add(new QSRoutes());
        // 微信配置
        weixinConfig.doConfigRoute(me);
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // ActiveRecord plugin
        DruidPlugin druidPlugin = createDruidPlugin();
        me.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setShowSql(true);
        _MappingKit.mapping(arp);
        me.add(arp);

        // ehcache plugin
        EhCachePlugin ehCachePlugin = new EhCachePlugin(getClass().getResourceAsStream("/ehcache.xml"));
        me.add(ehCachePlugin);

        // jfinal-event plugin
        EventPlugin eventPlugin = new EventPlugin();
        eventPlugin.scanPackage("cc.jfinal.qs");
        me.add(eventPlugin);

        // config sql plugin
        SqlPlugin sqlPlugin = new SqlPlugin(ConfigKit.getSqlStorePath());
        me.add(sqlPlugin);
    }

    public static DruidPlugin createDruidPlugin() {
        // config druid db pool
        Prop p = PropKit.use(ConfigKit.CONFIG_DB);
        DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
        druidPlugin.addFilter(new StatFilter());
        WallFilter wall = new WallFilter();
        wall.setDbType(JdbcConstants.MYSQL);
        druidPlugin.addFilter(wall);
        return druidPlugin;
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        DruidStatViewHandler dvh = new DruidStatViewHandler("/druid", new IDruidStatViewAuth() {
            public boolean isPermitted(HttpServletRequest request) {
                // 这里只是简单的判断访问者是否登录，还可以做更加细致的权限控制
                return true;
            }
        });
        me.add(dvh);
    }

    @Override
    public void afterJFinalStart() {
        super.afterJFinalStart();
        Configs.init("zfb.properties");
        com.gaols.unipay.weixin.Configs.init("wx.properties");
    }

    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {
        JFinal.start("web", 8080, "/", 5);
    }
}
