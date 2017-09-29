package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;


public abstract class Agent {

	/**
	 * 获取企业号应用
	 */
	public static JSONObject get(String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_AGENT_GET, accessToken, agentid);
		String respJson = wc.get(getUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 设置企业号应用
	 */
	public static JSONObject set(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String setUrl = MessageFormat.format(Constants.URL_AGENT_SET, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(setUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取应用概况列表
	 */
	public static JSONObject list(WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String listUrl = MessageFormat.format(Constants.URL_AGENT_LIST, accessToken);
		String respJson = wc.get(listUrl);
		return JSONObject.fromObject(respJson);
	}

}
