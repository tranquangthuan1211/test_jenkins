package com.example.demo.controller;


import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class UserControllerTest {

    @Test
    void createUser (){
        log.info("hello anh em");
    }
}
