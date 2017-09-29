package com.wechat.corp.connect;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;

import net.sf.json.JSONObject;



public class WechatClientSuiteHC implements WechatClientSuite {

	protected String suiteId;

	protected String suiteSecret;

	protected String suiteTicket;

	protected String suiteAccessToken;

	protected long suiteExpires;

	protected HttpClient httpClient;

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

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
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

	public String get(String uri) throws WechatException {
		try {
			HttpGet get = new HttpGet(uri);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpClient.execute(get, responseHandler);
		} catch (ClientProtocolException e) {
			throw new WechatException(e);
		} catch (IOException e) {
			throw new WechatException(e);
		}
	}

	public String post(String uri, String msg) throws WechatException {
		try {
			HttpPost post = new HttpPost(uri);
			StringEntity entity = new StringEntity(msg, "UTF-8");
			post.setEntity(entity);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpClient.execute(post, responseHandler);
		} catch (UnsupportedEncodingException e) {
			throw new WechatException(e);
		} catch (ClientProtocolException e) {
			throw new WechatException(e);
		} catch (IOException e) {
			throw new WechatException(e);
		}
	}

}
