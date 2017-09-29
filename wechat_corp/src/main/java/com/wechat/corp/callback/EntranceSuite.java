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
import com.wechat.corp.service.interfaces.InfoHandler;



public class EntranceSuite {

	private WXBizMsgCrypt wxcpt;

	private InfoHandler infoHandler;

	public WXBizMsgCrypt getWxcpt() {
		return wxcpt;
	}

	public void setWxcpt(WXBizMsgCrypt wxcpt) {
		this.wxcpt = wxcpt;
	}

	public InfoHandler getInfoHandler() {
		return infoHandler;
	}

	public void setInfoHandler(InfoHandler infoHandler) {
		this.infoHandler = infoHandler;
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
		NodeList infoTypes = root.getElementsByTagName("InfoType");
		if (infoTypes.getLength() > 0) {
			String infoType = infoTypes.item(0).getTextContent();
			String replyMsg = null;
			if (Constants.INFOTYPE_SUITE_TICKET.equals(infoType)) {
				replyMsg = infoHandler.suiteTicket(root);
			} else if (Constants.INFOTYPE_CHANGE_AUTH.equals(infoType)) {
				replyMsg = infoHandler.changeAuth(root);
			} else if (Constants.INFOTYPE_CANCEL_AUTH.equals(infoType)) {
				replyMsg = infoHandler.cancelAuth(root);
			}
			if (replyMsg != null) {
				String sEncryptMsg = wxcpt.EncryptMsg(replyMsg, sReqTimeStamp, sReqNonce);
				PrintWriter out = response.getWriter();
				out.write(sEncryptMsg);
				out.flush();
				out.close();
			}
		}
	}
}
