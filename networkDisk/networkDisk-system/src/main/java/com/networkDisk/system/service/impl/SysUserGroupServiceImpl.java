package com.networkDisk.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.networkDisk.common.utils.StringUtils;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.networkDisk.system.domain.bo.SysUserGroupBo;
import com.networkDisk.system.domain.vo.SysUserGroupVo;
import com.networkDisk.system.domain.SysUserGroup;
import com.networkDisk.system.mapper.SysUserGroupMapper;
import com.networkDisk.system.service.ISysUserGroupService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户与用户组关联Service业务层处理
 *
 * @author networkdisk
 * @date 2024-05-29
 */
@RequiredArgsConstructor
@Service
public class SysUserGroupServiceImpl implements ISysUserGroupService {

    private final SysUserGroupMapper baseMapper;

    /**
     * 查询用户与用户组关联
     */
    @Override
    public SysUserGroupVo queryById(Long userId){
        return baseMapper.selectVoById(userId);
    }

    /**
     * 查询用户与用户组关联列表
     */
    @Override
    public TableDataInfo<SysUserGroupVo> queryPageList(SysUserGroupBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysUserGroup> lqw = buildQueryWrapper(bo);
        Page<SysUserGroupVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户与用户组关联列表
     */
    @Override
    public List<SysUserGroupVo> queryList(SysUserGroupBo bo) {
        LambdaQueryWrapper<SysUserGroup> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysUserGroup> buildQueryWrapper(SysUserGroupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysUserGroup> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增用户与用户组关联
     */
    @Override
    public Boolean insertByBo(SysUserGroupBo bo) {
        SysUserGroup add = BeanUtil.toBean(bo, SysUserGroup.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    /**
     * 修改用户与用户组关联
     */
    @Override
    public Boolean updateByBo(SysUserGroupBo bo) {
        SysUserGroup update = BeanUtil.toBean(bo, SysUserGroup.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysUserGroup entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户与用户组关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
