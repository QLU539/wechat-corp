package com.wechat.corp.service.impl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.callback.CallbackUtils;
import com.wechat.corp.cgi.CgiUtils;
import com.wechat.corp.cgi.Message;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;
import com.wechat.corp.service.interfaces.EventHandler;

import net.sf.json.JSONObject;

@Service
public class EventHandlerImpl implements EventHandler {

	public String subscribe(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		System.out.println("ToUserName : " + toUserName);
		System.out.println("FromUserName : " + fromUserName);
		System.out.println("CreateTime : " + createTime);
		System.out.println("MsgType : " + msgType);
		System.out.println("Event : " + event);
		System.out.println("AgentID : " + agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, fromUserName);
		return retString;
	}

	public String location(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String latitude = CallbackUtils.getFirstTextContent(root, "Latitude");
		String longitude = CallbackUtils.getFirstTextContent(root, "Longitude");
		String precision = CallbackUtils.getFirstTextContent(root, "Precision");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		System.out.println("ToUserName : " + toUserName);
		System.out.println("FromUserName : " + fromUserName);
		System.out.println("CreateTime : " + createTime);
		System.out.println("MsgType : " + msgType);
		System.out.println("Event : " + event);
		System.out.println("Latitude : " + latitude);
		System.out.println("Longitude : " + longitude);
		System.out.println("Precision : " + precision);
		System.out.println("AgentID : " + agentID);
		try {
			WechatClientHCE wc = new WechatClientHCE("","");
			HttpClient httpClient = HttpClientFactory.createHttpClient();
			wc.setHttpClient(httpClient);
			System.out.println("发送text消息。");
			JSONObject json0 = CgiUtils.getMessageText(fromUserName, null, null, "0", "Latitude : " + latitude
					+ ", Longitude : " + longitude + ", Precision : " + precision, 0);
			System.out.println("text消息：" + Message.send(json0, wc, wc.getAccessToken()));
		} catch (WechatException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String click(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		System.out.println("ToUserName : " + toUserName);
		System.out.println("FromUserName : " + fromUserName);
		System.out.println("CreateTime : " + createTime);
		System.out.println("MsgType : " + msgType);
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		System.out.println("AgentID : " + agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, "Event : " + event
				+ ", EventKey" + eventKey);
		System.out.println(retString);
		return retString;
	}

	public String view(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		System.out.println("ToUserName : " + toUserName);
		System.out.println("FromUserName : " + fromUserName);
		System.out.println("CreateTime : " + createTime);
		System.out.println("MsgType : " + msgType);
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		System.out.println("AgentID : " + agentID);
		return null;
	}

	public String scancodePush(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		String scanType = CallbackUtils.getFirstTextContent(root, "ScanCodeInfo", "ScanType");
		String scanResult = CallbackUtils.getFirstTextContent(root, "ScanCodeInfo", "scanResult");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		System.out.println("ToUserName : " + toUserName);
		System.out.println("FromUserName : " + fromUserName);
		System.out.println("CreateTime : " + createTime);
		System.out.println("MsgType : " + msgType);
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		System.out.println("ScanType : " + scanType);
		System.out.println("ScanResult : " + scanResult);
		System.out.println("AgentID : " + agentID);
		return null;
	}

	public String scancodeWaitmsg(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		String scanType = CallbackUtils.getFirstTextContent(root, "ScanCodeInfo", "ScanType");
		String scanResult = CallbackUtils.getFirstTextContent(root, "ScanCodeInfo", "scanResult");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		System.out.println("ToUserName : " + toUserName);
		System.out.println("FromUserName : " + fromUserName);
		System.out.println("CreateTime : " + createTime);
		System.out.println("MsgType : " + msgType);
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		System.out.println("ScanType : " + scanType);
		System.out.println("ScanResult : " + scanResult);
		System.out.println("AgentID : " + agentID);
		return null;
	}

	public String picSysphoto(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		return null;
	}

	public String picPhotoOrAlbum(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		return null;
	}

	public String picWeixin(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		return null;
	}

	public String locationSelect(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		return null;
	}

	public String enterAgent(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String eventKey = CallbackUtils.getFirstTextContent(root, "EventKey");
		System.out.println("Event : " + event);
		System.out.println("EventKey : " + eventKey);
		return null;
	}

	public String batchJobResult(Element root) {
		String event = CallbackUtils.getFirstTextContent(root, "Event");
		String errCode = CallbackUtils.getFirstTextContent(root, "ErrCode");
		System.out.println("Event : " + event);
		System.out.println("ErrCode : " + errCode);
		return null;
	}

}
