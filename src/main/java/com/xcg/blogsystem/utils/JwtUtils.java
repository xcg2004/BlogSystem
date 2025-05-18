package com.xcg.blogsystem.utils;

import cn.hutool.core.map.MapUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.xcg.blogsystem.property.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtils {
    private static final String USERID  = "userId";

    @Autowired
    private JwtProperties jwtProperties;

    public String generateToken(Long userId) {
        Map<String,Object> payload = MapUtil.of(USERID, userId);
//        return JWTUtil.createToken(payload, "blogsystem".getBytes());
        return JWTUtil.createToken(payload, jwtProperties.getSecretKey().getBytes());
    }

    public Long parseToken(String token) {

        Object userId = JWTUtil.parseToken(token).getPayload(USERID);
        return Long.valueOf(userId.toString());
    }

    public static void main(String[] args) {
        JwtUtils jwtUtils = new JwtUtils();
       // String token = jwtUtils.generateToken(1L);
        Long userId = jwtUtils.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.tm3RXWjxD6ItmdrDlJuNvzdn5dUJMVczl2KGjZotR9E");
        System.out.println(userId);
        //   System.out.println(token);
    }
}
