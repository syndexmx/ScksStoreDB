package com.github.syndexmx.socksbase.controllers;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.syndexmx.socksbase.controllers.dtos.TestSocksDto.testDoubleSocksDto;
import static com.github.syndexmx.socksbase.controllers.dtos.TestSocksDto.testSocksDto;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CorrectionSocksControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CorrectionSocksController underTestController;

    @Test
    public void testThatSocksAreCreated() throws Exception {
        final SocksDto socksDtoToSave = testSocksDto;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String socksJson = objectMapper.writeValueAsString(socksDtoToSave);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/socks/"
                                + socksDtoToSave.getTypeId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(socksJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.colour")
                        .value(socksDtoToSave.getColour()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cotton")
                        .value(socksDtoToSave.getCotton()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount")
                        .value(socksDtoToSave.getAmount()))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testThatSocksAreCorrected() throws Exception {
        final SocksDto socksDtoToSave = testSocksDto;
        final ObjectMapper objectMapper = new ObjectMapper();
        final String socksJson = objectMapper.writeValueAsString(socksDtoToSave);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/socks/"
                                + socksDtoToSave.getTypeId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(socksJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.colour")
                        .value(socksDtoToSave.getColour()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cotton")
                        .value(socksDtoToSave.getCotton()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount")
                        .value(socksDtoToSave.getAmount()))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
        final SocksDto doubleSocksDtoToSave = testDoubleSocksDto;
        final ObjectMapper secondObjectMapper = new ObjectMapper();
        final String doubleSocksJson = secondObjectMapper.writeValueAsString(doubleSocksDtoToSave);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/socks/"
                                + socksDtoToSave.getTypeId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doubleSocksJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.colour")
                        .value(doubleSocksDtoToSave.getColour()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cotton")
                        .value(doubleSocksDtoToSave.getCotton()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount")
                        .value(doubleSocksDtoToSave.getAmount()))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}