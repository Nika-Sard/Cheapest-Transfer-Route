package com.example.Cheapest.Transfer.Route.Service;

import com.example.Cheapest.Transfer.Route.Interface.IDPSolutionService;
import com.example.Cheapest.Transfer.Route.Model.Transfer;
import com.example.Cheapest.Transfer.Route.Model.TransfersRequestDto;
import com.example.Cheapest.Transfer.Route.Model.TransfersResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DPSolutionService implements IDPSolutionService {

    public TransfersResponseDto solveUsingDP(TransfersRequestDto transfersDto){
        int maxWeight = transfersDto.getMaxWeight();
        List<Transfer> transfers = transfersDto.getAvailableTransfers();

        int[] dp = new int[maxWeight + 1];
        int[] path = new int[maxWeight + 1];
        initializeArray(path, maxWeight + 1);
        processTransfers(transfers, maxWeight, dp, path);

        return build(maxWeight, path, transfers);
    }

    private void initializeArray(int[] array, int n){
        for(int i = 0; i < n; i++) array[i] = -1;
    }
    private void processTransfers(List<Transfer> transfers, int maxWeight, int[] dp, int[] path) {
        for (int i = 0; i < transfers.size(); i++) {
            int transferWeight = transfers.get(i).getWeight();
            int transferCost = transfers.get(i).getCost();
            for (int weight = maxWeight; weight >= transferWeight; weight--) {
                int newCost = dp[weight - transferWeight] + transferCost;
                if (newCost > dp[weight]) {
                    dp[weight] = newCost;
                    path[weight] = i;
                }
            }
        }
    }

    private TransfersResponseDto build(int maxWeight, int[] path, List<Transfer> transfers) {
        List<Transfer> selectedTransfers = new ArrayList<>();
        boolean[] selectedIndices = new boolean[transfers.size() + 1];
        int weight = maxWeight;
        int totalWeight = 0;
        int totalCost = 0;

        while (weight > 0 && path[weight] != -1) {
            int itemIndex = path[weight];
            if (!selectedIndices[itemIndex]) {
                selectedIndices[itemIndex] = true;
                totalWeight += transfers.get(itemIndex).getWeight();
                totalCost += transfers.get(itemIndex).getCost();
                selectedTransfers.add(transfers.get(itemIndex));
            }
            weight -= transfers.get(itemIndex).getWeight();
        }

        return new TransfersResponseDto(selectedTransfers, totalCost, totalWeight);
    }

}
