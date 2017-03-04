package com.pickup.pickup.model;

import java.util.ArrayList;

/**
 * Created by zachschlesinger on 3/4/17.
 */

public class Place {

    private boolean isRestricted;
    private ArrayList<String> recommendEvents;
    private double openHour;
    private double closeHour;




    public Place(double openHour, double closeHour, ArrayList<String> recommendEvents, boolean isRestricted) {
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.isRestricted = isRestricted;
        this.recommendEvents = recommendEvents;
    }

    public boolean isRestricted() {
        return isRestricted;
    }

    public void setRestricted(boolean restricted) {
        isRestricted = restricted;
    }

    public ArrayList<String> getRecommendEvents() {
        return recommendEvents;
    }

    public void setRecommendEvents(ArrayList<String> recommendEvents) {
        this.recommendEvents = recommendEvents;
    }

    public double getOpenHour() {
        return openHour;
    }

    public void setOpenHour(double openHour) {
        this.openHour = openHour;
    }

    public double getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(double closeHour) {
        this.closeHour = closeHour;
    }
}
