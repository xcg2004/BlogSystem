package com.xcg.blogsystem.controller;


import com.xcg.blogsystem.annotation.AuthorizationAnnotation;
import com.xcg.blogsystem.domain.dto.ChangePwdDTO;
import com.xcg.blogsystem.domain.dto.LoginDTO;
import com.xcg.blogsystem.domain.dto.RegisterDTO;
import com.xcg.blogsystem.domain.po.User;
import com.xcg.blogsystem.domain.vo.UserVO;
import com.xcg.blogsystem.result.Result;
import com.xcg.blogsystem.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XCG
 * @since 2025-05-12
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO){
        log.info("用户登录：{}",loginDTO);
        return userService.login(loginDTO);
    }

    /**
     * 注册
     * @param registerDTO
     * @return
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDTO registerDTO){
        log.info("用户注册：{}",registerDTO);
        return userService.register(registerDTO);
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){
        log.info("用户注销：{}");
        return userService.logout(request);
    }

    /**
     * 查询用户列表(需要管理员权限)
     * @return
     */
    @GetMapping("/list")
    @AuthorizationAnnotation(value = "admin")
    public Result<List<UserVO>> list(){
        log.info("查询用户列表");
        return userService.listVO();
    }

    @GetMapping("/code")
    public Result<String> getCode(String email){
        log.info("获取邮箱验证码：{}",email);
        return userService.getCode(email);
    }

    @PutMapping("/info")
    public Result<String> updateInfo(@RequestBody User user){
        log.info("更新用户信息：{}",user);
        return userService.updateInfo(user);
    }

    @PutMapping("/password")
    public Result<String> updatePassword(@RequestBody ChangePwdDTO changePwdDTO){
        log.info("更新用户密码：{}",changePwdDTO);
        return userService.updatePwd(changePwdDTO);
    }



}
