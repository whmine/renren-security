package io.renren.modules.shop.dao;

import io.renren.modules.shop.entity.ShopGoodsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */

@Mapper
public interface ShopGoodsDao extends BaseMapper<ShopGoodsEntity> {

}
