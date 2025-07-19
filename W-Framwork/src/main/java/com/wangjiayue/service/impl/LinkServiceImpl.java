package com.wangjiayue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangjiayue.constants.SystemConstants;
import com.wangjiayue.domain.ResponseResult;
import com.wangjiayue.domain.entity.Link;
import com.wangjiayue.domain.vo.LinkVo;
import com.wangjiayue.mapper.LinkMapper;
import com.wangjiayue.service.LinkService;
import com.wangjiayue.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2025-07-19 00:50:14
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {

        // 查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        // 转化成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        // 封装
        return ResponseResult.okResult(linkVos);
    }
}
