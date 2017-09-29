/*
 * Company：      Asiainfo Technology Co., Ltd.
 * 
 * @author:   Liulh   
 * 
 * @Createdate:2017年9月1日 下午2:26:02  
 *
 * Copyright: Copyright(C) 2016,2999  All rights Reserved, Designed By Asiainfo 
 * License   
 * The original version of this source code and documentation is copyrighted
 * and owned by Asiainfo Technology Co., Ltd., a wholly-owned subsidiary of Asiainfo. 
 * These materials are provided under terms of a License Agreement Asiainfo. 
 * This notice and attribution to Asiainfo  may not be removed.
 * Asiainfo is a registered trademark of Asiainfo Technology Co., Ltd. 
 */
package com.wechat.corp.test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.cgi.Agent;
import com.wechat.corp.cgi.CgiUtils;
import com.wechat.corp.cgi.Menu;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;

import net.sf.json.JSONObject;

public class AppManage {
	
	public static void main(String[] args) {
		try {
			WechatClientHCE wc = new WechatClientHCE(Constants.CORP_ID, Constants.ASSISTANT_SECRET);
			wc.setHttpClient(HttpClientFactory.createHttpClient());
			
			//获取企业应用
			JSONObject jsonObject1 = Agent.get(Constants.ASSISTANT_AGENTID, wc);
			System.out.println(jsonObject1);
			System.out.println();
			
			//获取企业应用菜单
			JSONObject jsonObject2 = Menu.get(Constants.ASSISTANT_AGENTID, wc);
			System.out.println(jsonObject2);
			System.out.println();
			
			//创建企业应用菜单
			wc.setCorpSecret(Constants.TEST_SECRET);
			JSONObject json2 = Menu.create(jsonObject2, Constants.TEST_AGENTID, wc);
			System.out.println(json2);
			System.out.println(Menu.get(Constants.TEST_AGENTID, wc));
			
			//设置企业应用
			JSONObject json1 = CgiUtils.getAgentSet(Constants.TEST_AGENTID, "0", "", "测试应用", "测试用", "", "", "");
					System.out.println(Agent.set(json1, wc));
			System.out.println();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (WechatException e) {
			e.printStackTrace();
		}
	}
}


