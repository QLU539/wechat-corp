package com.wechat.corp.cgi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.DownLoadHandler;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;


public abstract class Media {

	/**
	 * 上传临时素材文件
	 */
	public static JSONObject upload(String type, File file, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String uploadUrl = MessageFormat.format(Constants.URL_MEDIA_UPLOAD, accessToken, type);
		String respJson = wc.upLoad(uploadUrl, file);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取临时素材文件
	 */
	public static JSONObject get(String mediaId, OutputStream os, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_MEDIA_GET, accessToken, mediaId);
		String respJson = wc.download(getUrl, os);
		return respJson == null ? null : JSONObject.fromObject(respJson);
	}

	/**
	 * 获取临时素材文件
	 */
	public static JSONObject get(String mediaId, DownLoadHandler handler, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_MEDIA_GET, accessToken, mediaId);
		String respJson = wc.download(getUrl, handler);
		return respJson == null ? null : JSONObject.fromObject(respJson);
	}

	/**
	 * 上传图文消息内的图片
	 */
	public static JSONObject uploadimg(File file, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String uploadimgUrl = MessageFormat.format(Constants.URL_MEDIA_UPLOADIMG, accessToken);
		String respJson = wc.upload(uploadimgUrl, file);
		return JSONObject.fromObject(respJson);
	}
	
	/**
	 * 上传媒体文件
	 * @param accessToken 接口访问凭证
	 * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件(file)
	 * @param media form-data中媒体文件标识，有filename、filelength、content-type等信息
	 * @param mediaFileUrl 媒体文件的url
	 * 上传的媒体文件限制
     * 图片（image）:1MB，支持JPG格式
     * 语音（voice）：2MB，播放长度不超过60s，支持AMR格式
     * 视频（video）：10MB，支持MP4格式
     * 普通文件（file）：10MB
	 * @throws WechatException 
	 * */
	public static JSONObject uploadMedia(String type, File file, WechatClient wc) throws WechatException {
		JSONObject jsonObject = new JSONObject();
		String accessToken = wc.getAccessToken();
		// 拼装请求地址
		String uploadMediaUrl = MessageFormat.format(Constants.URL_MEDIA_UPLOADIMG, accessToken);

		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			String filename = file.toString().substring(file.toString().lastIndexOf("\\")+1);
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\";\r\n", filename).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", "multipart/form-data;").getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 使用JSON-lib解析返回结果
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			String error = String.format("上传媒体文件失败：%s", e);
			System.out.println(error);
		}
		return jsonObject;
	}

}
