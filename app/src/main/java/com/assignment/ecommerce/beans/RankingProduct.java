package com.assignment.ecommerce.beans;

public class RankingProduct {

    private int productID;
    private int count;

    public RankingProduct(int productID, int count) {
        this.productID = productID;
        this.count = count;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
