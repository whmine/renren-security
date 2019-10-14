package io.renren.modules.cu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cu.entity.CuSignEntity;
import io.renren.modules.cu.request.CuSignListRequest;
import io.renren.modules.cu.request.CuSignPageRequest;
import io.renren.modules.cu.request.CuSignPushRequest;
import io.renren.modules.cu.request.CuSignSignRequest;

import java.util.*;

/**
 * 客户签到
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:41:36
 */
public interface CuSignService extends IService<CuSignEntity> {

    Map queryPage(CuSignPageRequest request);

    /***
     * 客户签到-发布redis
     * @param request
     */
    void push(CuSignPushRequest request);

    /***
     * 签到
     * @param request
     * @throws RRException
     */
    void sign(CuSignSignRequest request) throws RRException;

    /***
     * 签到列表
     * @param request
     * @return
     * @throws RRException
     */
    List<CuSignEntity> signList(CuSignListRequest request) throws RRException;
}

