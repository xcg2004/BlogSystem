package com.xcg.blogsystem.service.impl;

import com.xcg.blogsystem.domain.po.Category;
import com.xcg.blogsystem.mapper.CategoryMapper;
import com.xcg.blogsystem.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
