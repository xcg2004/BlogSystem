package com.xcg.blogsystem.service.impl;

import com.xcg.blogsystem.domain.po.Tag;
import com.xcg.blogsystem.mapper.TagMapper;
import com.xcg.blogsystem.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XCG
 * @since 2025-05-17
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
