package io.renren.modules.web.token.service;

import io.renren.common.exception.RRException;
import io.renren.modules.token.request.InfoRequest;

import java.util.Map;

public interface ApiTokenInfoService {

    /***
     * 登录信息
     * @param request
     * @return
     */
    Map info(InfoRequest request) throws RRException;
}
