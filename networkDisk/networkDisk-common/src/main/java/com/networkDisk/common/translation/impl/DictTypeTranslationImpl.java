package com.networkDisk.common.translation.impl;

import com.networkDisk.common.annotation.TranslationType;
import com.networkDisk.common.constant.TransConstant;
import com.networkDisk.common.core.service.DictService;
import com.networkDisk.common.translation.TranslationInterface;
import com.networkDisk.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 字典翻译实现
 *
 * @author Lion Li
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.DICT_TYPE_TO_LABEL)
public class DictTypeTranslationImpl implements TranslationInterface<String> {

    private final DictService dictService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof String && StringUtils.isNotBlank(other)) {
            return dictService.getDictLabel(other, key.toString());
        }
        return null;
    }
}
