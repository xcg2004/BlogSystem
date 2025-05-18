package com.xcg.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xcg.blogsystem.domain.po.Permission;
import com.xcg.blogsystem.domain.po.User;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XCG
 * @since 2025-05-14
 */
public interface UserMapper extends BaseMapper<User> {

    List<Permission> selectPermissions(Long userId);
}
