package com.xcg.blogsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.xcg.blogsystem.domain.dto.LoginDTO;
import com.xcg.blogsystem.domain.dto.RegisterDTO;
import com.xcg.blogsystem.domain.po.User;
import com.xcg.blogsystem.domain.vo.UserVO;
import com.xcg.blogsystem.result.Result;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XCG
 * @since 2025-05-14
 */
public interface IUserService extends IService<User> {

    Result<String> login(LoginDTO loginDTO);

    Result<String> register(RegisterDTO registerDTO);

    Result<String> logout(HttpServletRequest request);


    Result<List<UserVO>> listVO();

    Result<String> getCode(String email);

    Result<String> updateInfo(User user);
}
