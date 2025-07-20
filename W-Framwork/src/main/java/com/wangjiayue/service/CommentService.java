package com.wangjiayue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangjiayue.domain.ResponseResult;
import com.wangjiayue.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2025-07-20 17:03:01
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
