package com.sf.jfinal.qs.controller;

import com.gaols.unipay.NotifyHandlerFactory;
import com.gaols.unipay.core.AsyncPayNotifyParser;
import com.gaols.unipay.core.NotifyHandler;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.sf.jfinal.qs.service.DetergentNotifyService;
import com.sf.jfinal.qs.service.FaucetNotifyService;

import java.util.Map;

/**
 * 水龙头支付回调处理。
 */
public class FaucetNotifyController extends Controller {

    private static Log logger = Log.getLog(FaucetNotifyController.class);

    public void callback() {
        String payType = getPara(0);
        logger.error("[水龙头购买] notify start, payType is: " + payType);
        NotifyHandler notifyHandler = NotifyHandlerFactory.getNotifyHandler(payType);
        AsyncPayNotifyParser parser = notifyHandler.getPayNotifyParser(getRequest());
        Map<String, String> parasMap = parser.getNotifyParasMap();
        String outTradeNo = parasMap.get("out_trade_no");

        if (parser.isSignValid()) {
            logger.error(String.format("check sign[%s] passed", outTradeNo));
            enhance(FaucetNotifyService.class).handleFaucetNotify(outTradeNo, parasMap, parser.isSuccess());
        } else {
            logger.error(String.format("check sign[%s] failed", parasMap.get("out_trade_no")));
        }
        renderText(notifyHandler.generateResult(true));
    }

}
