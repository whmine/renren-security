package io.renren.modules.token.service;


import io.renren.common.exception.RRException;
import io.renren.modules.token.request.LoginSysRequest;
import io.renren.modules.token.request.LoginWeixinRequest;

import java.util.Map;

public interface ApiTokenSysService {

    /***
     *登录-本地账号
     * @param form
     * @return
     */
    Map loginSys(LoginSysRequest form) throws RRException;

}
