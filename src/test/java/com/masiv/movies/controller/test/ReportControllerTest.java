package com.masiv.movies.controller.test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.masiv.movies.controller.ReportController;
import com.masiv.movies.models.Function;
import com.masiv.movies.service.ReportService;

public class ReportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    public void testGetTopFunction() throws Exception {
        Function function1 = new Function();
        function1.setFunctionId(1L);
        function1.setTicketsSold(100L);
        
        Function function2 = new Function();
        function2.setFunctionId(2L);
        function2.setTicketsSold(80L);
        List<Function> topFunctions = Arrays.asList(function1, function2);
        when(reportService.getTopFunctionByTicketsSold(10L)).thenReturn(topFunctions);
        mockMvc.perform(get("/reports/top")
                .param("top", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].functionId").value(1L))
                .andExpect(jsonPath("$[0].ticketsSold").value(100L))
                .andExpect(jsonPath("$[1].functionId").value(2L))
                .andExpect(jsonPath("$[1].ticketsSold").value(80L));
        verify(reportService, times(1)).getTopFunctionByTicketsSold(10L);
    }
}
