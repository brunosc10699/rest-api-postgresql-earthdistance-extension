package com.bruno.distancecalculator.resources;

import com.bruno.distancecalculator.services.impl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CityResourceTest {

    private static final String URN = "/api/v1/cities";

    private static final double MILE = 1.60934;

    @Mock
    private CityServiceImpl cityService;

    @InjectMocks
    private CityResource cityResource;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cityResource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("(1) Should return 200 OK status")
    void whenGivingTheCitiesIdsReturn200OkStatus() throws Exception {
        Long from = 1L;
        Long to = 2L;
        double distance = 10.0;
        double distanceInKm = distance * MILE;
        String result = String.format("The distance between the given cities is %.2f Km", distanceInKm);
        when(cityService.getDistance(from, to)).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.get(URN + "/distance?from=" + from + "&to=" + to))
                .andExpect(status().isOk());
    }
}
