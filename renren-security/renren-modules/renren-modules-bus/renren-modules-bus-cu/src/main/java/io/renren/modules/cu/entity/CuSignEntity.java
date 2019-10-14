package io.renren.modules.cu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户签到
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:41:36
 */
@Data
@TableName("cu_sign")
public class CuSignEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 客户ID
     */
    private String customerId;
    /**
     * 签到日期
     */
    private String customerDate;
    /**
     * 签到日期年
     */
    private String customerYear;
    /**
     * 签到日期月
     */
    private String customerMonth;
    /**
     * 签到日期日
     */
    private String customerDay;

}
