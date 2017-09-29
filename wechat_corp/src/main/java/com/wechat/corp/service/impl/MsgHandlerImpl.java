package com.wechat.corp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import com.wechat.corp.bean.NewsItem;
import com.wechat.corp.callback.CallbackUtils;
import com.wechat.corp.service.interfaces.MsgHandler;


@Service
public class MsgHandlerImpl implements MsgHandler {
	private transient static final Logger logger = LoggerFactory.getLogger(MsgHandlerImpl.class);

	public String text(Element root, String content) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		if(StringUtils.isBlank(content)){
			content = CallbackUtils.getFirstTextContent(root, "Content");
		}
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		logger.debug("ToUserName : {}" , toUserName);
		logger.debug("FromUserName : {}" , fromUserName);
		logger.debug("CreateTime : {}" , createTime);
		logger.debug("MsgType : {}" , msgType);
		logger.debug("Content : {}" , content);
		logger.debug("MsgId : {}" , msgId);
		logger.debug("AgentID : {}" , agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, content);
		return retString;
	}
	
	public String news(Element root, String content, List<NewsItem> newsItems) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		if(StringUtils.isBlank(content)){
			content = CallbackUtils.getFirstTextContent(root, "Content");
		}
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		logger.debug("ToUserName : {}" , toUserName);
		logger.debug("FromUserName : {}" , fromUserName);
		logger.debug("CreateTime : {}" , createTime);
		logger.debug("MsgType : {}" , msgType);
		logger.debug("Content : {}" , content);
		logger.debug("MsgId : {}" , msgId);
		logger.debug("AgentID : {}" , agentID);
		String retString = CallbackUtils.getNewsRespString(toUserName, fromUserName, createTime, String.valueOf(newsItems.size()), newsItems);
		return retString;
	}

	public String image(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String picUrl = CallbackUtils.getFirstTextContent(root, "PicUrl");
		String mediaId = CallbackUtils.getFirstTextContent(root, "MediaId");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		logger.debug("ToUserName : {}" , toUserName);
		logger.debug("FromUserName : {}" , fromUserName);
		logger.debug("CreateTime : {}" , createTime);
		logger.debug("MsgType : {}" , msgType);
		logger.debug("PicUrl : {}" , picUrl);
		logger.debug("MediaId : {}" , mediaId);
		logger.debug("MsgId : {}" , msgId);
		logger.debug("AgentID : {}" , agentID);
		String retString = CallbackUtils.getImageRespString(fromUserName, toUserName, createTime, mediaId);
		return retString;
	}

	public String voice(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String mediaId = CallbackUtils.getFirstTextContent(root, "MediaId");
		String format = CallbackUtils.getFirstTextContent(root, "Format");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		logger.debug("ToUserName : {}" , toUserName);
		logger.debug("FromUserName : {}" , fromUserName);
		logger.debug("CreateTime : {}" , createTime);
		logger.debug("MsgType : {}" , msgType);
		logger.debug("Format : {}" , format);
		logger.debug("MediaId : {}" , mediaId);
		logger.debug("MsgId : {}" , msgId);
		logger.debug("AgentID : {}" , agentID);
		String retString = CallbackUtils.getVoiceRespString(fromUserName, toUserName, createTime, mediaId);
		return retString;
	}

	public String video(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String mediaId = CallbackUtils.getFirstTextContent(root, "MediaId");
		String thumbMediaId = CallbackUtils.getFirstTextContent(root, "ThumbMediaId");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		logger.debug("ToUserName : {}" , toUserName);
		logger.debug("FromUserName : {}" , fromUserName);
		logger.debug("CreateTime : {}" , createTime);
		logger.debug("MsgType : {}" , msgType);
		logger.debug("ThumbMediaId : {}" , thumbMediaId);
		logger.debug("MediaId : {}" , mediaId);
		logger.debug("MsgId : {}" , msgId);
		logger.debug("AgentID : {}" , agentID);
		String retString = CallbackUtils.getVideoRespString(fromUserName, toUserName, createTime, thumbMediaId, "test",
				"这是一个测试！");
		return retString;
	}

	public String shortvideo(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String mediaId = CallbackUtils.getFirstTextContent(root, "MediaId");
		String thumbMediaId = CallbackUtils.getFirstTextContent(root, "ThumbMediaId");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		logger.debug("ToUserName : {}" , toUserName);
		logger.debug("FromUserName : {}" , fromUserName);
		logger.debug("CreateTime : {}" , createTime);
		logger.debug("MsgType : {}" , msgType);
		logger.debug("ThumbMediaId : {}" , thumbMediaId);
		logger.debug("MediaId : {}" , mediaId);
		logger.debug("MsgId : {}" , msgId);
		logger.debug("AgentID : {}" , agentID);
		String retString = CallbackUtils.getVideoRespString(fromUserName, toUserName, createTime, thumbMediaId, "test",
				"这是一个测试！");
		return retString;
	}

	public String location(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String location_X = CallbackUtils.getFirstTextContent(root, "Location_X");
		String location_Y = CallbackUtils.getFirstTextContent(root, "Location_Y");
		String scale = CallbackUtils.getFirstTextContent(root, "Scale");
		String label = CallbackUtils.getFirstTextContent(root, "Label");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		logger.debug("ToUserName : {}" , toUserName);
		logger.debug("FromUserName : {}" , fromUserName);
		logger.debug("CreateTime : {}" , createTime);
		logger.debug("MsgType : {}" , msgType);
		logger.debug("Location_X : {}" , location_X);
		logger.debug("Location_Y : {}" , location_Y);
		logger.debug("Scale : {}" , scale);
		logger.debug("Label : {}" , label);
		logger.debug("MsgId : {}" , msgId);
		logger.debug("AgentID : {}" , agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, "Label : " + label
				+ ", Scale : " + scale + ", Location_X : " + location_X + ", Location_Y : " + location_Y);
		return retString;
	}

	public String link(Element root) {
		String toUserName = CallbackUtils.getFirstTextContent(root, "ToUserName");
		String fromUserName = CallbackUtils.getFirstTextContent(root, "FromUserName");
		String createTime = CallbackUtils.getFirstTextContent(root, "CreateTime");
		String msgType = CallbackUtils.getFirstTextContent(root, "MsgType");
		String title = CallbackUtils.getFirstTextContent(root, "Title");
		String description = CallbackUtils.getFirstTextContent(root, "Description");
		String picUrl = CallbackUtils.getFirstTextContent(root, "PicUrl");
		String msgId = CallbackUtils.getFirstTextContent(root, "MsgId");
		String agentID = CallbackUtils.getFirstTextContent(root, "AgentID");
		logger.debug("ToUserName : {}" , toUserName);
		logger.debug("FromUserName : {}" , fromUserName);
		logger.debug("CreateTime : {}" , createTime);
		logger.debug("MsgType : {}" , msgType);
		logger.debug("Title : {}" , title);
		logger.debug("Description : {}" , description);
		logger.debug("PicUrl : {}" , picUrl);
		logger.debug("MsgId : {}" , msgId);
		logger.debug("AgentID : {}" , agentID);
		String retString = CallbackUtils.getTextRespString(fromUserName, toUserName, createTime, "Title : " + title
				+ ", Description : " + description + ", PicUrl : " + picUrl);
		return retString;
	}

}
