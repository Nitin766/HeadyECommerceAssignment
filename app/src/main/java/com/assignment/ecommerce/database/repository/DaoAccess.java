package com.assignment.ecommerce.database.repository;

import android.arch.persistence.room.*;
import com.assignment.ecommerce.database.dbdao.*;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertCategory(ArrayList<CategoryTable> category);

    @Query("SELECT * FROM CategoryTable")
    List<CategoryTable> fetchAllCategory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertProduct(ProductTable product);

    @Query("SELECT * FROM ProductTable where cid = :cid")
    List<ProductTable> fetchProducts(int cid);

    @Query("SELECT * FROM ProductVariantTable where pid = :pid")
    List<ProductVariantTable> fetchVariants(int pid);

    @Query("SELECT * FROM TaxTable where pid = :pid")
    TaxTable fetchTax(int pid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertVariant(ArrayList<ProductVariantTable> variant);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertTax(ArrayList<TaxTable> tax);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertChildCategory(ArrayList<ChildCategoryTable> childCategory);
}
