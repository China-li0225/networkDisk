package com.networkDisk.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.networkDisk.common.core.domain.BaseEntity;

/**
 * 用户与用户组关联对象 sys_user_group
 *
 * @author networkdisk
 * @date 2024-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_group")
public class SysUserGroup extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long userId;
    /**
     * 岗位ID
     */
    private Long groupId;

}
