package io.renren.modules.sys.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.IDRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
    import java.io.Serializable;
import java.util.Date;

/**
 * 系统服务依赖
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 10:12:45
 */
@Data
@Api("系统服务依赖-修改")
public class SysServiceDependencyUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 服务ID
             */
                    @ApiModelProperty(value = "服务ID")
    private String sysServiceId;
                                /**
             * 依赖服务ID
             */
                    @ApiModelProperty(value = "依赖服务ID")
    private String sysServiceDependencyId;
                                                                                                                                                                                                
}
