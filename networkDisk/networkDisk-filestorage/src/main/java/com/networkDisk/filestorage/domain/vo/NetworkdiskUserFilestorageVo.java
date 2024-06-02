package com.networkDisk.filestorage.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.networkDisk.common.annotation.ExcelDictFormat;
import com.networkDisk.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 用户文件存储视图对象 networkdisk_user_filestorage
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Data
@ExcelIgnoreUnannotated
public class NetworkdiskUserFilestorageVo implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 文件大小
     */
    @ExcelProperty(value = "文件大小")
    private Double fileSize;

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


}
