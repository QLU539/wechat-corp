package com.wechat.corp.cgi;

import java.io.File;
import java.io.OutputStream;
import java.text.MessageFormat;

import com.wechat.corp.bean.WechatException;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.DownLoadHandler;
import com.wechat.corp.connect.WechatClient;

import net.sf.json.JSONObject;


public abstract class Material {

	/**
	 * 上传其他类型永久素材
	 */
	public static JSONObject addMaterial(String agentid, String type, File file, WechatClient wc)
			throws WechatException {
		String accessToken = wc.getAccessToken();
		String addMaterialUrl = MessageFormat.format(Constants.URL_MATERIAL_ADDMATERIAL, agentid, type, accessToken);
		String respJson = wc.upload(addMaterialUrl, file);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 上传永久图文素材
	 */
	public static JSONObject addMpnews(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String addMpnewsUrl = MessageFormat.format(Constants.URL_MATERIAL_ADDMPNEWS, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(addMpnewsUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取永久素材
	 */
	public static JSONObject get(String mediaId, String agentid, OutputStream os, WechatClient wc)
			throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_MATERIAL_GET, accessToken, mediaId, agentid);
		String respJson = wc.download(getUrl, os);
		return respJson == null ? null : JSONObject.fromObject(respJson);
	}

	/**
	 * 获取永久素材
	 */
	public static JSONObject get(String mediaId, String agentid, DownLoadHandler handler, WechatClient wc)
			throws WechatException {
		String accessToken = wc.getAccessToken();
		String getUrl = MessageFormat.format(Constants.URL_MATERIAL_GET, accessToken, mediaId, agentid);
		String respJson = wc.download(getUrl, handler);
		return respJson == null ? null : JSONObject.fromObject(respJson);
	}

	/**
	 * 删除永久素材
	 */
	public static JSONObject del(String mediaId, String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String delUrl = MessageFormat.format(Constants.URL_MATERIAL_DEL, accessToken, agentid, mediaId);
		String respJson = wc.get(delUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 修改永久图文素材
	 */
	public static JSONObject updateMpnews(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String updateMpnewsUrl = MessageFormat.format(Constants.URL_MATERIAL_UPDATEMPNEWS, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(updateMpnewsUrl, msg);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取素材总数
	 */
	public static JSONObject getCount(String agentid, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String getCountUrl = MessageFormat.format(Constants.URL_MATERIAL_GETCOUNT, accessToken, agentid);
		String respJson = wc.get(getCountUrl);
		return JSONObject.fromObject(respJson);
	}

	/**
	 * 获取素材列表
	 */
	public static JSONObject batchget(JSONObject msgJson, WechatClient wc) throws WechatException {
		String accessToken = wc.getAccessToken();
		String batchgetUrl = MessageFormat.format(Constants.URL_MATERIAL_BATCHGET, accessToken);
		String msg = msgJson.toString();
		String respJson = wc.post(batchgetUrl, msg);
		return JSONObject.fromObject(respJson);
	}

}
