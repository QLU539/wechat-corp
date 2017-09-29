package com.wechat.corp.cgi;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;

public abstract class Batch {

	/**
	 * 邀请成员关注
	 */
	public static JSONObject inviteUser(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String inviteUserUrl = MessageFormat.format(Constants.URL_BATCH_INVITEUSER, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(inviteUserUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 增量更新成员
	 */
	public static JSONObject syncUser(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String syncUserUrl = MessageFormat.format(Constants.URL_BATCH_SYNCUSER, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(syncUserUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 全量覆盖成员
	 */
	public static JSONObject replaceUser(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String replaceUserUrl = MessageFormat.format(Constants.URL_BATCH_REPLACEUSER, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(replaceUserUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 全量覆盖部门
	 */
	public static JSONObject replaceParty(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String replacePartyUrl = MessageFormat.format(Constants.URL_BATCH_REPLACEPARTY, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(replacePartyUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取异步任务结果
	 */
	public static JSONObject getResult(String jobId, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getResultUrl = MessageFormat.format(Constants.URL_BATCH_GETRESULT, accessToken, jobId);
		String respJson = wc.get(getResultUrl);
		return JSONObject.fromObject(respJson);
	}

}
