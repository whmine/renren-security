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
 * 系统角色
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统角色-修改")
public class SysRoleUpdateRequest extends IDRequest {
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
             * 是否系统角色
             */
                    @ApiModelProperty(value = "是否系统角色")
    private String isSys;
                                /**
             * 层级
             */
                    @ApiModelProperty(value = "层级")
    private BigDecimal level;
                                /**
             * 可否继承
             */
                    @ApiModelProperty(value = "可否继承")
    private String inheritable;
                                /**
             * 继承角色
             */
                    @ApiModelProperty(value = "继承角色")
    private String parentRole;
                                                                                                                                                                                                
}
