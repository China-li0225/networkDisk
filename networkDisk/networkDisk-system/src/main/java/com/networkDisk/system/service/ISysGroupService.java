package com.networkDisk.system.service;

import com.networkDisk.system.domain.SysGroup;
import com.networkDisk.system.domain.vo.SysGroupVo;
import com.networkDisk.system.domain.bo.SysGroupBo;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户组信息Service接口
 *
 * @author networkdisk
 * @date 2024-05-29
 */
public interface ISysGroupService {

    /**
     * 查询用户组信息
     */
    SysGroupVo queryById(Long groupId);

    /**
     * 查询用户组信息列表
     */
    TableDataInfo<SysGroupVo> queryPageList(SysGroupBo bo, PageQuery pageQuery);

    /**
     * 查询用户组信息列表
     */
    List<SysGroupVo> queryList(SysGroupBo bo);

    /**
     * 新增用户组信息
     */
    Boolean insertByBo(SysGroupBo bo);

    /**
     * 修改用户组信息
     */
    Boolean updateByBo(SysGroupBo bo);

    /**
     * 修改用户组状态
     */
    Boolean changeStatusBo(SysGroupBo bo);

    /**
     * 校验并批量删除用户组信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<SysGroup> selectGroupList(SysGroup group);

    List<Long> selectGroupListByUserId(Long userId);
}
