package io.renren.modules.shop.dao;

import io.renren.modules.shop.entity.ShopPayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付订单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:44
 */

@Mapper
public interface ShopPayDao extends BaseMapper<ShopPayEntity> {

}
