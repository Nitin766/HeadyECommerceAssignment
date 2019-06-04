package com.assignment.ecommerce.database.dbdao;

import android.arch.persistence.room.*;

@Entity(foreignKeys = @ForeignKey(entity = ProductTable.class,
        parentColumns = "pid",
        childColumns = "pid",
        onDelete = ForeignKey.NO_ACTION),
        indices = {@Index("pid")})
public class ProductVariantTable {

    @ColumnInfo(name = "pid")
    public int productID;

    @PrimaryKey
    @ColumnInfo(name = "vid")
    public int variantID;

    @ColumnInfo(name = "color")
    public String color;

    @ColumnInfo(name = "size")
    public int size;

    @ColumnInfo(name = "price")
    public String price;

    public ProductVariantTable(int productID, int variantID, String color, int size, String price) {
        this.productID = productID;
        this.variantID = variantID;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getVariantID() {
        return variantID;
    }

    public void setVariantID(int variantID) {
        this.variantID = variantID;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
