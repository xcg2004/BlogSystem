package com.xcg.blogsystem.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xcg.blogsystem.domain.dto.ChangePwdDTO;
import com.xcg.blogsystem.domain.dto.LoginDTO;
import com.xcg.blogsystem.domain.dto.RegisterDTO;
import com.xcg.blogsystem.domain.po.User;
import com.xcg.blogsystem.domain.vo.UserVO;
import com.xcg.blogsystem.mapper.UserMapper;
import com.xcg.blogsystem.result.Result;
import com.xcg.blogsystem.service.EmailService;
import com.xcg.blogsystem.service.IUserService;
import com.xcg.blogsystem.utils.JwtUtils;
import com.xcg.blogsystem.utils.Md5Util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XCG
 * @since 2025-05-14
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EmailService emailService;

    @Override
    public Result<String> login(LoginDTO loginDTO) {
        // 校验验证码
        String code = redisTemplate.opsForValue().get("code:" + loginDTO.getEmail());
        if(code == null || !code.equals(loginDTO.getCode())){
            return Result.error("验证码错误");
        }

        //查询是否存在用户
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(true,User::getUsername, loginDTO.getUsername())
                        .eq(true,User::getEmail,loginDTO.getEmail())
        );

        //用户名不存在
        if(user == null){
            return Result.error("用户名不存在");
        }

        //密码错误
        if(!user.getPassword().equals(Md5Util.md5(loginDTO.getPassword()))){
            return Result.error("密码错误");
        }

        String token = redisTemplate.opsForValue().get("user:login:" + user.getId());
        if(token == null || token.isBlank()){
            //获取userId，生成token
            Long userId = user.getId();
            token = jwtUtils.generateToken(user.getId());
            //redis存入该用户状态 ttl=token
            redisTemplate.opsForValue().setIfAbsent(
                    "user:login:"+user.getId(),
                    token ,
                    15,
                    TimeUnit.MINUTES);

        }
        //登录成功，删除redis中的验证码
        redisTemplate.delete("code:" + loginDTO.getEmail());
        return Result.success(token);
    }

    @Override
    public Result<String> updateInfo(User user) {
        if(userMapper.updateById(user) == 1){
            return Result.success();
        }
        return Result.error("更新失败");
    }

    @Override
    public Result<String> getCode(String email) {
        int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        log.debug("验证码:"+code);

        emailService.sendSimpleEmail(email,"博客系统邮箱验证", String.valueOf(code));
        redisTemplate.opsForValue().setIfAbsent(
                "code:"+email,
                String.valueOf(code),
                5,
                TimeUnit.MINUTES);
        return Result.success("验证码发送成功，5分钟内有效");
    }

    @Override
    @Transactional
    public Result<String> register(RegisterDTO registerDTO) {
        //校验验证码
        String code = redisTemplate.opsForValue().get("code:" + registerDTO.getEmail());
        if(code == null || !code.equals(registerDTO.getCode())){
            return Result.error("验证码错误");
        }

        //1.注册
        User user = new User();
        BeanUtils.copyProperties(registerDTO,user);
        user.setPassword(Md5Util.md5(registerDTO.getPassword()));
        userMapper.insert(user);

        //2.删除验证码
        redisTemplate.delete("code:" + registerDTO.getEmail());
        return Result.success();
    }

    @Override
    public Result<String> logout(HttpServletRequest request) {
        //获取用户id
        String token = request.getHeader("token");
        if(token == null || token.isEmpty()){
            return Result.error("token不存在或无效");
        }

        //解析用户id
        Long userId = jwtUtils.parseToken(token);

        //幂等，检验是否已经注销
        Boolean b = redisTemplate.hasKey("user:login:" + userId);

        if(b.equals(Boolean.FALSE)){
            return Result.success("注销成功");
        }

        //存在，删除key
        redisTemplate.delete("user:login:" + userId);

        return Result.success("注销成功");
    }

    @Override
    public Result<List<UserVO>> listVO() {
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>());
        List<UserVO> userVOS = users.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user,userVO);
            return userVO;
        }).toList();
        return Result.success(userVOS);
    }

    @Override
    public Result<String> updatePwd(ChangePwdDTO changePwdDTO) {
        //1.查询用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, changePwdDTO.getUsername()));
        if(user == null){
            return Result.error("用户名不存在");
        }
        boolean flag = user.getPassword().equals(Md5Util.md5(changePwdDTO.getOldPassword()));
        if(!flag){
            return Result.error("旧密码错误");
        }
        user.setPassword(Md5Util.md5(changePwdDTO.getNewPassword()));
        userMapper.updateById(user);
        return Result.success("修改密码成功");
    }
}
