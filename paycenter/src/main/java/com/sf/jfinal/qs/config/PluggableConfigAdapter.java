package com.sf.jfinal.qs.config;

import com.jfinal.config.*;

public abstract class PluggableConfigAdapter extends JFinalConfig implements PluggableConfig {
    public final void configConstant(Constants me) {
        if (pluggable()) {
            doConfigConstant(me);
        }
    }

    public void doConfigConstant(Constants me) {
    }

    public final void configRoute(Routes me) {
        if (pluggable()) {
            doConfigRoute(me);
        }
    }

    public void doConfigRoute(Routes me) {
    }

    public final void configPlugin(Plugins me) {
        if (pluggable()) {
            doConfigPlugin(me);
        }
    }

    public void doConfigPlugin(Plugins me) {
    }

    public final void configInterceptor(Interceptors me) {
        if (pluggable()) {
            doConfigInterceptor(me);
        }
    }

    public void doConfigInterceptor(Interceptors me) {
    }

    public final void configHandler(Handlers me) {
        if (pluggable()) {
            doConfigHandler(me);
        }
    }

    public void doConfigHandler(Handlers me) {
    }
}
