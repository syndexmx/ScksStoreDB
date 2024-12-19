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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.syndexmx.socksbase.controllers.dtos.TestSocksDto.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class IncomingSocksControllerITest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    IncomingSocksController underTestController;

    @Test
    public void testThatSocksAreCreated() throws Exception {
        final SocksDto socksDtoToSave = testSocksDto;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String socksJson = objectMapper.writeValueAsString(socksDtoToSave);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(socksJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.colour")
                        .value(socksDtoToSave.getColour()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cotton")
                        .value(socksDtoToSave.getCotton()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount")
                        .value(socksDtoToSave.getAmount()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatSocksAreAdded() throws Exception {
        final SocksDto startingSocksDtoToSave = testSocksDto;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String socksJson = objectMapper.writeValueAsString(startingSocksDtoToSave);
        // Initial settings before test
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(socksJson));

        final SocksDto additionalSocksDtoToAdd = testDoubleSocksDto;
        final ObjectMapper additionalObjectMapper = new ObjectMapper();
        final String additionalSocksJson = additionalObjectMapper.writeValueAsString(additionalSocksDtoToAdd);

        final SocksDto expectedSocksDto = testTripleSocksDto;

        // Incoming socks and Assertions
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(additionalSocksJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.colour")
                        .value(expectedSocksDto.getColour()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cotton")
                        .value(expectedSocksDto.getCotton()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount")
                        .value(expectedSocksDto.getAmount()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}