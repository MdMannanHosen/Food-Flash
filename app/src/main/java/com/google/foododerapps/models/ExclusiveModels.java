package com.google.foododerapps.models;

public class ExclusiveModels {

    String  name,ingredients, tags, cuisine, image;
    private int cookTimeMinutes;
    private double rating;


    public ExclusiveModels(String name, String ingredients, String tags, String cuisine, String image, int cookTimeMinutes, double rating) {
        this.name = name;
        this.ingredients = ingredients;
        this.tags = tags;
        this.cuisine = cuisine;
        this.image = image;
        this.cookTimeMinutes = cookTimeMinutes;
        this.rating = rating;
    }

    public ExclusiveModels(String name, String cuisine, String image, String s, int cookTimeMinutes, double rating) {


        this.name = name;
        this.cuisine = cuisine;
        this.image = image;
        this.cookTimeMinutes = cookTimeMinutes;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCookTimeMinutes() {
        return cookTimeMinutes;
    }

    public void setCookTimeMinutes(int cookTimeMinutes) {
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}