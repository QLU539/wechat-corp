package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;

public abstract class Department {

	/**
	 * 创建部门
	 */
	public static JSONObject create(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String createUrl = MessageFormat.format(Constants.URL_DEPARTMENT_CREATE, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(createUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 修改部门
	 */
	public static JSONObject update(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String updateUrl = MessageFormat.format(Constants.URL_DEPARTMENT_UPDATE, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(updateUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 删除部门
	 */
	public static JSONObject delete(String id, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deleteUrl = MessageFormat.format(Constants.URL_DEPARTMENT_DELETE, accessToken, id);
		String respJson = wc.get(deleteUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取部门
	 */
	public static JSONObject list(String id, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String listUrl = MessageFormat.format(Constants.URL_DEPARTMENT_LIST, accessToken, id);
		String respJson = wc.get(listUrl);
		return JSONObject.fromObject(respJson);
	}

}
