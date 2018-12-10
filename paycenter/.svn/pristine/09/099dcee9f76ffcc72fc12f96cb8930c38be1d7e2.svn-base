package com.sf.jfinal.qs.core.sms;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gaols
 */
public class PaymentNotifyService {

    private ThreadPoolExecutor workerService;
    private static PaymentNotifyService worker = new PaymentNotifyService();
    private static final int QUEUE_SIZE = 2000;

    public static PaymentNotifyService me() {
        return worker;
    }

    private PaymentNotifyService() {
        final int POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
        workerService = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE * 2,
                30, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(QUEUE_SIZE),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    private void work(PaymentNotifyJob job) {
        workerService.execute(job);
    }

    public static void submitWork(PaymentNotifyJob job) {
        me().work(job);
    }
}
