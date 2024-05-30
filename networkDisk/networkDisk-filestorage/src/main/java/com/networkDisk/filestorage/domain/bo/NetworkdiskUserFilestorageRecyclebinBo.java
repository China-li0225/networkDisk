package com.networkDisk.filestorage.domain.bo;

import com.networkDisk.common.core.validate.AddGroup;
import com.networkDisk.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.networkDisk.common.core.domain.BaseEntity;

/**
 * 用户文件存储业务对象 networkdisk_user_filestorage_recyclebin
 *
 * @author networkdisk
 * @date 2024-05-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class NetworkdiskUserFilestorageRecyclebinBo extends BaseEntity {

    /**
     * 回收站主键
     */
    @NotNull(message = "回收站主键不能为空", groups = { EditGroup.class })
    private Long expirationId;

    /**
     * 文件存储主键
     */
    @NotNull(message = "文件存储主键不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long filestorageId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 镜像存储id
     */
    @NotNull(message = "镜像存储id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long ossId;

    /**
     * 文件名
     */
    @NotBlank(message = "文件名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileName;

    /**
     * 原名
     */
    @NotBlank(message = "原名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String originalName;

    /**
     * 文件后缀名
     */
    @NotBlank(message = "文件后缀名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileSuffix;

    /**
     * URL地址
     */
    @NotBlank(message = "URL地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String url;

    /**
     * 文件回收有效期
     */
    @NotNull(message = "文件回收有效期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fileExpirationDate;


}
