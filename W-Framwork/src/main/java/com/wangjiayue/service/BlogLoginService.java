package com.wangjiayue.service;

import com.wangjiayue.domain.ResponseResult;
import com.wangjiayue.domain.entity.User;

public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
