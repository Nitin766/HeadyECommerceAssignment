package com.assignment.ecommerce.activities;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.assignment.ecommerce.R;
import com.assignment.ecommerce.adapters.CategoryAdapter;
import com.assignment.ecommerce.beans.ResponseMapper;
import com.assignment.ecommerce.database.dbdao.CategoryTable;
import com.assignment.ecommerce.database.repository.DBRepository;
import com.assignment.ecommerce.services.GetDataService;
import com.assignment.ecommerce.services.RetrofitApiClient;
import com.assignment.ecommerce.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private DBRepository dbRepository;
    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dialog = Utils.showProgressDialog(this, getResources().getString(R.string.downloading_data));
        dbRepository = new DBRepository(this);
        setupUI();
        if (Utils.isNetworkConnected(this)) {
            callEcommerceService();
        }else{
            displayCategories();
        }
    }

    private void setupUI() {
        categoryRecyclerView = (RecyclerView) findViewById(R.id.categoryRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        categoryRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void displayCategories() {
        new AsyncTask<Void, Void, ArrayList<CategoryTable>>() {
            AlertDialog dialog;
            @Override
            protected void onPreExecute() {
                dialog = Utils.showProgressDialog(DashboardActivity.this, getResources().getString(R.string.fetching_category));
            }

            @Override
            protected ArrayList<CategoryTable> doInBackground(Void... voids) {
                return dbRepository.fetchCategoriesList();
            }

            @Override
            protected void onPostExecute(ArrayList<CategoryTable> categoryList) {
                Utils.hideProgressDialog(dialog);
                if(categoryList != null && categoryList.size() > 0){
                    CategoryAdapter customAdapter = new CategoryAdapter(categoryList, DashboardActivity.this);
                    categoryRecyclerView.setAdapter(customAdapter);// set the Adapter to RecyclerView
                }else{
                    Toast.makeText(DashboardActivity.this, getResources().getString(R.string.no_data_available), Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    private void callEcommerceService() {
        GetDataService service = RetrofitApiClient.getClient().create(GetDataService.class);
        Call<ResponseMapper> call = service.getData();
        call.enqueue(new Callback<ResponseMapper>() {
            @Override
            public void onResponse(Call<ResponseMapper> call, Response<ResponseMapper> response) {
                Utils.hideProgressDialog(dialog);
                dbRepository.manipulateAndStoreData(response.body());
                displayCategories();
            }

            @Override
            public void onFailure(Call<ResponseMapper> call, Throwable t) {
                Utils.hideProgressDialog(dialog);
                Toast.makeText(DashboardActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
