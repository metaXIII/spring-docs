package com.metaxiii.fr.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerITTest {

    @Autowired
    private MockMvc mock;

    @Test
    void shouldReturnHelloWorldWhenNoParameter() throws Exception {
        this.mock.perform(get("/greeting"))
                .andDo(print())
                .andExpect(content().string(containsString("Hello World!")))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    void shouldReturnHelloNameWhenParameterIsProvided() throws Exception {
        this.mock.perform(get("/greeting?name=meta"))
                .andDo(print())
                .andExpect(content().string(containsString("Hello meta!")))
                .andExpect(status().isOk())
                .andReturn();
    }
}