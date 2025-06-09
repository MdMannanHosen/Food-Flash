package com.google.foododerapps.models;

import java.util.List;

public class CategoryModels {

    private String image, name, brand, type, category, description, origin, expiry;
    private int price, calories, protein;
    private double fat;
    private List<String> tags;

    // ✅ Constructor
    public CategoryModels(String image, String name, String brand, String type, String category,
                          String description, String origin, int calories, int protein, double fat,
                          List<String> tags, String expiry, int price) {
        this.image = image;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.category = category;
        this.description = description;
        this.origin = origin;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.tags = tags;
        this.expiry = expiry;
        this.price = price;
    }

    // ✅ Getters and Setters
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
