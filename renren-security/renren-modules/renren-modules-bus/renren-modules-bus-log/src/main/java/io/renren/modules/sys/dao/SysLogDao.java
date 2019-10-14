package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:04:07
 */

@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

}
