package com.assignment.ecommerce.database.eCommerceDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.assignment.ecommerce.database.dbdao.*;
import com.assignment.ecommerce.database.repository.DaoAccess;

@Database(entities = {ProductTable.class, CategoryTable.class, ChildCategoryTable.class, ProductVariantTable.class
        , TaxTable.class}, version = 1, exportSchema = false)
public abstract class ECommerceDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
