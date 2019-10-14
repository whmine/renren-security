package io.renren.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SmsWhUtil {

	private static final String BaseUrl = "http://123.58.1.121:7862/sms";

	public static final String SMS_MODE = "【DEtoken】";

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(send("15207180551", "3545235", SMS_MODE));
	}

	public static String send(String mobile, String code, final String smsMode) throws UnsupportedEncodingException {
		String content = URLEncoder.encode("您的短信验证码为" + code + "，有效时间5分钟，请不要把验证码泄漏给其他人，如非本人请勿操作！" + smsMode, "utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append("action=send");
		sb.append("&account=110008");
		sb.append("&password=THk3Ww");
		sb.append("&mobile=");
		sb.append(mobile.trim());
		sb.append("&content=");
		sb.append(content);
		sb.append("&extno=106901051");
		sb.append("&rt=json");
		return sendGet(BaseUrl, sb.toString());
	}

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Content-Type", "application/json;charset=utf-8"); // 设置发送数据的格式
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
