package com.assignment.ecommerce.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Category {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("products")
    private ArrayList<Product> products;
    @SerializedName("child_categories")
    private ArrayList<Integer> childCategories;

    public Category(int id, String name, ArrayList<Product> products, ArrayList<Integer> childCategories) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.childCategories = childCategories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Integer> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(ArrayList<Integer> childCategories) {
        this.childCategories = childCategories;
    }
}
