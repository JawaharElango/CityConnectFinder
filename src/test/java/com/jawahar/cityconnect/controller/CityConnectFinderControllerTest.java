package com.jawahar.cityconnect.controller;

import com.jawahar.cityconnect.service.CityConnectFinderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
public class CityConnectFinderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityConnectFinderService cityConnectFinderService;

    @Test
    public void testCheckIfCitiesConnected() throws Exception {
        when(cityConnectFinderService.checkIfCitiesConnected("Richmond", "Austin")).thenReturn(true);
        mvc.perform(MockMvcRequestBuilders.get("/connected")
                .param("origin", "Richmond")
                .param("destination", "Austin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("yes"));
    }

    @Test
    public void testCheckIfCitiesConnected_No() throws Exception {
        when(cityConnectFinderService.checkIfCitiesConnected("Austin", "Boston")).thenReturn(false);
        mvc.perform(MockMvcRequestBuilders.get("/connected")
                .param("origin", "New York")
                .param("destination", "Austin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("no"));
    }

}
