package com.example.moustafashahin.ocrproject.api;

import com.example.moustafashahin.ocrproject.Model.MyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
//https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=votes&title=android%20null&site=stackoverflow
public interface StackService {
@GET("2.2/search/advanced?")
Call<MyResponse> Repos(@Query("order") String order,@Query("sort") String sort,@Query("title") String text,@Query("site") String site_stackoverflow);
}
