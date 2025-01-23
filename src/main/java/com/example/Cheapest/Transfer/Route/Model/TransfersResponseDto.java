package com.example.Cheapest.Transfer.Route.Model;

import java.util.List;

public class TransfersResponseDto {
    private List<Transfer> selectedTransfers;
    private int totalCost;
    private int totalWeight;

    public TransfersResponseDto(List<Transfer> selectedTransfers, int totalCost, int totalWeight) {
        this.selectedTransfers = selectedTransfers;
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public List<Transfer> getSelectedTransfers() {
        return selectedTransfers;
    }
}
