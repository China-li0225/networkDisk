package com.networkDisk.filestorage.service;

import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageCollectionVo;
import com.networkDisk.filestorage.domain.bo.NetworkdiskUserFilestorageCollectionBo;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户收藏的文件Service接口
 *
 * @author networkdisk
 * @date 2024-05-30
 */
public interface INetworkdiskUserFilestorageCollectionService {

    /**
     * 查询用户收藏的文件
     */
    NetworkdiskUserFilestorageCollectionVo queryById(Long collectionId);

    /**
     * 查询用户收藏的文件列表
     */
    TableDataInfo<NetworkdiskUserFilestorageCollectionVo> queryPageList(NetworkdiskUserFilestorageCollectionBo bo, PageQuery pageQuery);

    /**
     * 查询用户收藏的文件列表
     */
    List<NetworkdiskUserFilestorageCollectionVo> queryList(NetworkdiskUserFilestorageCollectionBo bo);

    /**
     * 新增用户收藏的文件
     */
    Boolean insertByBo(NetworkdiskUserFilestorageCollectionBo bo);

    /**
     * 修改用户收藏的文件
     */
    Boolean updateByBo(NetworkdiskUserFilestorageCollectionBo bo);

    /**
     * 校验并批量删除用户收藏的文件信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
