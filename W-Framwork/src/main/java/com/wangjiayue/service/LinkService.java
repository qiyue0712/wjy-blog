package com.wangjiayue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangjiayue.domain.ResponseResult;
import com.wangjiayue.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2025-07-19 00:50:14
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
