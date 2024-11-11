package com.ms_retrofit.ms_retrofit.client;

import com.ms_retrofit.ms_retrofit.utils.response.ResponseCompany;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ClientCompany {
    @GET("/v2/sunat/ruc/full")
    Call<ResponseCompany> getCompany(@Header("Authorization") String token, @Query("numero") String numero);
}
