package com.assignment.ecommerce.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("date_added")
    private String dateAdded;
    @SerializedName("variants")
    private ArrayList<Variants> variants;
    @SerializedName("tax")
    private Tax taxesApplicable;

    public Product(int id, String name, String dateAdded, ArrayList<Variants> variants, Tax taxesApplicable) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.variants = variants;
        this.taxesApplicable = taxesApplicable;
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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public ArrayList<Variants> getVariants() {
        return variants;
    }

    public void setVariantsList(ArrayList<Variants> variants) {
        this.variants = variants;
    }

    public Tax getTaxesApplicable() {
        return taxesApplicable;
    }

    public void setTaxesApplicable(Tax taxesApplicable) {
        this.taxesApplicable = taxesApplicable;
    }
}
