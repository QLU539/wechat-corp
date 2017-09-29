package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;


public abstract class Message {

	/**
	 * 发送消息
	 */
	public static JSONObject send(JSONObject msgJson, WechatClient wc, String accessToken) throws WechatException {
		String sendUrl = MessageFormat.format(Constants.URL_MESSAGE_SEND, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(sendUrl, msg);
		return JSONObject.fromObject(respJson);
	}

}
