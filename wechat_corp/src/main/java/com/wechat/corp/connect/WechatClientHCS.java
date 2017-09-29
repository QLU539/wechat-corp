package com.wechat.corp.connect;

import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;

import net.sf.json.JSONObject;


public class WechatClientHCS extends WechatClientHC {

	protected String suiteId;

	protected String suiteSecret;

	protected String suiteTicket;

	protected String authCorpId;

	protected String permanentCode;

	protected String accessToken;

	protected String suiteAccessToken;

	protected long expires;

	protected long suiteExpires;

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public String getSuiteSecret() {
		return suiteSecret;
	}

	public void setSuiteSecret(String suiteSecret) {
		this.suiteSecret = suiteSecret;
	}

	public String getSuiteTicket() {
		return suiteTicket;
	}

	public void setSuiteTicket(String suiteTicket) {
		this.suiteTicket = suiteTicket;
	}

	public String getAuthCorpId() {
		return authCorpId;
	}

	public void setAuthCorpId(String authCorpId) {
		this.authCorpId = authCorpId;
	}

	public String getPermanentCode() {
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode) {
		this.permanentCode = permanentCode;
	}

	public String getSuiteAccessToken() throws WechatException {
		return getSuiteAccessToken(false);
	}

	public String getSuiteAccessToken(boolean newOne) throws WechatException {
		long now = System.currentTimeMillis();
		if (!newOne) {
			if (now < suiteExpires) {
				if (suiteAccessToken != null) {
					return suiteAccessToken;
				}
			}
		}
		String gettokenUrl = Constants.URL_GETSUITETOKEN;
		JSONObject json = new JSONObject();
		json.put("suite_id", suiteId);
		json.put("suite_secret", suiteSecret);
		json.put("suite_ticket", suiteTicket);
		String msg = json.toString();
		String respJson = post(gettokenUrl, msg);
		JSONObject jsonObject = JSONObject.fromObject(respJson);
		if (jsonObject.containsKey(Constants.RESP_SUITE_ACCESS_TOKEN)) {
			suiteAccessToken = jsonObject.getString(Constants.RESP_SUITE_ACCESS_TOKEN);
			String expiresIn = jsonObject.getString(Constants.RESP_EXPIRES_IN);
			suiteExpires = now + Integer.parseInt(expiresIn) * 1000;
			return suiteAccessToken;
		}
		throw new WechatException(respJson);
	}

	public String getAccessToken() throws WechatException {
		return getAccessToken(false);
	}

	public synchronized String getAccessToken(boolean newOne) throws WechatException {
		long now = System.currentTimeMillis();
		if (!newOne) {
			if (now < expires) {
				if (accessToken != null) {
					return accessToken;
				}
			}
		}
		String suiteAccessToken = getSuiteAccessToken(true);
		String gettokenUrl = MessageFormat.format(Constants.URL_GETCORPTOKEN, suiteAccessToken);
		JSONObject json = new JSONObject();
		json.put("suite_id", suiteId);
		json.put("auth_corpid", authCorpId);
		json.put("permanent_code", permanentCode);
		String msg = json.toString();
		String respJson = post(gettokenUrl, msg);
		JSONObject jsonObject = JSONObject.fromObject(respJson);
		if (jsonObject.containsKey(Constants.RESP_ACCESS_TOKEN)) {
			accessToken = jsonObject.getString(Constants.RESP_ACCESS_TOKEN);
			String expiresIn = jsonObject.getString(Constants.RESP_EXPIRES_IN);
			expires = now + Integer.parseInt(expiresIn) * 1000;
			return accessToken;
		}
		throw new WechatException(respJson);
	}

	public String getAssistantAccessToken() throws WechatException {
		return null;
	}

	public String getMessageAccessToken() throws WechatException {
		return null;
	}

	public String getReportAccessToken() throws WechatException {
		return null;
	}
	public String getTaskAccessToken() throws WechatException {
		return null;
	}

	public String getMemebersAccessToken() throws WechatException {
		return null;
	}

	public String getAccessToken(boolean newOne, String secret) throws WechatException {
		return null;
	}

}
