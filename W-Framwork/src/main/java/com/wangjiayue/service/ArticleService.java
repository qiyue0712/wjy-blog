package com.wangjiayue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangjiayue.domain.ResponseResult;
import com.wangjiayue.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
