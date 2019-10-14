package io.renren.modules.cu.dao;

import io.renren.modules.cu.entity.CuAccountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户账目信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */

@Mapper
public interface CuAccountDao extends BaseMapper<CuAccountEntity> {

}
