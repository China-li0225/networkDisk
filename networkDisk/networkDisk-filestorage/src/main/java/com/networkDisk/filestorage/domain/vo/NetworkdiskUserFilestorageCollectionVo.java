package com.networkDisk.filestorage.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.networkDisk.common.annotation.ExcelDictFormat;
import com.networkDisk.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * 用户收藏的文件视图对象 networkdisk_user_filestorage_collection
 *
 * @author networkdisk
 * @date 2024-05-30
 */
@Data
@ExcelIgnoreUnannotated
public class NetworkdiskUserFilestorageCollectionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏表主键
     */
    @ExcelProperty(value = "收藏表主键")
    private Long collectionId;

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
     * 文件变动类型
     */
    @ExcelProperty(value = "文件变动类型")
    private String historyType;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
