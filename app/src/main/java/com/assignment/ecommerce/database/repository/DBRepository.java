package com.assignment.ecommerce.database.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.assignment.ecommerce.beans.*;
import com.assignment.ecommerce.database.dbdao.*;
import com.assignment.ecommerce.database.eCommerceDatabase.DatabaseClient;
import com.assignment.ecommerce.database.eCommerceDatabase.ECommerceDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBRepository {

    private ECommerceDatabase eCommerceDB;

    public DBRepository(Context context) {
        eCommerceDB = DatabaseClient.getInstance(context).getAppDatabase();
    }

    public void insertCategories(final ArrayList<CategoryTable> categories) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                eCommerceDB.daoAccess().insertCategory(categories);
                return null;
            }
        }.execute();
    }

    public void insertProducts(final HashMap<Integer, ProductTable> productTables) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                for (Map.Entry<Integer, ProductTable> entry : productTables.entrySet()) {
                    eCommerceDB.daoAccess().insertProduct(entry.getValue());
                }
                return null;
            }
        }.execute();
    }

    public void insertTaxes(final ArrayList<TaxTable> taxes) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                eCommerceDB.daoAccess().insertTax(taxes);
                return null;
            }
        }.execute();
    }

    public void insertProductVariants(final ArrayList<ProductVariantTable> variants) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                eCommerceDB.daoAccess().insertVariant(variants);
                return null;
            }
        }.execute();
    }

    public void insertChildCategories(final ArrayList<ChildCategoryTable> childCategories) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                eCommerceDB.daoAccess().insertChildCategory(childCategories);
                return null;
            }
        }.execute();
    }

    public ArrayList<CategoryTable> fetchCategoriesList() {
        return (ArrayList<CategoryTable>) eCommerceDB.daoAccess().fetchAllCategory();
    }

    public ArrayList<ProductTable> fetchProducts(int cid) {
        return (ArrayList<ProductTable>) eCommerceDB.daoAccess().fetchProducts(cid);
    }

    public ArrayList<ProductVariantTable> fetchVariants(int pid) {
        return (ArrayList<ProductVariantTable>) eCommerceDB.daoAccess().fetchVariants(pid);
    }

    public TaxTable fetchTax(int pid) {
        return (TaxTable) eCommerceDB.daoAccess().fetchTax(pid);
    }

    public void manipulateAndStoreData(ResponseMapper data) {
        if (data != null) {
            ArrayList<CategoryTable> fillCategoryTableData = new ArrayList<CategoryTable>();
            HashMap<Integer, ProductTable> productTableMap = new HashMap<Integer, ProductTable>(200);
            ArrayList<TaxTable> fillTaxTableData = new ArrayList<TaxTable>();
            ArrayList<ProductVariantTable> fillProductVariantTableData = new ArrayList<ProductVariantTable>();
            ArrayList<ChildCategoryTable> fillChildCategoryTableData = new ArrayList<ChildCategoryTable>();

            ArrayList<Category> categoriesList = data.getCategoriesList();
            if (categoriesList != null && categoriesList.size() > 0) {
                for (int i = 0; i < categoriesList.size(); i++) {
                    Category category = categoriesList.get(i);
                    int categoryID = category.getId();

                    CategoryTable categoryTable = new CategoryTable(categoryID, category.getName());
                    fillCategoryTableData.add(categoryTable);

                    ArrayList<Product> productList = category.getProducts();
                    if (productList != null && productList.size() > 0) {
                        for (int p = 0; p < productList.size(); p++) {
                            Product product = productList.get(p);
                            int productID = product.getId();

                            ProductTable productTable = new ProductTable(categoryID, productID,
                                    product.getName(), product.getDateAdded());
                            productTableMap.put(productID, productTable);

                            Tax tax = product.getTaxesApplicable();

                            if (tax != null) {
                                TaxTable taxTable = new TaxTable(productID, tax.getTaxName(), String.valueOf(tax.getTaxValue()));
                                fillTaxTableData.add(taxTable);
                            }

                            ArrayList<Variants> variants = product.getVariants();
                            if (variants != null && variants.size() > 0) {
                                for (int v = 0; v < variants.size(); v++) {
                                    Variants variant = variants.get(v);
                                    ProductVariantTable variantTable = new ProductVariantTable(productID, variant.getId(),
                                            variant.getColor(), variant.getSize(), String.valueOf(variant.getPrice()));
                                    fillProductVariantTableData.add(variantTable);
                                }
                            }
                        }
                    }

                    ArrayList<Integer> childCategories = category.getChildCategories();
                    if (childCategories != null && childCategories.size() > 0) {
                        for (int cc = 0; cc < childCategories.size(); cc++) {
                            ChildCategoryTable childCategoryTable = new ChildCategoryTable(categoryID, childCategories.get(cc));
                            fillChildCategoryTableData.add(childCategoryTable);
                        }
                    }
                }
            }

            ArrayList<Ranking> rankingList = data.getRankingList();

            if (rankingList != null && rankingList.size() > 0) {
                for (int r = 0; r < rankingList.size(); r++) {
                    Ranking ranking = rankingList.get(r);

                    String rank = ranking.getRank();
                    if (!TextUtils.isEmpty(rank)) {
                        ArrayList<RankingProduct> rankingViewedProductList = ranking.getRankingProductList();
                        for (int i = 0; i < rankingViewedProductList.size(); i++) {
                            RankingProduct rankingProduct = rankingViewedProductList.get(i);
                            int productID = rankingProduct.getProductID();
                            int count = rankingProduct.getCount();
                            productTableMap.get(productID).setRank(rank);
                            switch (rank) {
                                case "Most Viewed Products":
                                    productTableMap.get(productID).setViewCount(count);
                                    break;

                                case "Most OrdeRed Products":
                                    productTableMap.get(productID).setOrderCount(count);
                                    break;

                                case "Most ShaRed Products":
                                    productTableMap.get(productID).setShareCount(count);
                                    break;

                                default:
                                    break;
                            }
                        }
                    }
                }
            }

            insertCategories(fillCategoryTableData);
            insertProducts(productTableMap);
            insertProductVariants(fillProductVariantTableData);
            insertTaxes(fillTaxTableData);
            insertChildCategories(fillChildCategoryTableData);
        }
    }
}
