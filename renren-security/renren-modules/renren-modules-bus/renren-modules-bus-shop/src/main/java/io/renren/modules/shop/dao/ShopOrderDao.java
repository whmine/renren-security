package io.renren.modules.shop.dao;

import io.renren.modules.shop.entity.ShopGoodsEntity;
import io.renren.modules.shop.entity.ShopOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 订单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */

@Mapper
public interface ShopOrderDao extends BaseMapper<ShopOrderEntity> {

    List<ShopGoodsEntity> myList(Map map);

}
