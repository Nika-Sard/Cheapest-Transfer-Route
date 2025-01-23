package com.example.Cheapest.Transfer.Route.Helpers;

import com.example.Cheapest.Transfer.Route.Model.Transfer;
import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidateRequest {

    public ValidateRequest(TransfersRequestDto transfers) {
        validate(transfers);
    }

    private void validate(TransfersRequestDto transfersDto) {
        if (transfersDto.getMaxWeight() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Max weight must be positive.");
        }
        if (transfersDto.getAvailableTransfers() == null || transfersDto.getAvailableTransfers().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Available transfers list cannot be empty.");
        }
        for (Transfer transfer : transfersDto.getAvailableTransfers()) {
            if (transfer.getWeight() <= 0 || transfer.getCost() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer weight and cost must be positive.");
            }
        }
    }
}

