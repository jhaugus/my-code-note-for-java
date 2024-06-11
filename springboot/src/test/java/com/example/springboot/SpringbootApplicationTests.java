package com.example.springboot;

import com.example.springboot.domain.User;
import com.example.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootApplicationTests {

    @Resource
    private UserService userService;


    @Test
    void UserTest() {
        User user = new User();
        user.setUseraccount("root");
        user.setUserpassword("root");
        user.setUsername("root");
        user.setUseravatar(null);

        userService.save(user);
    }

}
