/*
 * Company：      Asiainfo Technology Co., Ltd.
 * 
 * @author:   Liulh   
 * 
 * @Createdate:2017年9月8日 下午3:31:17  
 *
 * Copyright: Copyright(C) 2016,2999  All rights Reserved, Designed By Asiainfo 
 * License   
 * The original version of this source code and documentation is copyrighted
 * and owned by Asiainfo Technology Co., Ltd., a wholly-owned subsidiary of Asiainfo. 
 * These materials are provided under terms of a License Agreement Asiainfo. 
 * This notice and attribution to Asiainfo  may not be removed.
 * Asiainfo is a registered trademark of Asiainfo Technology Co., Ltd. 
 */
package com.wechat.corp.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.wechat.corp.aes.AesException;
import com.wechat.corp.aes.WXBizMsgCrypt;
import com.wechat.corp.callback.Entrance;
import com.wechat.corp.cgi.User;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;
import com.wechat.corp.service.interfaces.EventHandler;
import com.wechat.corp.service.interfaces.MsgHandler;

import net.sf.json.JSONObject;


/**
 * @Description
 *
 * @Liulh 创建时间：2017年9月8日
 */
@Controller
@RequestMapping("/testcontroller")
public class TestController {
	private transient static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	private static WXBizMsgCrypt wxcpt;
	private final MsgHandler msgHandler;
	private final EventHandler eventHandler;
	
	@Autowired
	public TestController(MsgHandler msgHandler, EventHandler eventHandler) {
		super();
		this.msgHandler = msgHandler;
		this.eventHandler = eventHandler;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, AesException {
		wxcpt = new WXBizMsgCrypt(Constants.TEST_STOKEN, Constants.TEST_SENCODINGAESKEY, Constants.CORP_ID);
		Entrance entrance = new Entrance(wxcpt, msgHandler, eventHandler);
		entrance.doGet(request, response);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void oprate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, AesException, ParserConfigurationException, SAXException {
		wxcpt = new WXBizMsgCrypt(Constants.TEST_STOKEN, Constants.TEST_SENCODINGAESKEY, Constants.CORP_ID);
		Entrance entrance = new Entrance(wxcpt, msgHandler, eventHandler);
		entrance.doPost(request, response);
	}
	
	/**
	 * 身份验证，网页授权获取成员详细信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getuserinfo", method = RequestMethod.GET )
	public @ResponseBody String testOAuth(HttpServletRequest request, HttpServletResponse response) throws Exception{
		WechatClientHCE wc = new WechatClientHCE(Constants.CORP_ID, Constants.TEST_SECRET);
		HttpClient httpClient = HttpClientFactory.createHttpClient();
		wc.setHttpClient(httpClient);
		//获取code
		String code = request.getParameter("code");
		logger.info("授权 authcode : {}",code);
		JSONObject jsonObject;
		//根据code获取成员基础信息
		jsonObject = User.getuserinfo(code, wc);
		logger.info("授权返回信息： {}", jsonObject);
		if (jsonObject.containsKey("UserId")) {
			JSONObject getDetailJson = new JSONObject();
			getDetailJson.put("user_ticket", jsonObject.getString("user_ticket"));
			//根据user_ticket获取成员详细信息
			String userDetailStr = User.getUserDetail(getDetailJson, wc).toString();
			userDetailStr = new String(userDetailStr.getBytes("ISO-8859-1"),"UTF-8");
			logger.info("获取成员详细信息：{}", userDetailStr);
			return formatHtml(JSONObject.fromObject(userDetailStr));
		} else {
			return formatHtml(jsonObject);
		}
	}
	
	public static String formatHtml(JSONObject object){
		StringBuilder html = new StringBuilder();
		
		@SuppressWarnings("unchecked")
		Set<String> set = object.keySet();
		html.append("<font size='10'>");
		for (String key : set) {
			if ("userid".equals(key))
				html.append("成员UserID—");
			if ("name".equals(key))
				html.append("成员姓名—");
			if ("department".equals(key))
				html.append("成员所属部门—");
			if ("position".equals(key))
				html.append("职位信息—");
			if ("mobile".equals(key))
				html.append("成员手机号—");
			if ("gender".equals(key))
				html.append("性别—");
			if ("email".equals(key))
				html.append("成员邮箱—");
			if ("avatar".equals(key))
				html.append("头像url—");
			html.append(key).append(":").append(object.get(key)).append("<br>");
		}
		html.append("</font>");
		return html.toString();
	}
}


