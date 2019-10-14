package io.renren.modules.mq.dao;

import io.renren.modules.mq.entity.SysActivemqResponseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息结果表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-05-28 15:39:34
 */
@Mapper
public interface SysActivemqResponseDao extends BaseMapper<SysActivemqResponseEntity> {

}
