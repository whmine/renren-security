package io.renren.modules.token.service;

import io.renren.common.exception.RRException;
import io.renren.modules.token.request.LoginAccessRequest;

import java.util.Map;

public interface ApiTokenAccessService {
    /***
     * 登录-微信公众账号
     * @param form
     * @return
     * @throws RRException
     */
    Map<String, Object> loginAccess(LoginAccessRequest form) throws RRException;
}
