package com.github.syndexmx.socksbase.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.syndexmx.socksbase.controllers.dtos.TestSocksDto.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BatchIncomingSocksControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    IncomingSocksController underTestController;

    @Test
    public void testThatSocksAreAddedInBatch() throws Exception {
        final SocksDto startingSocksDtoToSave = testSocksDto;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String socksJson = objectMapper.writeValueAsString(startingSocksDtoToSave);
        // Initial settings before test
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(socksJson));

        final String batch = "yellow,90,16";

        // Incoming socks and Assertions
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(batch))
                .andExpect(MockMvcResultMatchers.status().isAccepted());

        // Usual incoming test
        final SocksDto expectedSocksDto = testTripleSocksDto;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(socksJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.colour")
                        .value(expectedSocksDto.getColour()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cotton")
                        .value(expectedSocksDto.getCotton()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount")
                        .value(expectedSocksDto.getAmount()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}