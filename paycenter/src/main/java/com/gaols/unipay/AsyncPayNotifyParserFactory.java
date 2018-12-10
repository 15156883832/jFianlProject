package com.gaols.unipay;

import com.gaols.unipay.alipay.AlipayAsyncPayNotifyParser;
import com.gaols.unipay.core.AsyncPayNotifyParser;
import com.gaols.unipay.weixin.WxAsyncPayNotifyParser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gaols
 */
public class AsyncPayNotifyParserFactory {
    /**
     * 根据支付类型，获取异步通知的处理器。
     *
     * @param type 支付类型
     * @return 异步支付通知处理器
     */
    public static AsyncPayNotifyParser getNotifyParser(PayType type, HttpServletRequest request) {
        switch (type) {
            case wx:
                return new WxAsyncPayNotifyParser(request);
            case alipay:
                return new AlipayAsyncPayNotifyParser(request);
        }

        throw new RuntimeException("no such type:" + type);
    }
}
