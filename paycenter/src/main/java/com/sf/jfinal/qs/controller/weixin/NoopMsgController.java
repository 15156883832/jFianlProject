package com.sf.jfinal.qs.controller.weixin;

import com.jfinal.weixin.sdk.jfinal.MsgController;
import com.jfinal.weixin.sdk.msg.in.*;
import com.jfinal.weixin.sdk.msg.in.event.*;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;

public abstract class NoopMsgController extends MsgController {

    @Override
    protected void processInTextMsg(InTextMsg inTextMsg) {
        renderText("");
    }

    @Override
    protected void processInImageMsg(InImageMsg inImageMsg) {
        renderText("");
    }

    @Override
    protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
        renderText("");
    }

    @Override
    protected void processInVideoMsg(InVideoMsg inVideoMsg) {
        renderText("");
    }

    @Override
    protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg) {
        renderText("");
    }

    @Override
    protected void processInLocationMsg(InLocationMsg inLocationMsg) {
        renderText("");
    }

    @Override
    protected void processInLinkMsg(InLinkMsg inLinkMsg) {
        renderText("");
    }

    @Override
    protected void processInCustomEvent(InCustomEvent inCustomEvent) {
        renderText("");
    }

    @Override
    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        renderText("");
    }

    @Override
    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        renderText("");
    }

    @Override
    protected void processInLocationEvent(InLocationEvent inLocationEvent) {
        renderText("");
    }

    @Override
    protected void processInMassEvent(InMassEvent inMassEvent) {
        renderText("");
    }

    @Override
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {
        renderText("");
    }

    @Override
    protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
        renderText("");
    }

    @Override
    protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {
        renderText("");
    }

    @Override
    protected void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent) {
        renderText("");
    }

    @Override
    protected void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent) {
        renderText("");
    }

    @Override
    protected void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent) {
        renderText("");
    }

    @Override
    protected void processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent) {
        renderText("");
    }

    @Override
    protected void processInWifiEvent(InWifiEvent inWifiEvent) {
        renderText("");
    }

    @Override
    protected void processInUserViewCardEvent(InUserViewCardEvent inUserViewCardEvent) {
        renderText("");
    }

    @Override
    protected void processInSubmitMemberCardEvent(InSubmitMemberCardEvent inSubmitMemberCardEvent) {
        renderText("");
    }

    @Override
    protected void processInUpdateMemberCardEvent(InUpdateMemberCardEvent inUpdateMemberCardEvent) {
        renderText("");
    }

    @Override
    protected void processInUserPayFromCardEvent(InUserPayFromCardEvent inUserPayFromCardEvent) {
        renderText("");
    }

    @Override
    protected void processInMerChantOrderEvent(InMerChantOrderEvent inMerChantOrderEvent) {
        renderText("");
    }

    @Override
    protected void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent) {
        renderText("");
    }

    @Override
    protected void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg) {
        renderText("");
    }
}
