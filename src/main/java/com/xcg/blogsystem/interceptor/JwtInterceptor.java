package com.xcg.blogsystem.interceptor;


import com.xcg.blogsystem.property.JwtProperties;
import com.xcg.blogsystem.utils.JwtUtils;
import com.xcg.blogsystem.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getHeader());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Long userId =  jwtUtils.parseToken(token);
            // 检查 Redis 中是否存在登录状态
            Boolean isLogin = redisTemplate.hasKey("user:login:" + userId);
            if (Boolean.FALSE.equals(isLogin)) {
                response.setStatus(401);
                log.info("登录状态已失效");
                return false;
            }
            log.info("当前用户id：{}", userId);
            UserHolder.setUserId(Long.valueOf(userId));
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            //ex.printStackTrace();
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserHolder.remove();
    }
}
