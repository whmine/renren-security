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
 * 客户账目信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
@Data
@TableName("cu_account" )
public class CuAccountEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID" , type = IdType.UUID)
    private String id;
    /**
     * 余额
     */
    private BigDecimal customerMoney;

}
