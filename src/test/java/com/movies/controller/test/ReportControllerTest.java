package com.movies.controller.test;

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

import com.movies.controller.ReportController;
import com.movies.models.Function;
import com.movies.service.ReportService;

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
        function1.setId("1");
        function1.setTicketsSold(100);
        
        Function function2 = new Function();
        function2.setId("2");
        function2.setTicketsSold(80);
        List<Function> topFunctions = Arrays.asList(function1, function2);
        when(reportService.getTopFunctionByTicketsSold(10)).thenReturn(topFunctions);
        mockMvc.perform(get("/api/reports/top")
                .param("top", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].ticketsSold").value(100))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].ticketsSold").value(80));
        verify(reportService, times(1)).getTopFunctionByTicketsSold(10);
    }
}
