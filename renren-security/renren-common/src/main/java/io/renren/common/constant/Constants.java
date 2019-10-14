package io.renren.common.constant;

public class Constants {

    //手机正则
    public static final String MOBILE_REGX = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(166)|(17[0135678])|(18[0-9])|(19[8|9]))\\d{8}$";
    public static final String CHAR_SPOT = ".";
    public static final String FLAG_SMS = "FLAG_SMS";
    public static final String FLAG_SMS_MAIL = "FLAG_SMS_MAIL";

    public static final String CONFIG_KEY = "CONFIG_KEY";
    //文件服务器域名路径
    public static final String PROFILE = "PROFILE";
    //文件上传路径
    public static final String FILE_PATH = "FILE_PATH";


    //是否
    public static final String STATE_YES = "Y";
    public static final String STATE_NO = "N";

    //资源类型
    public static final String SYS_RESOURCES_TYPE_SERVICE = "SERVICE";

    //请求方式
    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";


    //短信发送类型
    public static final Integer SMS_TYPE_REGIEST = 1;
    public static final Integer SMS_TYPE_FORGET = 2;
    public static final Integer SMS_TYPE_LOGIN = 3;
    public static final Integer SMS_TYPE_APPLY = 5;


    //=======================TOKEN登录授权============================
    //毫秒
    public static final Integer MILLISECOND = 1000;
    //是否开启自动注册服务功能
    public static final String IS_AUTO = "IS_AUTO";

    //获取全局超期时间
    public static final String EXPIRES_IN = "EXPIRES_IN";


    public static final String IS_TOKEN = "IS_TOKEN";
    public static final String PAY_CODE = "PAY_CODE";

    public static final String CU_SIGN_NUM = "CU_SIGN_NUM";


    //TOKEN前缀

    public static final String X_TOKEN = "TOKEN";
    public static final String X_OAUTH_TOKEN_TOKEN = "X:OAUTH:TOKEN:TOKEN:";
    public static final String X_OAUTH_TOKEN_USERID = "X:OAUTH:TOKEN:USERID:";

    public static final String SYS_USER_NAME = "SYS:USER:NAME:";
    public static final String SYS_USER_ID = "SYS:USER:ID:";
    public static final String SYS_ROLE_LIST = "SYS:ROLE:LIST:";
    public static final String SYS_ROLE_RESOURCES = "SYS:ROLE:RESOURCES:";

    public static final String SYS_RESOURCES = "SYS:RESOURCES:";
    public static final String SYS_CONFIG = "SYS:CONFIG:";
    public static final String SYS_SERVICE = "SYS:SERVICE:";
    public static final String SYS_OAUTH_CODE_OPENID = "SYS:OAUTH:CODE:OPENID:";
    public static final String SYS_OAUTH_CODE_USERID = "SYS:OAUTH:CODE:USERID:";

    public static final String CU_INFO = "CU:INFO:";
    public static final String CU_ACCOUNT = "CU:ACCOUNT:";
    public static final String CU_COUNT = "CU:COUNT:";


    public static final String X_RESOURCE = "X_RESOURCE";

    //TOKEN前缀
    public static final String X_AUTHZ_CODE = "X:AUTHZ:CODE:";

    //微信CODE配置登录地址
    public static final String WEIXIN_LOGIN_CONFIG = "WEIXIN_LOGIN_CONFIG";
    public static final String ACCESS_LOGIN_CONFIG = "ACCESS_LOGIN_CONFIG";

    public static final String WEIXIN_URL = "url";
    public static final String WEIXIN_APPID = "appid";
    public static final String WEIXIN_SECRET = "secret";
    public static final String WEIXIN_GRANT_TYPE = "grant_type";
    public static final String WEIXIN_JS_CODE = "js_code";
    public static final String WEIXIN_CODE = "code";
    public static final String WEIXIN_OPEN_ID = "openid";
    public static final String WEIXIN_ACCESS_TOKEN = "access_token";


    public static final String WEIXIN_OPEN_PASSWD = "123456";

    public static final String WEIXIN_OPEN_ROLE_ID = "WEIXIN_OPEN_ROLE_ID";


}
