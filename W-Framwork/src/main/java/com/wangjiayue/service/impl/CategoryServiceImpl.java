package com.wangjiayue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangjiayue.constants.SystemConstants;
import com.wangjiayue.domain.ResponseResult;
import com.wangjiayue.domain.entity.Article;
import com.wangjiayue.domain.entity.Category;
import com.wangjiayue.domain.vo.CategoryVo;
import com.wangjiayue.mapper.CategoryMapper;
import com.wangjiayue.service.ArticleService;
import com.wangjiayue.service.CategoryService;
import com.wangjiayue.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2025-07-15 16:30:13
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        // 查询文章表 状态为已发布的
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        // 获取文章的分类id, 并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        // 查询分类表
        List<Category> categories = listByIds(categoryIds);

        categories = categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()) )
                .collect(Collectors.toList());
        // 封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}
