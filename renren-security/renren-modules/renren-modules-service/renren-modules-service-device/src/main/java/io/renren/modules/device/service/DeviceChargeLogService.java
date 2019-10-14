package io.renren.modules.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.device.entity.DeviceChargeLogEntity;
import io.renren.modules.device.request.DeviceChargeLogPageRequest;
import io.renren.modules.device.request.DeviceChargeLogPushRequest;
import java.util.*;

/**
 * 充电宝租借
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
public interface DeviceChargeLogService extends IService<DeviceChargeLogEntity> {

    Map queryPage(DeviceChargeLogPageRequest request);

    /***
     * 充电宝租借-发布redis
     * @param request
     */
    void push(DeviceChargeLogPushRequest request);
}

