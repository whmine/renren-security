package io.renren.modules.cu.dao;

import io.renren.modules.cu.entity.CuSignEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户签到
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:41:36
 */

@Mapper
public interface CuSignDao extends BaseMapper<CuSignEntity> {

}
