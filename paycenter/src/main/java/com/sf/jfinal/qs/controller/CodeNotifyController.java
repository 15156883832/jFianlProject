package com.sf.jfinal.qs.controller;

import com.gaols.unipay.NotifyHandlerFactory;
import com.gaols.unipay.core.AsyncPayNotifyParser;
import com.gaols.unipay.core.NotifyHandler;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.sf.jfinal.qs.service.CodeNotifyService;

import java.util.Map;

/**
 * 二维码支付回调处理。
 */
public class CodeNotifyController extends Controller {

    private static Log logger = Log.getLog(CodeNotifyController.class);

    public void callback() {
        String payType = getPara(0);
        logger.error("[二维码购买] notify start, payType is: " + payType);
        NotifyHandler notifyHandler = NotifyHandlerFactory.getNotifyHandler(payType);
        AsyncPayNotifyParser parser = notifyHandler.getPayNotifyParser(getRequest());
        Map<String, String> parasMap = parser.getNotifyParasMap();
        String outTradeNo = parasMap.get("out_trade_no");

        if (parser.isSignValid()) {
            logger.error(String.format("check sign[%s] passed", outTradeNo));
            enhance(CodeNotifyService.class).handleCodeNotify(outTradeNo, parasMap, parser.isSuccess());
        } else {
            logger.error(String.format("check sign[%s] failed", parasMap.get("out_trade_no")));
        }
        renderText(notifyHandler.generateResult(true));
    }

}
