package io.renren.common.utils;

import java.util.UUID;


public class UseValue {

	public final static String xAppId ="wx70d4e40487e68a2b";
	//public final static String  NONCESTR = UUID.randomUUID().toString();
	public final static String xAppSecret ="c3d02e4a6052e7472c4df0bf7a5603a2";
	public final static String  NONCESTR = UUID.randomUUID().toString();
	public final static String AppId ="wx51f7ff831154b837";
	public final static String AppSecret ="d1f73d3cb5a331e4ab71380023e94849";
	public final static String getTokenUrl ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String getCodeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public final static String getOAuthAccessToken="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public final static String getreferAccessUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	public final static String getOAuthUserNews="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public final static String isOAuthAccessToken="https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	public final static String getJsapiUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	public final static String getSMSiUrl="https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";

	public final static String getMinUrl="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";


	public final static String getMinMESSAGEURL="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";

	public final static String getUnifiedorder="https://api.mch.weixin.qq.com/pay/unifiedorder";

	public final static String notify_url="http://ti.51pta.com/interfaces/pay/notify_url";

	public final static String Path_url="http://ti.51pta.com";

	//public final static String Path_IP="http://127.0.0.1";
	//public final static String Path_port="8181";
	public final static String Path_IP="http://ti.51pta.com";
	public final static String Path_port="80";





	public final static String MCHId ="1489234852";
	public final static String MCHKEY ="quanminkaojiaoshi188012770128888";

}
