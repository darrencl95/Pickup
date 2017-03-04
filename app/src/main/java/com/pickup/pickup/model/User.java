package com.pickup.pickup.model;

/**
 * Created by zachschlesinger on 3/4/17.
 */

public class User {

    // implement user image
    private String username;
    private String firstName;
    private String lastName;
    private String[] favEvents;
    private String userType;

    public User(String user, String first, String last, String[] fav, String type) {
        username = user;
        firstName = first;
        lastName = last;
        favEvents = fav;
        userType = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String[] getFavEvents() {
        return favEvents;
    }

    public void setFavEvents(String[] favEvents) {
        this.favEvents = favEvents;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
