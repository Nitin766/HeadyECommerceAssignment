package com.assignment.ecommerce.services;

import com.assignment.ecommerce.beans.ResponseMapper;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GetDataService {

    @GET("/json")
    Call<ResponseMapper> getData();
}