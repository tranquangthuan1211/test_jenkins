package com.example.demo.controller;


import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private UserService userService;

    private UserCreateRequest request;
    private User user;
    @BeforeEach
    public void initData() {
        LocalDate lcd = LocalDate.of(2004, 1, 1); // Thay đổi ngày tháng tùy ý

        request = UserCreateRequest.builder()
                .username("tranquanthuan")
                .password("1234")
                .dbo(lcd) // Thêm giá trị ngày sinh
                .build();

        user = User.builder()
                .id("1234")
                .username("tranquangthuan")
                .dbo(lcd)
                .build();
    }
    @Test
    void createUser() throws Exception {
        // Khởi tạo ObjectMapper và đăng ký module cho LocalDate
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Chuyển đổi request thành JSON string
        String content = objectMapper.writeValueAsString(request);
        log.info("Request Payload: {}", content);

        // Giả lập hành vi của userService
        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(user);

        // Gửi request và kiểm tra phản hồi
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.id").value(user.getId())); // Sử dụng giá trị từ user object
    }

}
