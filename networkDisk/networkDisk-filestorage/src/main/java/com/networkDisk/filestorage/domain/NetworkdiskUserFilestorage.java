package com.networkDisk.filestorage.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.networkDisk.common.core.domain.BaseEntity;

/**
 * 用户文件存储对象 networkdisk_user_filestorage
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("networkdisk_user_filestorage")
public class NetworkdiskUserFilestorage extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文件存储主键
     */
    @TableId(value = "filestorage_id")
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
     * 文件后缀名
     */
    private Double fileSize;
    /**
     * URL地址
     */
    private String url;

}
