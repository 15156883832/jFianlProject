package com.sf.jfinal.qs.controller;

import com.gaols.unipay.NotifyHandlerFactory;
import com.gaols.unipay.core.AsyncPayNotifyParser;
import com.gaols.unipay.core.NotifyHandler;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.sf.jfinal.qs.service.SFNotifyService;

import java.util.Map;

/**
 * 服务商激活码注册Vip回调处理
 */
public class SFSignUpController extends Controller {

    private static Log logger = Log.getLog(SFNotifyController.class);

    public void callback1() {
        String payType = getPara(0);
        logger.error("[思方erp VIP注册] notify start, payType is: " + payType);
        NotifyHandler notifyHandler = NotifyHandlerFactory.getNotifyHandler(payType);
        AsyncPayNotifyParser parser = notifyHandler.getPayNotifyParser(getRequest());
        Map<String, String> parasMap = parser.getNotifyParasMap();
        String outTradeNo = parasMap.get("out_trade_no");

        if (parser.isSignValid()) {
            logger.error(String.format("check sign[%s] passed", outTradeNo));
            enhance(SFNotifyService.class).handleSFNotify1(outTradeNo, parasMap, parser.isSuccess());
        } else {
            logger.error(String.format("check sign[%s] failed", parasMap.get("out_trade_no")));
        }
        renderText(notifyHandler.generateResult(true));
    }

}
