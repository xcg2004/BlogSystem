package com.xcg.blogsystem.service.impl;

import com.xcg.blogsystem.domain.po.Comment;
import com.xcg.blogsystem.mapper.CommentMapper;
import com.xcg.blogsystem.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
