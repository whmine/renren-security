package io.renren.modules.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.device.entity.DeviceChargeEntity;
import io.renren.modules.device.request.DeviceChargePageRequest;
import io.renren.modules.device.request.DeviceChargePushRequest;
import java.util.*;

/**
 * 充电宝
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
public interface DeviceChargeService extends IService<DeviceChargeEntity> {

    Map queryPage(DeviceChargePageRequest request);

    /***
     * 充电宝-发布redis
     * @param request
     */
    void push(DeviceChargePushRequest request);
}

