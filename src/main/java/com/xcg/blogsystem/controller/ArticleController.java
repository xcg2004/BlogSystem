package com.xcg.blogsystem.controller;


import com.xcg.blogsystem.domain.dto.ArticleDTO;
import com.xcg.blogsystem.result.Result;
import com.xcg.blogsystem.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XCG
 * @since 2025-05-17
 */
@RestController
@RequestMapping("/api/article")
@Slf4j
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    /**
     * 发布文章
     * @param articleDTO
     * @return
     */
    @PostMapping("/publish")
    public Result<String> publicArticle(@RequestBody ArticleDTO articleDTO){
        log.info("发布文章：{}",articleDTO);
        return articleService.publish(articleDTO);
    }

    /**
     * 暂存不发布
     * @param articleDTO
     * @return
     */
    @PostMapping("/save")
    public Result<String> saveArticle(@RequestBody ArticleDTO articleDTO){
        log.info("暂存文章：{}",articleDTO);
        return articleService.saveArticle(articleDTO);
    }




}
