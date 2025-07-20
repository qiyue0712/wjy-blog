package com.wangjiayue.service.impl;

import com.wangjiayue.domain.ResponseResult;
import com.wangjiayue.domain.entity.LoginUser;
import com.wangjiayue.domain.entity.User;
import com.wangjiayue.domain.vo.BlogUserLoginVo;
import com.wangjiayue.domain.vo.UserInfoVo;
import com.wangjiayue.service.BlogLoginService;
import com.wangjiayue.utils.BeanCopyUtils;
import com.wangjiayue.utils.JwtUtil;
import com.wangjiayue.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 判断认证是否通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        // 获取 userid  生成 token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        // 把用户信息存入 redis
        redisCache.setCacheObject("bloglogin:"+userId, loginUser);

        // 把 token 和 userinfo 封装返回
        // 把 User 转换成 UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);
        return  ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {

        // 获取 token 解析获取 userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取 userid
        Long userId = loginUser.getUser().getId();
        // 删除 redis 中的用户信息
        redisCache.deleteObject("bloglogin:" + userId);
        return ResponseResult.okResult();
    }
}
