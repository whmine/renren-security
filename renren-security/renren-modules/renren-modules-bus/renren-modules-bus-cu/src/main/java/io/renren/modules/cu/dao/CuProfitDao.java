package io.renren.modules.cu.dao;

import io.renren.modules.cu.entity.CuProfitEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 客户收益信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */

@Mapper
public interface CuProfitDao extends BaseMapper<CuProfitEntity> {

    /**
     * 收益
     *
     * @param map
     * @return
     */
    Map sumProfit(Map map);

    /**
     * 支出
     *
     * @param map
     * @return
     */
    Map sumPay(Map map);

    List<CuProfitEntity> myList(Map map);

}
