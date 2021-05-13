package com.example.mp3player.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //Declare retrofitClient
    private static Retrofit retrofit = null;
    //Base url
    private static final String base_url = "https://apphoangminh.000webhostapp.com/Server/";

    private static Retrofit getClient(){
        if(retrofit == null){

            // Create OkHttpClient
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true)
                    .protocols(Arrays.asList(Protocol.HTTP_1_1))
                    .build();

            //Create Gson instance
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //Init retrofit instance
            retrofit = new Retrofit.Builder()
                        .baseUrl(base_url)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

            return retrofit;
        }
        return retrofit;
    }

    //Get Instance that implement ApiService interface
    public static ApiService getApiService(){
        return getClient().create(ApiService.class);
    }

}
