package io.renren.modules.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.device.entity.DeviceCabinetLogEntity;
import io.renren.modules.device.request.DeviceCabinetLogPageRequest;
import io.renren.modules.device.request.DeviceCabinetLogPushRequest;
import java.util.*;

/**
 * 机柜租借
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
public interface DeviceCabinetLogService extends IService<DeviceCabinetLogEntity> {

    Map queryPage(DeviceCabinetLogPageRequest request);

    /***
     * 机柜租借-发布redis
     * @param request
     */
    void push(DeviceCabinetLogPushRequest request);
}

