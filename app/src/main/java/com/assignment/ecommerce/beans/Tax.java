package com.assignment.ecommerce.beans;

import com.google.gson.annotations.SerializedName;

public class Tax {

    @SerializedName("name")
    private String taxName;
    @SerializedName("value")
    private double taxValue;

    public Tax(String taxName, double taxValue) {
        this.taxName = taxName;
        this.taxValue = taxValue;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(double taxValue) {
        this.taxValue = taxValue;
    }
}
