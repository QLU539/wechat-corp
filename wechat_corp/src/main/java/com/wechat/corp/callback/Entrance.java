package com.wechat.corp.callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.wechat.corp.aes.AesException;
import com.wechat.corp.aes.WXBizMsgCrypt;
import com.wechat.corp.common.Constants;
import com.wechat.corp.service.interfaces.EventHandler;
import com.wechat.corp.service.interfaces.MsgHandler;



public class Entrance {

	private WXBizMsgCrypt wxcpt;

	private MsgHandler msgHandler;

	private EventHandler eventHandler;

	public void setWXBizMsgCrypt(WXBizMsgCrypt wxcpt) {
		this.wxcpt = wxcpt;
	}

	public WXBizMsgCrypt getWXBizMsgCrypt() {
		return wxcpt;
	}

	public MsgHandler getMsgHandler() {
		return msgHandler;
	}

	public void setMsgHandler(MsgHandler msgHandler) {
		this.msgHandler = msgHandler;
	}

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	public Entrance() {
	}

	public Entrance(WXBizMsgCrypt wxcpt, MsgHandler msgHandler, EventHandler eventHandler) {
		this.wxcpt = wxcpt;
		this.msgHandler = msgHandler;
		this.eventHandler = eventHandler;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			AesException {
		/*
		 * 接收到该请求时，企业应
		 * 1.解析出Get请求的参数，包括消息体签名(msg_signature)，时间戳(timestamp)，随机数字串(
		 * nonce)以及公众平台推送过来的随机加密字符串(echostr), 这一步注意作URL解码。 2.验证消息体签名的正确性。
		 * 3.解密出echostr原文，将原文当作Get请求的response，返回给公众平台
		 * 第2，3步可以用公众平台提供的库函数VerifyURL来实现。
		 */
		String sVerifyMsgSig = request.getParameter("msg_signature");
		String sVerifyTimeStamp = request.getParameter("timestamp");
		String sVerifyNonce = request.getParameter("nonce");
		String sVerifyEchoStr = request.getParameter("echostr");
		// 需要返回的明文
		String sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
		PrintWriter out = response.getWriter();
		out.print(sEchoStr);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			AesException, ParserConfigurationException, SAXException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		/*
		 * 企业收到post请求之后应该
		 * 1.解析出url上的参数，包括消息体签名(msg_signature)，时间戳(timestamp)以及随机数字串(nonce)。
		 * 2.验证消息体签名的正确性。
		 * 3.将post请求的数据进行xml解析，并将<Encrypt>标签的内容进行解密，解密出来的明文即是用户回复消息的明文
		 * ，明文格式请参考官方文档 第2，3步可以用公众平台提供的库函数DecryptMsg来实现。
		 */
		String sReqMsgSig = request.getParameter("msg_signature");
		String sReqTimeStamp = request.getParameter("timestamp");
		String sReqNonce = request.getParameter("nonce");
		StringBuffer data = new StringBuffer();
		String temp = null;
		BufferedReader buffer = request.getReader();
		while (true) {
			temp = buffer.readLine();
			if (temp == null || temp.trim().equals("")) {
				break;
			} else {
				data.append(temp);
			}
		}
		String sReqData = data.toString();
		String sMsg = wxcpt.DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, sReqData);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(sMsg);
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);

		Element root = document.getDocumentElement();
		NodeList msgTypes = root.getElementsByTagName("MsgType");
		if (msgTypes.getLength() > 0) {
			String msgType = msgTypes.item(0).getTextContent();
			String replyMsg = null;
			if (Constants.MSGTYPE_TEXT.equals(msgType)) {
				replyMsg = msgHandler.text(root, "");
			} else if (Constants.MSGTYPE_IMAGE.equals(msgType)) {
				replyMsg = msgHandler.image(root);
			} else if (Constants.MSGTYPE_VOICE.equals(msgType)) {
				replyMsg = msgHandler.voice(root);
			} else if (Constants.MSGTYPE_VIDEO.equals(msgType)) {
				replyMsg = msgHandler.video(root);
			} else if (Constants.MSGTYPE_SHORTVIDEO.equals(msgType)) {
				replyMsg = msgHandler.shortvideo(root);
			} else if (Constants.MSGTYPE_LOCATION.equals(msgType)) {
				replyMsg = msgHandler.location(root);
			} else if (Constants.MSGTYPE_LINK.equals(msgType)) {
				replyMsg = msgHandler.link(root);
			} else if (Constants.MSGTYPE_EVENT.equals(msgType)) {
				NodeList events = root.getElementsByTagName("Event");
				if (events.getLength() == 1) {
					String event = events.item(0).getTextContent();
					if (Constants.EVENT_SUBSCRIBE.equals(event)) {
						replyMsg = eventHandler.subscribe(root);
					} else if (Constants.EVENT_LOCATION.equals(event)) {
						replyMsg = eventHandler.location(root);
					} else if (Constants.EVENT_CLICK.equals(event)) {
						replyMsg = eventHandler.click(root);
					} else if (Constants.EVENT_VIEW.equals(event)) {
						replyMsg = eventHandler.view(root);
					} else if (Constants.EVENT_SCANCODE_PUSH.equals(event)) {
						replyMsg = eventHandler.scancodePush(root);
					} else if (Constants.EVENT_SCANCODE_WAITMSG.equals(event)) {
						replyMsg = eventHandler.scancodeWaitmsg(root);
					} else if (Constants.EVENT_PIC_SYSPHOTO.equals(event)) {
						replyMsg = eventHandler.picSysphoto(root);
					} else if (Constants.EVENT_PIC_PHOTO_OR_ALBUM.equals(event)) {
						replyMsg = eventHandler.picPhotoOrAlbum(root);
					} else if (Constants.EVENT_PIC_WEIXIN.equals(event)) {
						replyMsg = eventHandler.picWeixin(root);
					} else if (Constants.EVENT_LOCATION_SELECT.equals(event)) {
						replyMsg = eventHandler.locationSelect(root);
					} else if (Constants.EVENT_ENTER_AGENT.equals(event)) {
						replyMsg = eventHandler.enterAgent(root);
					} else if (Constants.EVENT_BATCH_JOB_RESULT.equals(event)) {
						replyMsg = eventHandler.batchJobResult(root);
					}
				}
			}
			if (replyMsg != null) {
				String sEncryptMsg = wxcpt.EncryptMsg(replyMsg, sReqTimeStamp, sReqNonce);
				retMessage(sEncryptMsg, response);
			}
		}
	}

	/**
	 *  输出信息
	 * @param sEncryptMsg
	 * @param response
	 */
	public static void retMessage (String sEncryptMsg, HttpServletResponse response) throws IOException {
			PrintWriter out = response.getWriter();
			out.write(sEncryptMsg);
			out.flush();
			out.close();
	}

}
