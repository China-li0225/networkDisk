package com.networkDisk.filestorage.service;

import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageVo;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageBo;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * 用户文件存储Service接口
 *
 * @author networkdisk
 * @date 2024-05-30
 */
public interface INetworkdiskUserFilestorageService {

    /**
     * 查询用户文件存储
     */
    NetworkdiskUserFilestorageVo queryById(Long filestorageId);

    /**
     * 查询用户文件存储列表
     */
    TableDataInfo<NetworkdiskUserFilestorageVo> queryPageList(NetworkdiskUserFilestorageBo bo, PageQuery pageQuery);

    /**
     * 查询用户文件存储列表
     */
    List<NetworkdiskUserFilestorageVo> queryList(NetworkdiskUserFilestorageBo bo);

    /**
     * 新增用户文件存储
     */
    Boolean insertByBo(NetworkdiskUserFilestorageBo bo);

    /**
     * 修改用户文件存储
     */
    Boolean updateByBo(NetworkdiskUserFilestorageBo bo);

    /**
     * 校验并批量删除用户文件存储信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void fileDownload(Long filestorageId, HttpServletResponse response) throws IOException;

    Boolean removeToRecyclebin(Long[] filestorageIds);
}
