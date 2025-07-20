package com.wangjiayue.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangjiayue.domain.entity.User;
import com.wangjiayue.mapper.UserMapper;
import com.wangjiayue.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2025-07-20 18:40:20
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
