package com.example.Cheapest.Transfer.Route.Model;

import java.util.List;

public class TransfersRequestDto {
    private int maxWeight;
    private List<Transfer> availableTransfers;

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<Transfer> getAvailableTransfers() {
        return availableTransfers;
    }

    public void setAvailableTransfers(List<Transfer> availableTransfers) {
        this.availableTransfers = availableTransfers;
    }
}

