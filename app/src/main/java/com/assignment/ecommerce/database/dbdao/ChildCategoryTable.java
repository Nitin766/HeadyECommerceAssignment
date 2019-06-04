package com.assignment.ecommerce.database.dbdao;

import android.arch.persistence.room.*;

@Entity(foreignKeys = @ForeignKey(entity = CategoryTable.class,
        parentColumns = "cid",
        childColumns = "cid",
        onDelete = ForeignKey.NO_ACTION),
        indices = {@Index("cid")})
public class ChildCategoryTable {

    @PrimaryKey
    @ColumnInfo(name = "cid")
    public int categoryID;

    @ColumnInfo(name = "child_id")
    public int childID;

    public ChildCategoryTable(int categoryID, int childID) {
        this.categoryID = categoryID;
        this.childID = childID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getChildID() {
        return childID;
    }

    public void setChildID(int childID) {
        this.childID = childID;
    }
}
