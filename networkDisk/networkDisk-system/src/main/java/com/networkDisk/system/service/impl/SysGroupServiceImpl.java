package com.networkDisk.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.networkDisk.common.utils.StringUtils;
import com.networkDisk.common.core.page.TableDataInfo;
import com.networkDisk.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.networkDisk.system.mapper.SysUserGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.networkDisk.system.domain.bo.SysGroupBo;
import com.networkDisk.system.domain.vo.SysGroupVo;
import com.networkDisk.system.domain.SysGroup;
import com.networkDisk.system.mapper.SysGroupMapper;
import com.networkDisk.system.service.ISysGroupService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户组信息Service业务层处理
 *
 * @author networkdisk
 * @date 2024-05-29
 */
@RequiredArgsConstructor
@Service
public class SysGroupServiceImpl implements ISysGroupService {

    private final SysGroupMapper baseMapper;
    private final SysUserGroupMapper userGroupMapper;

    /**
     * 查询用户组信息
     */
    @Override
    public SysGroupVo queryById(Long groupId){
        return baseMapper.selectVoById(groupId);
    }

    /**
     * 查询用户组信息列表
     */
    @Override
    public TableDataInfo<SysGroupVo> queryPageList(SysGroupBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysGroup> lqw = buildQueryWrapper(bo);
        Page<SysGroupVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户组信息列表
     */
    @Override
    public List<SysGroupVo> queryList(SysGroupBo bo) {
        LambdaQueryWrapper<SysGroup> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysGroup> buildQueryWrapper(SysGroupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getGroupCode()), SysGroup::getGroupCode, bo.getGroupCode());
        lqw.like(StringUtils.isNotBlank(bo.getGroupName()), SysGroup::getGroupName, bo.getGroupName());
        lqw.eq(bo.getGroupSort() != null, SysGroup::getGroupSort, bo.getGroupSort());
        lqw.eq(bo.getGroupLevel() != null, SysGroup::getGroupLevel, bo.getGroupLevel());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SysGroup::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增用户组信息
     */
    @Override
    public Boolean insertByBo(SysGroupBo bo) {
        SysGroup add = BeanUtil.toBean(bo, SysGroup.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setGroupId(add.getGroupId());
        }
        return flag;
    }

    /**
     * 修改用户组信息
     */
    @Override
    public Boolean updateByBo(SysGroupBo bo) {
        SysGroup update = BeanUtil.toBean(bo, SysGroup.class);
        validEntityBeforeSave(update);//数据效验
        return baseMapper.updateById(update) > 0;
    }

    @Override
    public Boolean changeStatusBo(SysGroupBo bo) {
        SysGroup changeStatusBo = BeanUtil.toBean(bo, SysGroup.class);
        validEntityBeforeSave(changeStatusBo);//数据效验
        return baseMapper.updateById(changeStatusBo) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysGroup entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户组信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 查询用户用户组列表
     * @param group
     * @return
     */
    @Override
    public List<SysGroup> selectGroupList(SysGroup group) {
        LambdaQueryWrapper<SysGroup> SysGroups = new LambdaQueryWrapper<>(group);
        return baseMapper.selectList(SysGroups);
    }

    @Override
    public List<Long> selectGroupListByUserId(Long userId) {
        return userGroupMapper.selectGroupListByUserId(userId);
    }
}
