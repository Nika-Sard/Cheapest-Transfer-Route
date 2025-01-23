package com.example.Cheapest.Transfer.Route.Model;

public class Transfer {
    private final int weight;
    private final int cost;

    public Transfer(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }
    public int getWeight(){
        return this.weight;
    }
}
