package com.sf.jfinal.qs.controller.weixin;


import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.sf.jfinal.qs.core.tools.weixin.ApiConfigFactory;
import com.sf.jfinal.qs.core.tools.weixin.SFWxKit;

public class WeixinMsgController extends NoopMsgController {
    public ApiConfig getApiConfig() {
        return ApiConfigFactory.create(SFWxKit.CONFIG_SFWx);
    }

    protected void processInTextMsg(InTextMsg inTextMsg) {
    }

    protected void processInImageMsg(InImageMsg inImageMsg) {
        renderText("");
    }

    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
        outMsg.setContent("欢迎关注思方科技。");
        render(outMsg);
    }
}
