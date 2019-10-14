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
 * 系统文件管理
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 14:34:35
 */
@Data
@Api("系统文件管理-保存")
public class SysAttachmentSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 名称
 */
                @ApiModelProperty(value = "名称")
private String name;
                    /**
 * 类型
 */
                @ApiModelProperty(value = "类型")
private String type;
                    /**
 * 加密类型
 */
                @ApiModelProperty(value = "加密类型")
private String sysType;
                    /**
 * 内容
 */
                @ApiModelProperty(value = "内容")
private String text;
                    /**
 * 大小(KB)
 */
                @ApiModelProperty(value = "大小(KB)")
private String length;
                                                                                                                                                                                                

}
