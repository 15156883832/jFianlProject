package com.gaols.unipay.weixin;

import com.gaols.unipay.core.AsyncPayNotifyParser;
import com.gaols.unipay.core.NotifyHandler;
import weixin.popular.bean.paymch.MchBaseResult;
import weixin.popular.util.XMLConverUtil;

import javax.servlet.http.HttpServletRequest;

public class WxNotifyHandler implements NotifyHandler {

    @Override
    public String generateResult(boolean handleResult) {
        String retCode = handleResult ? "SUCCESS" : "FAIL";
        MchBaseResult result = new MchBaseResult();
        result.setReturn_code(retCode);
        result.setReturn_msg("OK");
        return XMLConverUtil.convertToXML(result);
    }

    @Override
    public AsyncPayNotifyParser getPayNotifyParser(HttpServletRequest request) {
        return new WxAsyncPayNotifyParser(request);
    }
}
