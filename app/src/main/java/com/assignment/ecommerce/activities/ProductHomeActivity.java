package com.assignment.ecommerce.activities;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.assignment.ecommerce.R;
import com.assignment.ecommerce.adapters.ProductAdapter;
import com.assignment.ecommerce.database.dbdao.ProductTable;
import com.assignment.ecommerce.database.repository.DBRepository;
import com.assignment.ecommerce.utils.Constants;
import com.assignment.ecommerce.utils.Utils;

import java.util.*;

public class ProductHomeActivity extends AppCompatActivity {

    private DBRepository dbRepository;
    private RecyclerView productRecyclerView;
    private int categoryID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        dbRepository = new DBRepository(this);
        categoryID = Integer.parseInt(getIntent().getStringExtra(Constants.CATEGORY_ID));
        setupUI();
        displayProducts();
    }

    private void setupUI() {
        productRecyclerView = (RecyclerView) findViewById(R.id.productRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        productRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void displayProducts() {
        new AsyncTask<Void, Void, ArrayList<ProductTable>>() {
            AlertDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = Utils.showProgressDialog(ProductHomeActivity.this, getResources().getString(R.string.fetching_product));
            }

            @Override
            protected ArrayList<ProductTable> doInBackground(Void... voids) {
                return dbRepository.fetchProducts(categoryID);
            }

            @Override
            protected void onPostExecute(ArrayList<ProductTable> productList) {
                Utils.hideProgressDialog(dialog);
                if (productList != null && productList.size() > 0) {
                    ProductAdapter productAdapter = new ProductAdapter(productList, ProductHomeActivity.this);
                    productRecyclerView.setAdapter(productAdapter);// set the Adapter to RecyclerView
                } else {
                    Toast.makeText(ProductHomeActivity.this, getResources().getString(R.string.no_data_available), Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }
}
