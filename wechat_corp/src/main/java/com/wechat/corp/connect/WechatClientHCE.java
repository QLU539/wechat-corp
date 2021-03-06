package com.wechat.corp.connect;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;

import net.sf.json.JSONObject;

public class WechatClientHCE extends WechatClientHC {

	protected String corpId = Constants.CORP_ID;

	protected String corpSecret;

	protected Map<String, String> accessToken;

	protected Map<String, Long> expires;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}
	
	/**   
	 * @Title:        WechatClientHCE   
	 * @Description:    
	 * @param:      
	 * @throws  
	 */
	public WechatClientHCE() {
		super();
	}

	/**   
	 * @Title:        WechatClientHCE   
	 * @Description:    
	 * @param:    @param corpId
	 * @param:    @param corpSecret  
	 * @throws  
	 */
	public WechatClientHCE(String corpId, String corpSecret) {
		super();
		this.corpId = corpId;
		this.corpSecret = corpSecret;
	}


	public String getAccessToken() throws WechatException {
		return getAccessToken(false);
	}

	/**
	 * 获取微信的accessToken，线程同步，有效期内不重新获取，有效期内返回相同的accessToken。
	 * 
	 * @return 微信的accessToken。
	 * @throws WechatException
	 *             可以抛出的异常。
	 */
	public synchronized String getAccessToken(boolean newOne) throws WechatException {
		long now = System.currentTimeMillis();
		if (!newOne) {
			//判断保存的失效时间是否为空
			if (expires != null) {
				//判断是否已有该secret对应应用的失效时间，并判断是否已过期。
				if (expires.containsKey(corpSecret) && now < expires.get(corpSecret)) {
					//判断是否已有该secret对应应用的accessToken，并判断存储的值是否为空
					if (accessToken.containsKey(corpSecret) && StringUtils.isNoneBlank(accessToken.get(corpSecret))) {
						//存在则直接返回。
						return accessToken.get(corpSecret);
					}
				}
			} else {
				accessToken = new HashMap<>();
				expires = new HashMap<>();
			}
		}
		//拼装获取accessToken的tokenUrl
		String getTokenUrl = MessageFormat.format(Constants.URL_GETTOKEN, corpId, corpSecret);
		//发送获取accessToken请求
		String respJson = get(getTokenUrl);
		JSONObject jsonObject = JSONObject.fromObject(respJson);
		if (jsonObject.containsKey(Constants.RESP_ACCESS_TOKEN)) {
			//更新对应应用的accessToken和失效时间
			accessToken.put(corpSecret, jsonObject.getString(Constants.RESP_ACCESS_TOKEN));
			String expiresIn = jsonObject.getString(Constants.RESP_EXPIRES_IN);
			expires.put(corpSecret, now + Integer.parseInt(expiresIn) * 1000);
			return accessToken.get(corpSecret);
		}
		throw new WechatException(respJson);
	}

}
