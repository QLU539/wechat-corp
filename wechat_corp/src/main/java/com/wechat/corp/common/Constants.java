package com.wechat.corp.common;

public class Constants {
	//企业ID
	public final static String CORP_ID = "";
	//通讯录secret
	public final static String MEMEBERS_SECRET = "";
	//企业小助手
	public final static String ASSISTANT_SECRET = "";
	public final static String ASSISTANT_AGENTID = "0";
	//测试应用
	public final static String TEST_SECRET = "";
	public final static String TEST_AGENTID = "1";
	public final static String TEST_STOKEN = "";
	public final static String TEST_SENCODINGAESKEY = "";

	//OAuth2.0鉴权
	public final static String LOCAL_PAGE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CORP_ID+"&redirect_uri={0}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	//获取accessToken
	public final static String URL_GETTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
	//鉴权根据user_tiket获取成员详细信息
	public final static String URL_USER_DETAIL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token={0}&lang=zh_CN";
	//二次验证
	public final static String URL_USER_AUTHSUCC = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token={0}&userid={1}";
	//创建成员
	public final static String URL_USER_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token={0}";
	//更新成员
	public final static String URL_USER_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token={0}";
	//删除微信成员
	public final static String URL_USER_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token={0}&userid={1}";
	//批量删除微信成员
	public final static String URL_USER_BATCHDELETE = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token={0}";
	//获取微信成员信息
	public final static String URL_USER_GET = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token={0}&userid={1}";
	//获取部门成员
	public final static String URL_USER_SIMPLELIST = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token={0}&department_id={1}&fetch_child={2}&status={3}";
	//获取部门成员详情
	public final static String URL_USER_LIST = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token={0}&department_id={1}&fetch_child={2}&status={3}";
	//根据code获取成员信息
	public final static String URL_USER_GETUSERINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token={0}&code={1}";
	//userId转换openId
	public final static String URL_USER_CONVERTTOOPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token={0}";
	//openId转换userId
	public final static String URL_USER_CONVERTTOUSERID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_userid?access_token={0}";
	//创建部门
	public final static String URL_DEPARTMENT_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token={0}";
	//更新部门
	public final static String URL_DEPARTMENT_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token={0}";
	//删除部门
	public final static String URL_DEPARTMENT_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token={0}&id={1}";
	//获取部门
	public final static String URL_DEPARTMENT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token={0}&id={1}";
	//创建标签
	public final static String URL_TAG_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token={0}";

	public final static String URL_TAG_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token={0}";

	public final static String URL_TAG_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token={0}&tagid={1}";

	public final static String URL_TAG_GET = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token={0}&tagid={1}";

	public final static String URL_TAG_ADDTAGUSERS = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token={0}";

	public final static String URL_TAG_DELTAGUSERS = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token={0}";

	public final static String URL_TAG_LIST = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token={0}";

	//创建菜单
	public final static String URL_MENU_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token={0}&agentid={1}";
	//删除菜单
	public final static String URL_MENU_DELETE = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token={0}&agentid={1}";
	//获取菜单
	public final static String URL_MENU_GET = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token={0}&agentid={1}";
	//消息发送
	public final static String URL_MESSAGE_SEND = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={0}";
	//上传素材
	public final static String URL_MEDIA_UPLOAD = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token={0}&type={1}";
	//获取素材
	public final static String URL_MEDIA_GET = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token={0}&media_id={1}";

	public final static String URL_MEDIA_UPLOADIMG = "https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?access_token={0}";

	public final static String URL_MATERIAL_ADDMATERIAL = "https://qyapi.weixin.qq.com/cgi-bin/material/add_material?agentid={0}&type={1}&access_token={2}";

	public final static String URL_MATERIAL_ADDMPNEWS = "https://qyapi.weixin.qq.com/cgi-bin/material/add_mpnews?access_token={0}";

	public final static String URL_MATERIAL_GET = "https://qyapi.weixin.qq.com/cgi-bin/material/get?access_token={0}&media_id={1}&agentid={2}";

	public final static String URL_MATERIAL_DEL = "https://qyapi.weixin.qq.com/cgi-bin/material/del?access_token={0}&agentid={1}&media_id={2}";

	public final static String URL_MATERIAL_UPDATEMPNEWS = "https://qyapi.weixin.qq.com/cgi-bin/material/update_mpnews?access_token={0}";

	public final static String URL_MATERIAL_GETCOUNT = "https://qyapi.weixin.qq.com/cgi-bin/material/get_count?access_token={0}&agentid={1}";

	public final static String URL_MATERIAL_BATCHGET = "https://qyapi.weixin.qq.com/cgi-bin/material/batchget?access_token={0}";

	public final static String URL_AGENT_GET = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token={0}&agentid={1}";

	public final static String URL_AGENT_SET = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token={0}";

	public final static String URL_AGENT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token={0}";

	public final static String RESP_ACCESS_TOKEN = "access_token";

	public final static String RESP_SUITE_ACCESS_TOKEN = "suite_access_token";

	public final static String RESP_EXPIRES_IN = "expires_in";

	public final static String RESP_ERRCODE = "errcode";

	public final static String RESP_ERRMSG = "errmsg";

	public final static String MSGTYPE_TEXT = "text";

	public final static String MSGTYPE_IMAGE = "image";

	public final static String MSGTYPE_VOICE = "voice";

	public final static String MSGTYPE_VIDEO = "video";

	public final static String MSGTYPE_FILE = "file";

	public final static String MSGTYPE_SHORTVIDEO = "shortvideo";

	public final static String MSGTYPE_LOCATION = "location";

	public final static String MSGTYPE_LINK = "link";

	public final static String MSGTYPE_EVENT = "event";

	public final static String MSGTYPE_NEWS = "news";

	public final static String MSGTYPE_MPNEWS = "mpnews";

	public final static String EVENT_SUBSCRIBE = "subscribe";

	public final static String EVENT_LOCATION = "LOCATION";

	public final static String EVENT_CLICK = "click";

	public final static String EVENT_VIEW = "view";

	public final static String EVENT_SCANCODE_PUSH = "scancode_push";

	public final static String EVENT_SCANCODE_WAITMSG = "scancode_waitmsg";

	public final static String EVENT_PIC_SYSPHOTO = "pic_sysphoto";

	public final static String EVENT_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";

	public final static String EVENT_PIC_WEIXIN = "pic_weixin";

	public final static String EVENT_LOCATION_SELECT = "location_select";

	public final static String EVENT_ENTER_AGENT = "enter_agent";

	public final static String EVENT_BATCH_JOB_RESULT = "batch_job_result";

	public final static String INFOTYPE_SUITE_TICKET = "suite_ticket";

	public final static String INFOTYPE_CHANGE_AUTH = "change_auth";

	public final static String INFOTYPE_CANCEL_AUTH = "cancel_auth";

	public final static String BATCH_TYPE_SYNC_USER = "sync_user";

	public final static String BATCH_TYPE_REPLACE_USER = "replace_user";

	public final static String BATCH_TYPE_INVITE_USER = "invite_user";

	public final static String BATCH_TYPE_REPLACE_PARTY = "replace_party";

	public final static String URL_BATCH_INVITEUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/inviteuser?access_token={0}";

	public final static String URL_BATCH_SYNCUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/syncuser?access_token={0}";

	public final static String URL_BATCH_REPLACEUSER = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceuser?access_token={0}";

	public final static String URL_BATCH_REPLACEPARTY = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceparty?access_token={0}";

	public final static String URL_BATCH_GETRESULT = "https://qyapi.weixin.qq.com/cgi-bin/batch/getresult?access_token={0}&jobid={1}";

	//第三方应用
	public final static String URL_GETSUITETOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";

	public final static String URL_GETCORPTOKEN = "https://qyapi.weixin.qq.com/cgi-bin/service/get_corp_token?suite_access_token={0}";

	public final static String URL_SERVICE_GETPREAUTHCODE = "https://qyapi.weixin.qq.com/cgi-bin/service/get_pre_auth_code?suite_access_token={0}";

	public final static String URL_SERVICE_SETSESSIONINFO = "https://qyapi.weixin.qq.com/cgi-bin/service/set_session_info?suite_access_token={0}";

	public final static String URL_SERVICE_GETPERMANENTCODE = "https://qyapi.weixin.qq.com/cgi-bin/service/get_permanent_code?suite_access_token={0}";

	public final static String URL_SERVICE_GETAUTHINFO = "https://qyapi.weixin.qq.com/cgi-bin/service/get_auth_info?suite_access_token={0}";

	public final static String URL_GETCALLBACKIP = "https://qyapi.weixin.qq.com/cgi-bin/getcallbackip?access_token={0}";

	public final static String URL_INVITE_SEND = "https://qyapi.weixin.qq.com/cgi-bin/invite/send?access_token={0}";

}
