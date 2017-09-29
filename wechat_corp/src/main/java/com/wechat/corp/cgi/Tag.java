package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;


public abstract class Tag {

	/**
	 * 创建标签
	 */
	public static JSONObject create(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String createUrl = MessageFormat.format(Constants.URL_TAG_CREATE, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(createUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 修改标签
	 */
	public static JSONObject update(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String updateUrl = MessageFormat.format(Constants.URL_TAG_UPDATE, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(updateUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 删除标签
	 */
	public static JSONObject delete(String tagid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deleteUrl = MessageFormat.format(Constants.URL_TAG_DELETE, accessToken, tagid);
		String respJson = wc.get(deleteUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取标签成员
	 */
	public static JSONObject get(String tagid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_TAG_GET, accessToken, tagid);
		String respJson = wc.get(getUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 增加标签成员
	 */
	public static JSONObject addtagusers(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String addtagusersUrl = MessageFormat.format(Constants.URL_TAG_ADDTAGUSERS, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(addtagusersUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 删除标签成员
	 */
	public static JSONObject deltagusers(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deltagusersUrl = MessageFormat.format(Constants.URL_TAG_DELTAGUSERS, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(deltagusersUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取标签列表
	 */
	public static JSONObject list(WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String listUrl = MessageFormat.format(Constants.URL_TAG_LIST, accessToken);
		String respJson = wc.get(listUrl);
		return JSONObject.fromObject(respJson);
	}

}
