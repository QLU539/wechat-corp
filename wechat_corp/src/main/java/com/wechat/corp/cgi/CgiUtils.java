package com.wechat.corp.cgi;

import java.util.List;
import java.util.Map;

import com.wechat.corp.bean.MpnewsItem;
import com.wechat.corp.bean.NewsItem;
import com.wechat.corp.common.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public abstract class CgiUtils {

	public static boolean refreshAccessToken(JSONObject json) {
		if (json.containsKey(Constants.RESP_ERRCODE)) {
			String errcode = json.getString(Constants.RESP_ERRCODE);
			if ("40014".equals(errcode) || "42001".equals(errcode)) {
				return true;
			}
		}
		return false;
	}

	public static JSONObject getAgentSet(String agentid, String reportLocationFlag, String logoMediaid, String name,
			String description, String redirectDomain, String isreportenter, String homeUrl) {
		JSONObject json = new JSONObject();
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		if (reportLocationFlag != null) {
			json.put("report_location_flag", reportLocationFlag);
		}
		if (logoMediaid != null) {
			json.put("logo_mediaid", logoMediaid);
		}
		if (name != null) {
			json.put("name", name);
		}
		if (description != null) {
			json.put("description", description);
		}
		if (redirectDomain != null) {
			json.put("redirect_domain", redirectDomain);
		}
		if (isreportenter != null) {
			json.put("isreportenter", isreportenter);
		}
		if (homeUrl != null) {
			json.put("home_url", homeUrl);
		}
		return json;
	}

	public static JSONObject getDepartmentCreate(String name, String parentid, String order, String id) {
		JSONObject json = new JSONObject();
		if (name != null) {
			json.put("name", name);
		}
		if (parentid != null) {
			json.put("parentid", parentid);
		}
		if (order != null) {
			json.put("order", order);
		}
		if (id != null) {
			json.put("id", id);
		}
		return json;
	}

	public static JSONObject getDepartmentUpdate(String id, String name, String parentid, String order) {
		JSONObject json = new JSONObject();
		if (id != null) {
			json.put("id", id);
		}
		if (name != null) {
			json.put("name", name);
		}
		if (parentid != null) {
			json.put("parentid", parentid);
		}
		if (order != null) {
			json.put("order", order);
		}
		return json;
	}

	public static JSONObject getInviteSend(String userid) {
		JSONObject json = new JSONObject();
		if (userid != null) {
			json.put("userid", userid);
		}
		return json;
	}

	public static JSONObject getMaterialAddMpnews(String agentid, List<MpnewsItem> mpnewsItems) {
		JSONObject json = new JSONObject();
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONArray articles = new JSONArray();
		for (MpnewsItem mpnewsItem : mpnewsItems) {
			JSONObject item = new JSONObject();
			item.put("title", mpnewsItem.getTitle());
			item.put("thumb_media_id", mpnewsItem.getThumbMediaId());
			item.put("author", mpnewsItem.getAuthor());
			item.put("content_source_url", mpnewsItem.getContentSourceUrl());
			item.put("content", mpnewsItem.getContent());
			item.put("digest", mpnewsItem.getDigest());
			item.put("show_cover_pic", mpnewsItem.getShowCoverPic());
			articles.add(item);
		}
		JSONObject mpnews = new JSONObject();
		mpnews.put("articles", articles);
		json.put("mpnews", mpnews);
		return json;
	}

	public static JSONObject getMaterialUpdateMpnews(String agentid, String mediaId, List<MpnewsItem> mpnewsItems) {
		JSONObject json = new JSONObject();
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		if (mediaId != null) {
			json.put("media_id", mediaId);
		}
		JSONArray articles = new JSONArray();
		for (MpnewsItem mpnewsItem : mpnewsItems) {
			JSONObject item = new JSONObject();
			item.put("title", mpnewsItem.getTitle());
			item.put("thumb_media_id", mpnewsItem.getThumbMediaId());
			item.put("author", mpnewsItem.getAuthor());
			item.put("content_source_url", mpnewsItem.getContentSourceUrl());
			item.put("content", mpnewsItem.getContent());
			item.put("digest", mpnewsItem.getDigest());
			item.put("show_cover_pic", mpnewsItem.getShowCoverPic());
			articles.add(item);
		}
		JSONObject mpnews = new JSONObject();
		mpnews.put("articles", articles);
		json.put("mpnews", mpnews);
		return json;
	}

	public static JSONObject getMaterialBatchget(String type, String agentid, Integer offset, Integer count) {
		JSONObject json = new JSONObject();
		if (type != null) {
			json.put("type", type);
		}
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		if (offset != null) {
			json.put("offset", offset);
		}
		if (count != null) {
			json.put("count", count);
		}
		return json;
	}

	public static JSONObject getMessageText(String touser, String toparty, String totag, String agentid,
			String content, Integer safe) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_TEXT);
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONObject textJson = new JSONObject();
		textJson.put("content", content);
		json.put("text", textJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	public static JSONObject getMessageImage(String touser, String toparty, String totag, String agentid,
			String mediaId, Integer safe) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_IMAGE);
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONObject imageJson = new JSONObject();
		imageJson.put("media_id", mediaId);
		json.put("image", imageJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	public static JSONObject getMessageVoice(String touser, String toparty, String totag, String agentid,
			String mediaId, Integer safe) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_VOICE);
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONObject voiceJson = new JSONObject();
		voiceJson.put("media_id", mediaId);
		json.put("voice", voiceJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	public static JSONObject getMessageVideo(String touser, String toparty, String totag, String agentid,
			String mediaId, String title, String description, Integer safe) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_VIDEO);
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONObject videoJson = new JSONObject();
		videoJson.put("media_id", mediaId);
		videoJson.put("title", title);
		videoJson.put("description", description);
		json.put("video", videoJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	public static JSONObject getMessageFile(String touser, String toparty, String totag, String agentid,
			String mediaId, Integer safe) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_FILE);
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONObject fileJson = new JSONObject();
		fileJson.put("media_id", mediaId);
		json.put("file", fileJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	public static JSONObject getMessageNews(String touser, String toparty, String totag, String agentid,
			List<NewsItem> newsItems) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_NEWS);
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONObject newsJson = new JSONObject();
		JSONArray articlesJson = new JSONArray();
		for (NewsItem newsItem : newsItems) {
			JSONObject articleJson = new JSONObject();
			articleJson.put("title", newsItem.getTitle());
			articleJson.put("description", newsItem.getDescription());
			articleJson.put("url", newsItem.getUrl());
			articleJson.put("picurl", newsItem.getPicurl());
			articlesJson.add(articleJson);
		}
		newsJson.put("articles", articlesJson);
		json.put("news", newsJson);
		return json;
	}

	public static JSONObject getMessageMpnews(String touser, String toparty, String totag, String agentid,
			List<MpnewsItem> mpnewsItems, Integer safe) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_MPNEWS);
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONObject mpnewsJson = new JSONObject();
		JSONArray articlesJson = new JSONArray();
		for (MpnewsItem mpnewsItem : mpnewsItems) {
			JSONObject articleJson = new JSONObject();
			articleJson.put("title", mpnewsItem.getTitle());
			articleJson.put("thumb_media_id", mpnewsItem.getThumbMediaId());
			articleJson.put("author", mpnewsItem.getAuthor());
			articleJson.put("content_source_url", mpnewsItem.getContentSourceUrl());
			articleJson.put("content", mpnewsItem.getContent());
			articleJson.put("digest", mpnewsItem.getDigest());
			articleJson.put("show_cover_pic", mpnewsItem.getShowCoverPic());
			articlesJson.add(articleJson);
		}
		mpnewsJson.put("articles", articlesJson);
		json.put("mpnews", mpnewsJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	public static JSONObject getMessageMpnews(String touser, String toparty, String totag, String agentid,
			String mediaId, Integer safe) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		json.put("msgtype", Constants.MSGTYPE_MPNEWS);
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		JSONObject mpnewsJson = new JSONObject();
		mpnewsJson.put("media_id", mediaId);
		json.put("mpnews", mpnewsJson);
		if (safe != null) {
			json.put("safe", safe);
		}
		return json;
	}

	public static JSONObject getTagCreate(String tagname, String tagid) {
		JSONObject json = new JSONObject();
		if (tagname != null) {
			json.put("tagname", tagname);
		}
		if (tagid != null) {
			json.put("tagid", tagid);
		}
		return json;
	}

	public static JSONObject getTagUpdate(String tagname, String tagid) {
		JSONObject json = new JSONObject();
		if (tagname != null) {
			json.put("tagname", tagname);
		}
		if (tagid != null) {
			json.put("tagid", tagid);
		}
		return json;
	}

	public static JSONObject getTagAddtagusers(String tagid, String[] userlist, Integer[] partylist) {
		JSONObject json = new JSONObject();
		if (tagid != null) {
			json.put("tagid", tagid);
		}
		json.put("userlist", userlist);
		json.put("partylist", partylist);
		return json;
	}

	public static JSONObject getTagdeltagusers(String tagid, String[] userlist, Integer[] partylist) {
		JSONObject json = new JSONObject();
		if (tagid != null) {
			json.put("tagid", tagid);
		}
		json.put("userlist", userlist);
		json.put("partylist", partylist);
		return json;
	}

	public static JSONObject getUserCreate(String userid, String name, Integer[] department, String position,
			String mobile, String gender, String email, String weixinid, String avatarMediaid,
			List<Map<String, String>> extattr) {
		JSONObject json = new JSONObject();
		if (userid != null) {
			json.put("userid", userid);
		}
		if (name != null) {
			json.put("name", name);
		}
		json.put("department", department);
		if (position != null) {
			json.put("position", position);
		}
		if (mobile != null) {
			json.put("mobile", mobile);
		}
		if (gender != null) {
			json.put("gender", gender);
		}
		if (email != null) {
			json.put("email", email);
		}
		if (weixinid != null) {
			json.put("weixinid", weixinid);
		}
		if (avatarMediaid != null) {
			json.put("avatar_mediaid", avatarMediaid);
		}
		if (extattr != null){
			JSONObject extattrJson = new JSONObject();
			JSONArray attrsJson = new JSONArray();
			for (Map<String, String> map : extattr) {
				attrsJson.add(map);
			}
			extattrJson.put("attrs", attrsJson);
			json.put("extattr", extattrJson);
		}
		return json;
	}

	public static JSONObject getUserUpdate(String userid, String name, Integer[] department, String position,
			String mobile, String gender, String email, String weixinid, Integer enable, String avatarMediaid,
			List<Map<String, String>> extattr) {
		JSONObject json = new JSONObject();
		if (userid != null) {
			json.put("userid", userid);
		}
		if (name != null) {
			json.put("name", name);
		}
		json.put("department", department);
		if (position != null) {
			json.put("position", position);
		}
		if (mobile != null) {
			json.put("mobile", mobile);
		}
		if (gender != null) {
			json.put("gender", gender);
		}
		if (email != null) {
			json.put("email", email);
		}
		if (weixinid != null) {
			json.put("weixinid", weixinid);
		}
		if (enable != null) {
			json.put("enable", enable);
		}
		if (avatarMediaid != null) {
			json.put("avatar_mediaid", avatarMediaid);
		}
		JSONObject extattrJson = new JSONObject();
		JSONArray attrsJson = new JSONArray();
		for (Map<String, String> map : extattr) {
			attrsJson.add(map);
		}
		extattrJson.put("attrs", attrsJson);
		json.put("extattr", extattrJson);
		return json;
	}

	public static JSONObject getUserBatchDelete(String[] useridlist) {
		JSONObject json = new JSONObject();
		json.put("useridlist", useridlist);
		return json;
	}

	public static JSONObject getUserConvertToOpenid(String userid, String agentid) {
		JSONObject json = new JSONObject();
		if (userid != null) {
			json.put("userid", userid);
		}
		if (agentid != null) {
			json.put("agentid", agentid);
		}
		return json;
	}

	public static JSONObject getUserConvertToUserid(String openid) {
		JSONObject json = new JSONObject();
		if (openid != null) {
			json.put("openid", openid);
		}
		return json;
	}

	public static JSONObject getBatchInviteUser(String touser, String toparty, String totag, String url, String token,
			String encodingaeskey) {
		JSONObject json = new JSONObject();
		if (touser != null) {
			json.put("touser", touser);
		}
		if (toparty != null) {
			json.put("toparty", toparty);
		}
		if (totag != null) {
			json.put("totag", totag);
		}
		JSONObject callbackJson = new JSONObject();
		if (url != null) {
			callbackJson.put("url", url);
		}
		if (token != null) {
			callbackJson.put("token", token);
		}
		if (encodingaeskey != null) {
			callbackJson.put("encodingaeskey", encodingaeskey);
		}
		json.put("callback", callbackJson);
		return json;
	}

	public static JSONObject getBatchSyncUser(String mediaId, String url, String token, String encodingaeskey) {
		JSONObject json = new JSONObject();
		if (mediaId != null) {
			json.put("media_id", mediaId);
		}
		JSONObject callbackJson = new JSONObject();
		if (url != null) {
			callbackJson.put("url", url);
		}
		if (token != null) {
			callbackJson.put("token", token);
		}
		if (encodingaeskey != null) {
			callbackJson.put("encodingaeskey", encodingaeskey);
		}
		json.put("callback", callbackJson);
		return json;
	}

	public static JSONObject getBatchReplaceUser(String mediaId, String url, String token, String encodingaeskey) {
		JSONObject json = new JSONObject();
		if (mediaId != null) {
			json.put("media_id", mediaId);
		}
		JSONObject callbackJson = new JSONObject();
		if (url != null) {
			callbackJson.put("url", url);
		}
		if (token != null) {
			callbackJson.put("token", token);
		}
		if (encodingaeskey != null) {
			callbackJson.put("encodingaeskey", encodingaeskey);
		}
		json.put("callback", callbackJson);
		return json;
	}

	public static JSONObject getBatchReplaceParty(String mediaId, String url, String token, String encodingaeskey) {
		JSONObject json = new JSONObject();
		if (mediaId != null) {
			json.put("media_id", mediaId);
		}
		JSONObject callbackJson = new JSONObject();
		if (url != null) {
			callbackJson.put("url", url);
		}
		if (token != null) {
			callbackJson.put("token", token);
		}
		if (encodingaeskey != null) {
			callbackJson.put("encodingaeskey", encodingaeskey);
		}
		json.put("callback", callbackJson);
		return json;
	}

	public static JSONObject getServiceGetPreAuthCode(String suiteId) {
		JSONObject json = new JSONObject();
		if (suiteId != null) {
			json.put("suite_id", suiteId);
		}
		return json;
	}

	public static JSONObject getServiceSetSessionInfo(String preAuthCode, Integer[] appid) {
		JSONObject json = new JSONObject();
		if (preAuthCode != null) {
			json.put("pre_auth_code", preAuthCode);
		}
		JSONObject sessionInfoJson = new JSONObject();
		sessionInfoJson.put("appid", appid);
		json.put("session_info", sessionInfoJson);
		return json;
	}

	public static JSONObject getServiceGetPermanentCode(String suiteId, String authCode) {
		JSONObject json = new JSONObject();
		if (suiteId != null) {
			json.put("suite_id", suiteId);
		}
		if (authCode != null) {
			json.put("auth_code", authCode);
		}
		return json;
	}

	public static JSONObject getServiceGetAuthInfo(String suiteId, String authCorpid, String permanentCode) {
		JSONObject json = new JSONObject();
		if (suiteId != null) {
			json.put("suite_id", suiteId);
		}
		if (authCorpid != null) {
			json.put("auth_corpid", authCorpid);
		}
		if (permanentCode != null) {
			json.put("permanent_code", permanentCode);
		}
		return json;
	}

}
