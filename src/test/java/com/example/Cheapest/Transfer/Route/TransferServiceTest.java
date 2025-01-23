package com.example.Cheapest.Transfer.Route;

import com.example.Cheapest.Transfer.Route.Helpers.ValidateRequest;
import com.example.Cheapest.Transfer.Route.Model.Transfer;
import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import com.example.Cheapest.Transfer.Route.Model.TransfersResponseDto;
import com.example.Cheapest.Transfer.Route.Service.DPSolutionService;
import com.example.Cheapest.Transfer.Route.Service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransferServiceTest {

    @Mock
    private DPSolutionService dpSolutionService;

    @InjectMocks
    private TransferService transferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateOptimalTransfers_ValidRequest() {
        TransfersRequestDto request = new TransfersRequestDto();
        request.setMaxWeight(15);
        request.setAvailableTransfers(Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20)
        ));

        TransfersResponseDto expectedResponse = new TransfersResponseDto(
                Arrays.asList(
                        new Transfer(5, 10),
                        new Transfer(10, 20)
                ),
                30,
                15
        );

        when(dpSolutionService.solveUsingDP(request)).thenReturn(expectedResponse);

        TransfersResponseDto response = transferService.calculateOptimalTransfers(request);

        assertEquals(expectedResponse, response);
        verify(dpSolutionService, times(1)).solveUsingDP(request);
    }

    @Test
    void testCalculateOptimalTransfers_InvalidRequest_EmptyTransfers() {
        TransfersRequestDto request = new TransfersRequestDto();
        request.setMaxWeight(15);
        request.setAvailableTransfers(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            new ValidateRequest(request);
        });

        assertEquals("400 BAD_REQUEST \"Available transfers list cannot be empty.\"", exception.getMessage());
    }

    @Test
    void testCalculateOptimalTransfers_InvalidRequest_NegativeMaxWeight() {
        TransfersRequestDto request = new TransfersRequestDto();
        request.setMaxWeight(-5);
        request.setAvailableTransfers(Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20)
        ));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            new ValidateRequest(request);
        });

        assertEquals("400 BAD_REQUEST \"Max weight must be positive.\"", exception.getMessage());
    }

    @Test
    void testCalculateOptimalTransfers_InvalidRequest_NegativeWeightOrCost() {
        TransfersRequestDto request = new TransfersRequestDto();
        request.setMaxWeight(5);
        request.setAvailableTransfers(Arrays.asList(
                new Transfer(-5, 10),
                new Transfer(10, -20)
        ));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            new ValidateRequest(request);
        });

        assertEquals("400 BAD_REQUEST \"Transfer weight and cost must be positive.\"", exception.getMessage());
    }
}
