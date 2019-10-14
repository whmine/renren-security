package io.renren.modules.cu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户收益信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
@Data
@TableName("cu_profit")
public class CuProfitEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 客户
     */
    private String customerId;
    /**
     * 来源ID
     */
    private String sourceId;
    /**
     * 来源类型
     */
    private String sourceType;
    /**
     * 收益
     */
    private BigDecimal profitNum;
    /**
     * 货币类型
     */
    private String profitType;
    /**
     * 收益状态(1收入 0支出)
     */
    private String profitStatus;

}
