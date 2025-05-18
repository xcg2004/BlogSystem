package com.xcg.blogsystem.service.impl;

import com.xcg.blogsystem.domain.dto.ArticleDTO;
import com.xcg.blogsystem.domain.po.Article;
import com.xcg.blogsystem.domain.po.ArticleTag;
import com.xcg.blogsystem.domain.po.Tag;
import com.xcg.blogsystem.enums.ArticleStatusEnum;
import com.xcg.blogsystem.mapper.ArticleMapper;
import com.xcg.blogsystem.mapper.ArticleTagMapper;
import com.xcg.blogsystem.mapper.TagMapper;
import com.xcg.blogsystem.result.Result;
import com.xcg.blogsystem.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcg.blogsystem.utils.UserHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XCG
 * @since 2025-05-17
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;



    @Override
    @Transactional
    public Result<String> publish(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO,article);
        //获取userId
        Long userId = UserHolder.getUserId();
        article.setUserId(userId);
        article.setStatus(ArticleStatusEnum.UnderReview.value);//审核中

        return insert(article,articleDTO.getTag());
    }

    @Transactional
    @Override
    public Result<String> saveArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO,article);
        //获取userId
        Long userId = UserHolder.getUserId();
        article.setUserId(userId);
        article.setStatus(ArticleStatusEnum.Draft.value);//草稿

        return insert(article,articleDTO.getTag());
    }

    private Result<String> insert(Article article,List<String> tagsList){
        //新增文章
        articleMapper.insert(article);

        //如果有tag
        //1.插入tag表
        List<Tag> tags = tagsList.stream().map(tagName -> Tag.builder().name(tagName).build()).toList();
        tagMapper.saveBatch(tags);
        //2.插入article_tag表
        Long articleId = article.getId();
        tags.forEach(tag -> {
            Long tagId = tag.getId();
            ArticleTag articleTag = ArticleTag.builder()
                    .articleId(articleId)
                    .tagId(tagId).build();
            articleTagMapper.insert(articleTag);
        });

        return Result.success();
    }
}
