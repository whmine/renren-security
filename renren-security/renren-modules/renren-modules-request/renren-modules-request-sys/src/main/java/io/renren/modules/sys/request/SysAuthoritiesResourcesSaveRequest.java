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
 * 系统权限资源关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统权限资源关系-保存")
public class SysAuthoritiesResourcesSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 系统权限
 */
                @ApiModelProperty(value = "系统权限")
private String sysAuthoritiesId;
                    /**
 * 系统资源
 */
                @ApiModelProperty(value = "系统资源")
private String sysResourcesId;
                                                                                                                                                                                                

}
