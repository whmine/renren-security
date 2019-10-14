package io.renren.modules.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.device.entity.DeviceLineEntity;
import io.renren.modules.device.request.DeviceLinePageRequest;
import io.renren.modules.device.request.DeviceLinePushRequest;
import java.util.*;

/**
 * 密码线
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
public interface DeviceLineService extends IService<DeviceLineEntity> {

    Map queryPage(DeviceLinePageRequest request);

    /***
     * 密码线-发布redis
     * @param request
     */
    void push(DeviceLinePushRequest request);
}

