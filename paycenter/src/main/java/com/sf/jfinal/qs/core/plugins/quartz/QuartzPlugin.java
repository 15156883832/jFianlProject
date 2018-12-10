package com.sf.jfinal.qs.core.plugins.quartz;

import com.jfinal.log.Log;
import com.jfinal.plugin.IPlugin;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzPlugin implements IPlugin {
    private static Log log = Log.getLog(QuartzPlugin.class);

    @Override
    public boolean start() {
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("start quartz failed", e);
        }
        return true;
    }

    @Override
    public boolean stop() {
        return false;
    }
}
