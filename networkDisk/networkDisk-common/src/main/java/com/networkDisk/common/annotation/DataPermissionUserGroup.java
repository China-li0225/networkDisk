package com.networkDisk.common.annotation;

import java.lang.annotation.*;

/**
 * 验证用户是否拥有用户组
 * ClassName: DataPermissionUserGroup <br/>
 * Description: <br/>
 * date: 2024/5/24 16:29<br/>
 *
 * @author lixing<br />
 * @since JDK
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermissionUserGroup {
    DataColumnUserGroup[] value();
}
