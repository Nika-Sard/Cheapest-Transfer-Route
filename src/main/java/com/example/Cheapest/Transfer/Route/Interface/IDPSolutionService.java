package com.example.Cheapest.Transfer.Route.Interface;

import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import com.example.Cheapest.Transfer.Route.Model.TransfersResponseDto;

public interface IDPSolutionService {
    TransfersResponseDto solveUsingDP(TransfersRequestDto transfersDto);
}
