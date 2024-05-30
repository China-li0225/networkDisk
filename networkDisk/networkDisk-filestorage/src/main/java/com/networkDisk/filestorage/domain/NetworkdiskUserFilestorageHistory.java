package com.networkDisk.filestorage.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.networkDisk.common.core.domain.BaseEntity;

/**
 * 用户文件历史记录对象 networkdisk_user_filestorage_history
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("networkdisk_user_filestorage_history")
public class NetworkdiskUserFilestorageHistory extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 回收站主键
     */
    @TableId(value = "history_id")
    private Long historyId;
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
     * URL地址
     */
    private String url;
    /**
     * 文件变动类型
     */
    private String historyType;
    /**
     * 备注
     */
    private String remark;

}
