package com.wechat.corp.connect;

import com.wechat.corp.bean.WechatException;

public interface WechatClientSuite {

	public String getSuiteAccessToken() throws WechatException;

	public String getSuiteAccessToken(boolean newOne) throws WechatException;

	public String get(String uri) throws WechatException;

	public String post(String uri, String msg) throws WechatException;

}
