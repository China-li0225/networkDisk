package com.networkDisk.demo.mapper;

import com.networkDisk.common.annotation.DataColumn;
import com.networkDisk.common.annotation.DataPermission;
import com.networkDisk.common.core.mapper.BaseMapperPlus;
import com.networkDisk.demo.domain.TestTree;
import com.networkDisk.demo.domain.vo.TestTreeVo;

/**
 * 测试树表Mapper接口
 *
 * @author Lion Li
 * @date 2021-07-26
 */
@DataPermission({
    @DataColumn(key = "deptName", value = "dept_id"),
    @DataColumn(key = "userName", value = "user_id")
})
public interface TestTreeMapper extends BaseMapperPlus<TestTreeMapper, TestTree, TestTreeVo> {

}
