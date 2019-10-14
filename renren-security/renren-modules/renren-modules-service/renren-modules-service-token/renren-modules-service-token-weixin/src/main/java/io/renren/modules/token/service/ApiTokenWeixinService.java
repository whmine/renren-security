package io.renren.modules.token.service;


import io.renren.modules.token.request.LoginSysRequest;
import io.renren.modules.token.request.LoginWeixinRequest;

import java.util.Map;

public interface ApiTokenWeixinService {

    /***
     *登录-微信账号
     * @param form
     * @return
     */
    Map loginWeixin(LoginWeixinRequest form) throws Exception;


}
