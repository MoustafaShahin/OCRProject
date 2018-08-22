package com.example.moustafashahin.ocrproject.api;

import retrofit2.converter.gson.GsonConverterFactory;
//https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=votes&title=android%20null&site=stackoverflow
public class Retrofit {
    public static final String BASE_URL = "https://api.stackexchange.com/";
    private static retrofit2.Retrofit retrofit = null;


    public static retrofit2.Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
