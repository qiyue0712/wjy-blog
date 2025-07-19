package com.wangjiayue.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;

    private Long categoryId;
    //所属分类id
    private String categoryName;
    //缩略图
    private String thumbnail;
    // 内容
    private String content;
    //访问量
    private Long viewCount;

    private Date createTime;
}
