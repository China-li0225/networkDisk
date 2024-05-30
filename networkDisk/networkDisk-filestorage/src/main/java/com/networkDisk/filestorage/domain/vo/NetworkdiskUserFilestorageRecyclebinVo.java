package com.networkDisk.filestorage.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.networkDisk.common.annotation.ExcelDictFormat;
import com.networkDisk.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 用户文件存储视图对象 networkdisk_user_filestorage_recyclebin
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Data
@ExcelIgnoreUnannotated
public class NetworkdiskUserFilestorageRecyclebinVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 回收站主键
     */
    @ExcelProperty(value = "回收站主键")
    private Long expirationId;

    /**
     * 文件存储主键
     */
    @ExcelProperty(value = "文件存储主键")
    private Long filestorageId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 镜像存储id
     */
    @ExcelProperty(value = "镜像存储id")
    private Long ossId;

    /**
     * 文件名
     */
    @ExcelProperty(value = "文件名")
    private String fileName;

    /**
     * 原名
     */
    @ExcelProperty(value = "原名")
    private String originalName;

    /**
     * 文件后缀名
     */
    @ExcelProperty(value = "文件后缀名")
    private String fileSuffix;

    /**
     * URL地址
     */
    @ExcelProperty(value = "URL地址")
    private String url;

    /**
     * 文件回收有效期
     */
    @ExcelProperty(value = "文件回收有效期")
    private Long fileExpirationDate;


}
