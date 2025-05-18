package com.xcg.blogsystem;

import com.xcg.blogsystem.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
public class BlogSystemApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmail(){
        int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        emailService.sendSimpleEmail("1071097001@qq.com", "验证码", String.valueOf(code));
    }

}
