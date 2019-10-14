package io.renren.modules.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.device.entity.DeviceCabinetEntity;
import io.renren.modules.device.request.DeviceCabinetPageRequest;
import io.renren.modules.device.request.DeviceCabinetPushRequest;
import java.util.*;

/**
 * 机柜
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
public interface DeviceCabinetService extends IService<DeviceCabinetEntity> {

    Map queryPage(DeviceCabinetPageRequest request);

    /***
     * 机柜-发布redis
     * @param request
     */
    void push(DeviceCabinetPushRequest request);
}

