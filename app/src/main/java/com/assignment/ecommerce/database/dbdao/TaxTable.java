package com.assignment.ecommerce.database.dbdao;

import android.arch.persistence.room.*;

@Entity(foreignKeys = @ForeignKey(entity = ProductTable.class,
        parentColumns = "pid",
        childColumns = "pid",
        onDelete = ForeignKey.NO_ACTION),
        indices = {@Index("pid")})
public class TaxTable {

    @ColumnInfo(name = "pid")
    public int productID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "tax_name")
    public String taxName;

    @ColumnInfo(name = "tax_value")
    public String taxValue;

    public TaxTable(int productID, String taxName, String taxValue) {
        this.productID = productID;
        this.taxName = taxName;
        this.taxValue = taxValue;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(String taxValue) {
        this.taxValue = taxValue;
    }
}
