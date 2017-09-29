package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;


public abstract class General {

	/**
	 * 邀请成员关注
	 */
	public static JSONObject inviteSend(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String inviteUrl = MessageFormat.format(Constants.URL_INVITE_SEND, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(inviteUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取微信服务器的ip段
	 */
	public static JSONObject getCallbackIp(WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getCallbackIpUrl = MessageFormat.format(Constants.URL_GETCALLBACKIP, accessToken);
		String respJson = wc.get(getCallbackIpUrl);
		return JSONObject.fromObject(respJson);
	}

}
