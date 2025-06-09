package com.google.foododerapps.models;

public class AllRestaurantModels {

    private String image;
    private String name;
    private double rating;
    private int deliveryTime;
    private String cuisine;

    public AllRestaurantModels(String image, String name, double rating, int deliveryTime, String cuisine) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.cuisine = cuisine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
