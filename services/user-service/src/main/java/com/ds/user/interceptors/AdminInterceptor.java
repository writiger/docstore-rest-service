package com.ds.user.interceptors;

import com.ds.common.enums.UserLevel;
import com.ds.user.domain.po.User;
import com.ds.user.mapper.UserMapper;
import com.ds.user.service.IUserService;
import com.ds.user.service.impl.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;
import static com.ds.common.constants.Constants.AUTH_KEY;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-17 16:24
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    public UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader(AUTH_KEY);

        Long id = Long.parseLong(userId);
        UserLevel userLevel;
        try{
            User user = userMapper.selectById(id);
            userLevel  = user.getLevel();
        }catch (Exception e){
            return false;
        }
        //非普通用户可以访问
        return userLevel != UserLevel.NORMAL;
    }
}
