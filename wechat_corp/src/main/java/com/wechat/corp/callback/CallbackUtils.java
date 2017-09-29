package com.wechat.corp.callback;

import java.text.MessageFormat;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wechat.corp.bean.NewsItem;
import com.wechat.corp.common.Constants;



public abstract class CallbackUtils {

	public static String getFirstTextContent(Element root, String tagName) {
		NodeList nodeList = root.getElementsByTagName(tagName);
		if (nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		return null;
	}

	public static String getFirstTextContent(Element root, String tagName0, String tagName1) {
		NodeList nodeList = root.getElementsByTagName(tagName0);
		if (nodeList.getLength() > 0) {
			Node node = nodeList.item(0);
			Document document = node.getOwnerDocument();
			Element element = document.getDocumentElement();
			return getFirstTextContent(element, tagName1);
		}
		return null;
	}

	public static String getTextRespString(String toUserName, String fromUserName, String createTime, String content) {
		String textFormatString = "<xml>\n" + "<ToUserName><![CDATA[{0}]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>\n" + "<CreateTime>{2}</CreateTime>\n"
				+ "<MsgType><![CDATA[{3}]]></MsgType>\n" + "<Content><![CDATA[{4}]]></Content>\n" + "</xml>";
		return MessageFormat.format(textFormatString, toUserName, fromUserName, createTime, Constants.MSGTYPE_TEXT,
				content);
	}

	public static String getImageRespString(String toUserName, String fromUserName, String createTime, String mediaId) {
		String textFormatString = "<xml>\n" + "<ToUserName><![CDATA[{0}]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>\n" + "<CreateTime>{2}</CreateTime>\n"
				+ "<MsgType><![CDATA[{3}]]></MsgType>\n" + "<Image>\n" + "<MediaId><![CDATA[{4}]]></MediaId>\n"
				+ "</Image>\n" + "</xml>";
		return MessageFormat.format(textFormatString, toUserName, fromUserName, createTime, Constants.MSGTYPE_IMAGE,
				mediaId);
	}

	public static String getVoiceRespString(String toUserName, String fromUserName, String createTime, String mediaId) {
		String textFormatString = "<xml>\n" + "<ToUserName><![CDATA[{0}]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>\n" + "<CreateTime>{2}</CreateTime>\n"
				+ "<MsgType><![CDATA[{3}]]></MsgType>\n" + "<Voice>\n" + "<MediaId><![CDATA[{4}]]></MediaId>\n"
				+ "</Voice>\n" + "</xml>";
		return MessageFormat.format(textFormatString, toUserName, fromUserName, createTime, Constants.MSGTYPE_VOICE,
				mediaId);
	}

	public static String getVideoRespString(String toUserName, String fromUserName, String createTime, String mediaId,
			String title, String description) {
		String textFormatString = "<xml>\n" + "<ToUserName><![CDATA[{0}]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>\n" + "<CreateTime>{2}</CreateTime>\n"
				+ "<MsgType><![CDATA[{3}]]></MsgType>\n" + "<Video>\n" + "<MediaId><![CDATA[{4}]]></MediaId>\n"
				+ "<Title><![CDATA[{5}]]></Title>\n" + "<Description><![CDATA[{6}]]></Description>\n" + "</Video>\n"
				+ "</xml>";
		return MessageFormat.format(textFormatString, toUserName, fromUserName, createTime, Constants.MSGTYPE_VIDEO,
				mediaId, title, description);
	}

	public static String getNewsRespString(String toUserName, String fromUserName, String createTime,
			String articleCount, List<NewsItem> newsItems) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>\n");
		sb.append("<ToUserName><![CDATA[");
		sb.append(toUserName);
		sb.append("]]></ToUserName>\n");
		sb.append("<FromUserName><![CDATA[");
		sb.append(fromUserName);
		sb.append("]]></FromUserName>\n");
		sb.append("<CreateTime>");
		sb.append(createTime);
		sb.append("</CreateTime>\n");
		sb.append("<MsgType><![CDATA[");
		sb.append(Constants.MSGTYPE_NEWS);
		sb.append("]]></MsgType>\n");
		sb.append("<ArticleCount>");
		sb.append(articleCount);
		sb.append("</ArticleCount>\n");
		sb.append("<Articles>\n");
		for (NewsItem newsItem : newsItems) {
			sb.append("<item>\n");
			sb.append("<Title><![CDATA[");
			sb.append(newsItem.getTitle());
			sb.append("]]></Title>\n");
			sb.append("<Description><![CDATA[");
			sb.append(newsItem.getDescription());
			sb.append("]]></Description>\n");
			sb.append("<PicUrl><![CDATA[");
			sb.append(newsItem.getPicurl());
			sb.append("]]></PicUrl>\n");
			sb.append("<Url><![CDATA[");
			sb.append(newsItem.getUrl());
			sb.append("]]></Url>\n");
			sb.append("</item>\n");
		}
		sb.append("</Articles>\n");
		sb.append("</xml>");
		String ret = sb.toString();
		return ret;
	}

}
