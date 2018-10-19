package com.example.piotr.pl5_task.apixu;

import com.example.piotr.pl5_task.consts.Common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApixuAPIHelper {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Common.API_LINK_JSON)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
