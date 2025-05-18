package com.xcg.blogsystem.mapper;

import com.xcg.blogsystem.domain.po.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XCG
 * @since 2025-05-17
 */
public interface TagMapper extends BaseMapper<Tag> {

    void saveBatch(List<Tag> tagList);
}
