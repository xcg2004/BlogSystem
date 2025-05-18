package com.xcg.blogsystem.aspect;


import com.xcg.blogsystem.annotation.AuthorizationAnnotation;
import com.xcg.blogsystem.domain.po.Permission;
import com.xcg.blogsystem.mapper.UserMapper;
import com.xcg.blogsystem.utils.UserHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Aspect
public class AdminAspect {
    @Autowired
    private UserMapper userMapper;

    @Pointcut("@annotation(com.xcg.blogsystem.annotation.AuthorizationAnnotation)")
    public void adminAnnotation(){}

    @Before("@annotation(authorizationAnnotation)")
    public void before(AuthorizationAnnotation authorizationAnnotation) {
        String value = authorizationAnnotation.value();
        if(Objects.equals(value, "user")){
            // 不需要管理员权限
            return;
        }
        //true => 需要管理员权限
        Long userId = UserHolder.getUserId();

        if(userId == null){
            throw new RuntimeException("请先登录");
        }

        //否则查询该用户的权限
        List<Permission> permissionList = userMapper.selectPermissions(userId);
        boolean hasAdminPermission = false;
        for (Permission permission : permissionList) {
            if ("管理员权限".equals(permission.getName())) {
                hasAdminPermission = true;
                break;
            }
        }

        if (!hasAdminPermission) {
            throw new RuntimeException("权限不足");
        }
    }
}
