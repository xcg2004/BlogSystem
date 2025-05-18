package com.xcg.blogsystem.service;

import com.xcg.blogsystem.domain.dto.ArticleDTO;
import com.xcg.blogsystem.domain.po.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xcg.blogsystem.result.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XCG
 * @since 2025-05-17
 */
public interface IArticleService extends IService<Article> {

    Result<String> publish(ArticleDTO articleDTO);

    Result<String> saveArticle(ArticleDTO articleDTO);
}
