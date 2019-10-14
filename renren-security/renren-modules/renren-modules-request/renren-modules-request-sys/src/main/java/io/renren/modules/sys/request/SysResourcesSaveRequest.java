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
 * 系统资源
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统资源-保存")
public class SysResourcesSaveRequest extends BaseRequest {

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
 * 资源值
 */
                @ApiModelProperty(value = "资源值")
private String value;
                    /**
 * 资源类型
 */
                @ApiModelProperty(value = "资源类型")
private String type;
                    /**
 * 是否系统资源
 */
                @ApiModelProperty(value = "是否系统资源")
private String isSys;
                                                                                                                                                                                                

}
