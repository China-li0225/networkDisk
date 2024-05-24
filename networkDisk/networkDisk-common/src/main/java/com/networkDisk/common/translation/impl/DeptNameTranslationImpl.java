package com.networkDisk.common.translation.impl;

import com.networkDisk.common.annotation.TranslationType;
import com.networkDisk.common.constant.TransConstant;
import com.networkDisk.common.core.service.DeptService;
import com.networkDisk.common.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.DEPT_ID_TO_NAME)
public class DeptNameTranslationImpl implements TranslationInterface<String> {

    private final DeptService deptService;
    
    @Override
    public String translation(Object key, String other) {
        return deptService.selectDeptNameByIds(key.toString());
    }
}
