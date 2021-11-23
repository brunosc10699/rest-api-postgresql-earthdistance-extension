package com.bruno.distancecalculator.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

import static com.bruno.distancecalculator.utils.JsonConversionUtil.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AppStatusResourceTest {

    @InjectMocks
    private AppStatusResource appStatusResource;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(appStatusResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("(1) Should return 200 OK status")
    void whenGETAppStatusThenReturn200OKStatus() throws Exception{
        ResponseEntity<Map<String, Object>> status = appStatusResource.getAppStatus();
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(status)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service", is(equalTo("Distance Calculation API"))))
                .andExpect(jsonPath("$.status", is(equalTo("UP"))))
                .andExpect(jsonPath("$.statusCode", is(equalTo(200))));
    }
}
