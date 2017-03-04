package com.pickup.pickup.model;

/**
 * Created by zachschlesinger on 3/4/17.
 */

public class PickupEvent {

    private String type;
    private int minPeople;
    private int maxPeople;
    private double minTime;
    private double maxTime;
    private int status; // this should be 0, 1, or 2 corresponding to pending, confirmed, completed


    public PickupEvent(String type, int minPeople, int maxPeople, double minTime, double maxTime, int status) {
        this.type = type;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinPeople() {
        return minPeople;
    }

    public void setMinPeople(int minPeople) {
        this.minPeople = minPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public double getMinTime() {
        return minTime;
    }

    public void setMinTime(double minTime) {
        this.minTime = minTime;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(double maxTime) {
        this.maxTime = maxTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
