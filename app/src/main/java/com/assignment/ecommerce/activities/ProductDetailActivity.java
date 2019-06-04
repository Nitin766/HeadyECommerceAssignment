package com.assignment.ecommerce.activities;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.assignment.ecommerce.R;
import com.assignment.ecommerce.database.dbdao.ProductVariantTable;
import com.assignment.ecommerce.database.dbdao.TaxTable;
import com.assignment.ecommerce.database.repository.DBRepository;
import com.assignment.ecommerce.utils.Constants;
import com.assignment.ecommerce.utils.Utils;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private DBRepository dbRepository;
    private int productID;
    private String productName;
    private TextView productIDTextView;
    private TextView productNameTextView;
    private TextView tax;
    private LinearLayout llRowLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        dbRepository = new DBRepository(this);
        productID = Integer.parseInt(getIntent().getStringExtra(Constants.PRODUCT_ID));
        productName = getIntent().getStringExtra(Constants.PRODUCT_NAME);
        setupUI();
        fetchVariants();
        fetchTax();
    }

    private void setupUI() {
        productIDTextView = (TextView) findViewById(R.id.productIDValue);
        productNameTextView = (TextView) findViewById(R.id.productNameValue);
        llRowLayout = (LinearLayout) findViewById(R.id.llrow_layout);
        tax = (TextView) findViewById(R.id.taxValue);
        productIDTextView.setText(productID+"");
        productNameTextView.setText(productName);
    }

    private void fetchVariants() {
        new AsyncTask<Void, Void, ArrayList<ProductVariantTable>>() {
            AlertDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = Utils.showProgressDialog(ProductDetailActivity.this, getResources().getString(R.string.fetching_product));
            }

            @Override
            protected ArrayList<ProductVariantTable> doInBackground(Void... voids) {
                return dbRepository.fetchVariants(productID);
            }

            @Override
            protected void onPostExecute(ArrayList<ProductVariantTable> variantsList) {
                Utils.hideProgressDialog(dialog);
                if (variantsList != null && variantsList.size() > 0) {
                    for(ProductVariantTable productVariant : variantsList){
                        View rowView = getLayoutInflater().inflate(R.layout.product_row_layout, null);
                        TextView color = (TextView) rowView.findViewById(R.id.color);
                        TextView size = (TextView) rowView.findViewById(R.id.size);
                        TextView price = (TextView) rowView.findViewById(R.id.price);
                        color.setText(productVariant.getColor());
                        size.setText(productVariant.getSize() + "");
                        price.setText(productVariant.getPrice() + "");
                        llRowLayout.addView(rowView);
                    }
                } else {
                    Toast.makeText(ProductDetailActivity.this, getResources().getString(R.string.no_data_available), Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    private void fetchTax() {
        new AsyncTask<Void, Void, TaxTable>() {
            AlertDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = Utils.showProgressDialog(ProductDetailActivity.this, getResources().getString(R.string.fetching_product));
            }

            @Override
            protected TaxTable doInBackground(Void... voids) {
                return dbRepository.fetchTax(productID);
            }

            @Override
            protected void onPostExecute(TaxTable taxes) {
                Utils.hideProgressDialog(dialog);
                if (taxes != null) {
                    tax.setText(taxes.getTaxName() + " : " + taxes.getTaxValue());
                } else {
                    Toast.makeText(ProductDetailActivity.this, getResources().getString(R.string.no_data_available), Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }
}
