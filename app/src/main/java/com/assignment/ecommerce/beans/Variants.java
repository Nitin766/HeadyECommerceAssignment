package com.assignment.ecommerce.beans;

import com.google.gson.annotations.SerializedName;

public class Variants {

    @SerializedName("id")
    private int id;
    @SerializedName("color")
    private String color;
    @SerializedName("size")
    private int size;
    @SerializedName("price")
    private double price;

    public Variants(int id, String color, int size, double price) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
