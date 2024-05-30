package com.networkDisk.system.domain.bo;

import com.networkDisk.common.core.validate.AddGroup;
import com.networkDisk.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.networkDisk.common.core.domain.BaseEntity;

/**
 * 用户与用户组关联业务对象 sys_user_group
 *
 * @author networkdisk
 * @date 2024-05-29
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserGroupBo extends BaseEntity {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { EditGroup.class })
    private Long userId;

    /**
     * 岗位ID
     */
    @NotNull(message = "岗位ID不能为空", groups = { EditGroup.class })
    private Long groupId;


}
