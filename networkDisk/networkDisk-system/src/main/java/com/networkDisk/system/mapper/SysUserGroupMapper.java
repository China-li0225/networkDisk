package com.networkDisk.system.mapper;

import com.networkDisk.system.domain.SysUserGroup;
import com.networkDisk.system.domain.vo.SysUserGroupVo;
import com.networkDisk.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与用户组关联Mapper接口
 *
 * @author networkdisk
 * @date 2024-05-29
 */
public interface SysUserGroupMapper extends BaseMapperPlus<SysUserGroupMapper, SysUserGroup, SysUserGroupVo> {

    List<Long> selectGroupListByUserId(@Param("userId") Long userId);
}
