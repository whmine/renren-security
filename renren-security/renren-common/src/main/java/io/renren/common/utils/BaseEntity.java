package io.renren.common.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Entity基类
 *
 * @author basics
 */
@Data
public class BaseEntity implements Serializable {
//    /** 搜索值 */
//    @TableField(exist = false)
//    private String searchValue;
//
//    /** 请求参数 */
//    @TableField(exist = false)
//    private Map<String, Object> params;


    /**
     * 所属系统
     */
    private String sysSystem;

    private Long sort;
    /**
     * 逻辑删除标识(A正常 P删除)
     */
    @TableLogic
    private String status;

    /**
     * 备注说明
     */
    private String remarks;

    /**
     * 版本号
     */
    @Version
    private Long version;

    /**
     * 创建人
     */
    private String createdUserId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * 修改人
     */
    private String modifyUserId;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
}
