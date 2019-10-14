package io.renren.modules.sys.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.IDRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 广告轮播图
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:00:09
 */
@Data
@Api("广告轮播图-修改")
public class SysAdUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 广告名字
     */
    @ApiModelProperty(value = "广告名字")
    private String adName;
    /**
     * 链接地址
     */
    @ApiModelProperty(value = "链接地址")
    private String adLink;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String adCode;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;
    /**
     * 添加人手机号
     */
    @ApiModelProperty(value = "添加人手机号")
    private String linkPhone;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    /**
     * 点击量
     */
    @ApiModelProperty(value = "点击量")
    private Integer clickCount;
    /**
     * 是否显示 0  1
     */
    @ApiModelProperty(value = "是否显示 0  1")
    private String enabled;

}
