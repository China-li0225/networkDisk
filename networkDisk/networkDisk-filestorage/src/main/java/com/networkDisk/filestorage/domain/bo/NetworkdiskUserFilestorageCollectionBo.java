package com.networkDisk.filestorage.domain.bo;

import com.networkDisk.common.core.validate.AddGroup;
import com.networkDisk.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.networkDisk.common.core.domain.BaseEntity;

/**
 * 用户收藏的文件业务对象 networkdisk_user_filestorage_collection
 *
 * @author networkdisk
 * @date 2024-05-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class NetworkdiskUserFilestorageCollectionBo extends BaseEntity {

    /**
     * 收藏表主键
     */
    @NotNull(message = "收藏表主键不能为空", groups = { EditGroup.class })
    private Long collectionId;

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
     * 文件变动类型
     */
    @NotBlank(message = "文件变动类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String historyType;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
