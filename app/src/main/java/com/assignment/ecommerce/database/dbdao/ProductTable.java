package com.assignment.ecommerce.database.dbdao;

import android.arch.persistence.room.*;

@Entity(foreignKeys = @ForeignKey(entity = CategoryTable.class,
        parentColumns = "cid",
        childColumns = "cid",
        onDelete = ForeignKey.NO_ACTION),
        indices = {@Index("cid")})
public class ProductTable {

    @ColumnInfo(name = "cid")
    public int categoryID;

    @PrimaryKey
    @ColumnInfo(name = "pid")
    public int productID;

    @ColumnInfo(name = "product_name")
    public String productName;

    @ColumnInfo(name = "data_added")
    public String dataAdded;

    @ColumnInfo(name = "view_count")
    public int viewCount;

    @ColumnInfo(name = "share_count")
    public int shareCount;

    @ColumnInfo(name = "order_count")
    public int orderCount;

    @ColumnInfo(name = "rank")
    public String rank;

    public ProductTable(int categoryID, int productID, String productName, String dataAdded) {
        this.categoryID = categoryID;
        this.productID = productID;
        this.productName = productName;
        this.dataAdded = dataAdded;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDataAdded() {
        return dataAdded;
    }

    public void setDataAdded(String dataAdded) {
        this.dataAdded = dataAdded;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
