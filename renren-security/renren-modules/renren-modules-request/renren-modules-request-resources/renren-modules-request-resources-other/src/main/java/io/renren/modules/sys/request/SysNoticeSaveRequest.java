package io.renren.modules.sys.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.renren.common.base.BaseRequest;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统公告表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:00:09
 */
@Data
@Api("系统公告表-保存")
public class SysNoticeSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容")
    private String content;
    /**
     * 发布人名字
     */
    @ApiModelProperty(value = "发布人名字")
    private String tname;
    /**
     * 公告图片
     */
    @ApiModelProperty(value = "公告图片")
    private String cementUrl;
    /**
     * 阅读数
     */
    @ApiModelProperty(value = "阅读数")
    private Integer readNum;


}
