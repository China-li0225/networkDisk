package com.networkDisk.system.service;

import com.networkDisk.system.domain.SysUserGroup;
import com.networkDisk.system.domain.vo.SysUserGroupVo;
import com.networkDisk.system.domain.bo.SysUserGroupBo;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户与用户组关联Service接口
 *
 * @author networkdisk
 * @date 2024-05-29
 */
public interface ISysUserGroupService {

    /**
     * 查询用户与用户组关联
     */
    SysUserGroupVo queryById(Long userId);

    /**
     * 查询用户与用户组关联列表
     */
    TableDataInfo<SysUserGroupVo> queryPageList(SysUserGroupBo bo, PageQuery pageQuery);

    /**
     * 查询用户与用户组关联列表
     */
    List<SysUserGroupVo> queryList(SysUserGroupBo bo);

    /**
     * 新增用户与用户组关联
     */
    Boolean insertByBo(SysUserGroupBo bo);

    /**
     * 修改用户与用户组关联
     */
    Boolean updateByBo(SysUserGroupBo bo);

    /**
     * 校验并批量删除用户与用户组关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
