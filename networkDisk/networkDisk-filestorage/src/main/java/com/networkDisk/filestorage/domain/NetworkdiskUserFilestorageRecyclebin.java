package com.networkDisk.filestorage.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.networkDisk.common.core.validate.AddGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.networkDisk.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 用户文件存储对象 networkdisk_user_filestorage_recyclebin
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("networkdisk_user_filestorage_recyclebin")
public class NetworkdiskUserFilestorageRecyclebin extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 回收站主键
     */
    @TableId(value = "expiration_id")
    private Long expirationId;
    /**
     * 文件存储主键
     */
    private Long filestorageId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 镜像存储id
     */
    private Long ossId;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 原名
     */
    private String originalName;
    /**
     * 文件后缀名
     */
    private String fileSuffix;
    /**
     * 文件大小
     */
    private Double fileSize;
    /**
     * URL地址
     */
    private String url;
    /**
     * 文件回收有效期
     */
    private Long fileExpirationDate;

}
