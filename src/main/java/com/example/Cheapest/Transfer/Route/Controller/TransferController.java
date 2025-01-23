package com.example.Cheapest.Transfer.Route.Controller;

import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import com.example.Cheapest.Transfer.Route.Model.TransfersResponseDto;
import com.example.Cheapest.Transfer.Route.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping("/cheapestTransfer")
    @ResponseBody
    public TransfersResponseDto getCheapestTransferRoute(@RequestBody TransfersRequestDto transfersDto){
        return transferService.calculateOptimalTransfers(transfersDto);
    }
}
