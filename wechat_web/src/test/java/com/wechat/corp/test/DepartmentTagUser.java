package com.wechat.corp.test;

import org.apache.http.client.HttpClient;

import com.wechat.corp.cgi.CgiUtils;
import com.wechat.corp.cgi.Department;
import com.wechat.corp.cgi.Tag;
import com.wechat.corp.cgi.User;
import com.wechat.corp.common.Constants;
import com.wechat.corp.connect.HttpClientFactory;
import com.wechat.corp.connect.WechatClientHCE;

import net.sf.json.JSONObject;


public class DepartmentTagUser {

	public static void add() throws Exception {
		WechatClientHCE wc = new WechatClientHCE(Constants.CORP_ID, Constants.MEMEBERS_SECRET);
		HttpClient httpClient = HttpClientFactory.createHttpClient();
		wc.setHttpClient(httpClient);

		System.out.println("获取部门：" + Department.list("1", wc).toString());// 获取部门
		System.out.println("获取标签：" + Tag.list(wc).toString());// 获取标签
		JSONObject createTagJson = CgiUtils.getTagCreate("标签100", "100");
		System.out.println("创建标签：" + Tag.create(createTagJson, wc));// 创建标签
		JSONObject createDepartmentJson1 = CgiUtils.getDepartmentCreate("开发部门", "1", "3", "3");
		System.out.println("创建部门：" + Department.create(createDepartmentJson1, wc).toString());// 创建部门
		JSONObject createDepartmentJson2 = CgiUtils.getDepartmentCreate("需求部门", "1", "4", "4");
		System.out.println("创建部门：" + Department.create(createDepartmentJson2, wc).toString());// 创建部门
		JSONObject createDepartmentJson3 = CgiUtils.getDepartmentCreate("运维部门", "1", "5", "5");
		System.out.println("创建部门：" + Department.create(createDepartmentJson3, wc).toString());// 创建部门

		JSONObject createUserJson = CgiUtils.getUserCreate("malushun", "马路顺", new Integer[] { 3 }, "开发",
				"13021708885", "1", "mlucas@163.com", "mlucaswx", null, null);
//			JSONObject createUserJson = CgiUtils.getUserCreate("zhangsan", "张三", new Integer[] { 200 }, "产品经理",
//					"15913215421", "1", "zhangsan@gzdev.com", "zhangsan4dev", null, extattr);
		System.out.println("创建成员：" + User.create(createUserJson, wc));// 创建成员
		JSONObject addtagusersJson = CgiUtils.getTagAddtagusers("100", new String[] { "malushun" },
				new Integer[] { 3 });
		System.out.println("添加成员标签：" + Tag.addtagusers(addtagusersJson, wc));// 添加成员标签

	}

	public static void del() throws Exception {
		WechatClientHCE wc = new WechatClientHCE(Constants.CORP_ID, Constants.MEMEBERS_SECRET);
		HttpClient httpClient = HttpClientFactory.createHttpClient();
		wc.setHttpClient(httpClient);

		System.out.println("获取标签：" + Tag.list(wc).toString());// 获取标签
		System.out.println("获取标签成员：" + Tag.get("100", wc).toString());// 获取标签成员
		System.out.println("获取部门200：" + Department.list("200", wc).toString());// 获取部门200
		System.out.println("获取成员张三：" + User.get("malushun", wc).toString());// 获取成员
		System.out.println("获取部门成员详情：" + User.list("3", "1", "0", wc).toString());// 获取部门成员详情
		System.out.println("删除成员：" + User.delete("malushun", wc).toString());// 删除成员
		System.out.println("删除部门：" + Department.delete("3", wc).toString());// 删除部门
		System.out.println("删除部门：" + Department.delete("4", wc).toString());// 删除部门
		System.out.println("删除部门：" + Department.delete("5", wc).toString());// 删除部门

		JSONObject deltagusersJson = CgiUtils.getTagdeltagusers("100", new String[] { "malushun" },
				new Integer[] { 3 });

		System.out.println("删除标签成员：" + Tag.deltagusers(deltagusersJson, wc).toString());// 删除标签
		System.out.println("删除标签：" + Tag.delete("100", wc).toString());// 删除标签
	}

	public static void main(String[] args) {
		try {
//			add();
			del();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
