package com.example.Cheapest.Transfer.Route.Interface;

import com.example.Cheapest.Transfer.Route.Model.Transfer;
import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import com.example.Cheapest.Transfer.Route.Model.TransfersResponseDto;


public interface ITransferService {
    TransfersResponseDto calculateOptimalTransfers(TransfersRequestDto request);
}
