package com.wechat.corp.connect;

import java.io.InputStream;

import com.wechat.corp.bean.WechatException;



/**
 * 
 * @Description 回调式下载接口。
 *
 * @Liulh 创建时间：2017年6月1日
 */
public interface DownLoadHandler {

	/**
	 * 根据contentType和fileName保存输入流中的内容，可抛出WechatException异常。
	 * 
	 * @param contentType
	 *            Http头中的Content-Type。
	 * @param fileName
	 *            Http头中的Content-disposition中的filename。
	 * @param is
	 *            下载的输入流。
	 * @throws WechatException
	 *             可以抛出的异常。
	 */
	public void handler(String contentType, String fileName, InputStream is) throws WechatException;

}
