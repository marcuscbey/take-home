package com.example.foodtrucks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrucksController.class)
public class TrucksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrucksService trucksService;


    @Test
    public void test_null_truck() throws Exception {
        when(trucksService.getTruckById("test")).thenReturn(null);

        this.mockMvc
                .perform(get("/trucks").param("locationId", "test"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test_null_truck_list() throws Exception {
        when(trucksService.getTrucksByBlock("test")).thenReturn(null);

        this.mockMvc
                .perform(get("/trucks/list").param("block", "test"))
                .andExpect(status().isNoContent());
    }
}
