package io.renren.modules.cu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cu.entity.CuProfitEntity;
import io.renren.modules.cu.request.CuProfitCountRequest;
import io.renren.modules.cu.request.CuProfitMyListRequest;
import io.renren.modules.cu.request.CuProfitPageRequest;

import java.util.*;

/**
 * 客户收益信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
public interface CuProfitService extends IService<CuProfitEntity> {

    Map queryPage(CuProfitPageRequest request);

    Map count(CuProfitCountRequest request) throws RRException;

    List<CuProfitEntity> myList(CuProfitMyListRequest request) throws RRException;
}

