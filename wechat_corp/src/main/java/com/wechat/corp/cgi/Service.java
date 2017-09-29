package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClientSuite;

import net.sf.json.JSONObject;

public abstract class Service {

	public static JSONObject getPreAuthCode(JSONObject msgJson, WechatClientSuite wcs) throws WechatException {
		String suiteAccessToken = wcs.getSuiteAccessToken();
		String getPreAuthCodeUrl = MessageFormat.format(Constants.URL_SERVICE_GETPREAUTHCODE, suiteAccessToken);
		String msg = msgJson.toString();
		String respJson = wcs.post(getPreAuthCodeUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	public static JSONObject setSessionInfo(JSONObject msgJson, WechatClientSuite wcs) throws WechatException {
		String suiteAccessToken = wcs.getSuiteAccessToken();
		String setSessionInfoUrl = MessageFormat.format(Constants.URL_SERVICE_SETSESSIONINFO, suiteAccessToken);
		String msg = msgJson.toString();
		String respJson = wcs.post(setSessionInfoUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	public static JSONObject getPermanentCode(JSONObject msgJson, WechatClientSuite wcs) throws WechatException {
		String suiteAccessToken = wcs.getSuiteAccessToken();
		String getPermanentCodeUrl = MessageFormat.format(Constants.URL_SERVICE_GETPERMANENTCODE, suiteAccessToken);
		String msg = msgJson.toString();
		String respJson = wcs.post(getPermanentCodeUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	public static JSONObject getAuthInfo(JSONObject msgJson, WechatClientSuite wcs) throws WechatException {
		String suiteAccessToken = wcs.getSuiteAccessToken();
		String getAuthInfoUrl = MessageFormat.format(Constants.URL_SERVICE_GETAUTHINFO, suiteAccessToken);
		String msg = msgJson.toString();
		String respJson = wcs.post(getAuthInfoUrl, msg);
		return JSONObject.fromObject(respJson);
	}

}
