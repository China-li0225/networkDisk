package com.networkDisk.filestorage.mapper;

import com.networkDisk.filestorage.domain.NetworkdiskUserFilestorageRecyclebin;
import com.networkDisk.filestorage.domain.vo.NetworkdiskUserFilestorageRecyclebinVo;
import com.networkDisk.common.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 用户文件存储Mapper接口
 *
 * @author networkdisk
 * @date 2024-05-30
 */
public interface NetworkdiskUserFilestorageRecyclebinMapper extends BaseMapperPlus<NetworkdiskUserFilestorageRecyclebinMapper, NetworkdiskUserFilestorageRecyclebin, NetworkdiskUserFilestorageRecyclebinVo> {

    List<NetworkdiskUserFilestorageRecyclebin> gettimeoutFile();
}
