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
 * 系统常量
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Data
@Api("系统常量-保存")
public class SysConfigSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 名称
 */
                @ApiModelProperty(value = "名称")
private String name;
                    /**
 * 中文名称
 */
                @ApiModelProperty(value = "中文名称")
private String cnName;
                    /**
 * 静态值
 */
                @ApiModelProperty(value = "静态值")
private String value;
                                                                                                                                                                                                

}
