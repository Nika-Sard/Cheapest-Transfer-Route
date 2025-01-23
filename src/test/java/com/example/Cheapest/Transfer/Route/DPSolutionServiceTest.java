package com.example.Cheapest.Transfer.Route;

import com.example.Cheapest.Transfer.Route.Model.Transfer;
import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import com.example.Cheapest.Transfer.Route.Model.TransfersResponseDto;
import com.example.Cheapest.Transfer.Route.Service.DPSolutionService;
import com.example.Cheapest.Transfer.Route.Service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DPSolutionServiceTest {

    private DPSolutionService dpSolutionService;

    @BeforeEach
    void setUp() {
        dpSolutionService = new DPSolutionService();
    }

    @Test
    void testSolveUsingDP() {
        TransfersRequestDto request = new TransfersRequestDto();
        request.setMaxWeight(16);
        request.setAvailableTransfers(Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(8, 15)
        ));

        TransfersResponseDto expectedResponse = new TransfersResponseDto(
                Arrays.asList(
                        new Transfer(10, 20),
                        new Transfer(5, 10)
                ),
                30,
                15
        );

        TransfersResponseDto actualResponse = dpSolutionService.solveUsingDP(request);

        assertEquals(expectedResponse.getTotalCost(), actualResponse.getTotalCost());
        assertEquals(expectedResponse.getTotalWeight(), actualResponse.getTotalWeight());
        assertEquals(expectedResponse.getSelectedTransfers().size(), actualResponse.getSelectedTransfers().size());

        assertEquals(expectedResponse.getSelectedTransfers().get(0).getWeight(),
                actualResponse.getSelectedTransfers().get(0).getWeight());
        assertEquals(expectedResponse.getSelectedTransfers().get(1).getWeight(),
                actualResponse.getSelectedTransfers().get(1).getWeight());
    }

    @Test
    void testSolveUsingDP_NoValidCombination() {
        TransfersRequestDto request = new TransfersRequestDto();
        request.setMaxWeight(5);
        request.setAvailableTransfers(Arrays.asList(
           new Transfer(10, 30),
           new Transfer(15, 20),
           new Transfer(20, 10)
        ));

        TransfersResponseDto expectedResponse = new TransfersResponseDto(
                Collections.emptyList(),
                0,
                0
        );

        TransfersResponseDto actualResponse = dpSolutionService.solveUsingDP(request);

        assertEquals(expectedResponse.getTotalCost(), actualResponse.getTotalCost());
        assertEquals(expectedResponse.getTotalWeight(), actualResponse.getTotalWeight());
        assertEquals(expectedResponse.getSelectedTransfers().size(), actualResponse.getSelectedTransfers().size());
    }

    @Test
    void testSolveUsingDP_SingleTransfer() {
        TransfersRequestDto request = new TransfersRequestDto();
        request.setMaxWeight(10);
        request.setAvailableTransfers(Arrays.asList(new Transfer(5, 10)));

        TransfersResponseDto expectedResponse = new TransfersResponseDto(
                Arrays.asList(new Transfer(5, 10)),
                10,
                5
        );

        TransfersResponseDto actualResponse = dpSolutionService.solveUsingDP(request);

        assertEquals(expectedResponse.getTotalCost(), actualResponse.getTotalCost());
        assertEquals(expectedResponse.getTotalWeight(), actualResponse.getTotalWeight());
        assertEquals(expectedResponse.getSelectedTransfers().get(0).getWeight(), actualResponse.getSelectedTransfers().get(0).getWeight());
        assertEquals(expectedResponse.getSelectedTransfers().get(0).getCost(), actualResponse.getSelectedTransfers().get(0).getCost());
    }

}
