package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.dto.AddUserRequest;
import com.estsoft.blogjpa.domain.User;
import com.estsoft.blogjpa.repository.UserRepository;
import com.estsoft.blogjpa.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest            // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ac;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ac).build();
    }

    @DisplayName("멤버 저장")
    @Test
    public void signup() throws Exception {
        // given
        AddUserRequest request = new AddUserRequest("mock_email", "mock_pw");

        // when
        ResultActions response = mockMvc.perform(post("/user")
                        .param("email", request.getEmail())
                        .param("password", request.getPassword()));// 1
        // then
        response.andExpect(status().is3xxRedirection());
        User byEmail = userRepository.findByEmail(request.getEmail()).orElseThrow();
        Assertions.assertNotNull(byEmail);
    }
}