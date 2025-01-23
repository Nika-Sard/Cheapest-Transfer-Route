package com.example.Cheapest.Transfer.Route.Service;

import com.example.Cheapest.Transfer.Route.Helpers.ValidateRequest;
import com.example.Cheapest.Transfer.Route.Interface.ITransferService;
import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import com.example.Cheapest.Transfer.Route.Model.TransfersResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class TransferService implements ITransferService {

    @Autowired
    private DPSolutionService dpSolutionService;

    public TransfersResponseDto calculateOptimalTransfers(TransfersRequestDto transfersDto) {
        new ValidateRequest(transfersDto);
        return dpSolutionService.solveUsingDP(transfersDto);
    }

}
