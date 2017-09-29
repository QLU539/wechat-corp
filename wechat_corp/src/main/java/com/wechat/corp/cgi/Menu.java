package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;

public abstract class Menu {

	/**
	 * 创建菜单
	 */
	public static JSONObject create(JSONObject msgJson, String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String createUrl = MessageFormat.format(Constants.URL_MENU_CREATE, accessToken, agentid);
		String msg = msgJson.toString();
		String respJson = wc.post(createUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 删除菜单
	 */
	public static JSONObject delete(String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deleteUrl = MessageFormat.format(Constants.URL_MENU_DELETE, accessToken, agentid);
		String respJson = wc.get(deleteUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取菜单
	 */
	public static JSONObject get(String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_MENU_GET, accessToken, agentid);
		String respJson = wc.get(getUrl);
		return JSONObject.fromObject(respJson);
	}

}
