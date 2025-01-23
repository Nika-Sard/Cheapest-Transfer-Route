package com.example.Cheapest.Transfer.Route;

import com.example.Cheapest.Transfer.Route.Model.Transfer;
import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import com.example.Cheapest.Transfer.Route.Service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransferService transferService;

    @Test
    public void testGetCheapestTransferRoute_OkRequest() throws Exception {
        TransfersRequestDto requestDto = new TransfersRequestDto();
        requestDto.setMaxWeight(15);
        requestDto.setAvailableTransfers(Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20)
        ));

        mockMvc.perform(post("/api/cheapestTransfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(30))
                .andExpect(jsonPath("$.totalWeight").value(15));
    }

    @Test
    public void testGetCheapestTransferRoute_BadRequest() throws Exception {
        TransfersRequestDto requestDto = new TransfersRequestDto();
        requestDto.setMaxWeight(-15);
        requestDto.setAvailableTransfers(Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20)
        ));

        mockMvc.perform(post("/api/cheapestTransfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }
}
