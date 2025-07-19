package com.wangjiayue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangjiayue.domain.ResponseResult;
import com.wangjiayue.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2025-07-15 16:30:12
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
