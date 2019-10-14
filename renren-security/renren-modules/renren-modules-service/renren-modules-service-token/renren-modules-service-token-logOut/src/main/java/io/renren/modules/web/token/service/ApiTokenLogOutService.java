package io.renren.modules.web.token.service;

import io.renren.common.exception.RRException;
import io.renren.modules.token.request.LogOutRequest;


public interface ApiTokenLogOutService {


    /***
     *登录-TOKEN退出
     * @param request
     * @return
     */
    void logOut(LogOutRequest request) throws RRException;
}
