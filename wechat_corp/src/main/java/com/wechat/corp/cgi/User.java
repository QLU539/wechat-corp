package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;

public abstract class User {

	/**
	 * 企业在开启二次验证时，必须填写企业二次验证页面的url，此url的域名必须设置为企业小助手的可信域名。当成员绑定通讯录中的帐号后，
	 * 会收到一条图文消息，引导成员到企业的验证页面验证身份。在跳转到企业的验证页面时，会带上如下参数：code=CODE&state=STATE，
	 * 企业可以调用oauth2接口，根据code获取成员的userid。 企业在成员验证成功后，调用如下接口即可让成员关注成功。
	 */
	public static JSONObject authsucc(String userid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String authsuccUrl = MessageFormat.format(Constants.URL_USER_AUTHSUCC, accessToken, userid);
		String respJson = wc.get(authsuccUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 创建成员
	 */
	public static JSONObject create(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String createUrl = MessageFormat.format(Constants.URL_USER_CREATE, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(createUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 修改成员
	 */
	public static JSONObject update(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String updateUrl = MessageFormat.format(Constants.URL_USER_UPDATE, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(updateUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 删除成员
	 */
	public static JSONObject delete(String id, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String deleteUrl = MessageFormat.format(Constants.URL_USER_DELETE, accessToken, id);
		String respJson = wc.get(deleteUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 批量删除成员
	 */
	public static JSONObject batchDelete(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String batchDeleteUrl = MessageFormat.format(Constants.URL_USER_BATCHDELETE, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(batchDeleteUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取成员信息
	 */
	public static JSONObject get(String id, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_USER_GET, accessToken, id);
		String respJson = wc.get(getUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取部门成员
	 */
	public static JSONObject simpleList(String departmentId, String fetchChild, String status, WechatClient wc)
			throws WechatException {
		String accessToken = wc.getAccessToken();
		String simpleListUrl = MessageFormat.format(Constants.URL_USER_SIMPLELIST, accessToken, departmentId,
				fetchChild, status);
		String respJson = wc.get(simpleListUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取部门成员详情
	 */
	public static JSONObject list(String departmentId, String fetchChild, String status, WechatClient wc)
			throws WechatException {
		String accessToken = wc.getAccessToken();
		String listUrl = MessageFormat.format(Constants.URL_USER_LIST, accessToken, departmentId, fetchChild, status);
		String respJson = wc.get(listUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 根据code获取成员信息
	 */
	public static JSONObject getuserinfo(String code, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getuserinfoUrl = MessageFormat.format(Constants.URL_USER_GETUSERINFO, accessToken, code);
		String respJson = wc.get(getuserinfoUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 根据user_ticket获取成员详细信息
	 */
	public static JSONObject getUserDetail(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String sendUrl = MessageFormat.format(Constants.URL_USER_DETAIL, accessToken);
		String respJson = wc.post(sendUrl, msgJson.toString());
		return JSONObject.fromObject(respJson);
	}
	
	/**
	 * userid转换成openid接口
	 */
	public static JSONObject convertToOpenid(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String convertToOpenidUrl = MessageFormat.format(Constants.URL_USER_CONVERTTOOPENID, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(convertToOpenidUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * openid转换成userid接口
	 */
	public static JSONObject convertToUserid(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String convertToUseridUrl = MessageFormat.format(Constants.URL_USER_CONVERTTOUSERID, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(convertToUseridUrl, msg);
		return JSONObject.fromObject(respJson);
	}

}
