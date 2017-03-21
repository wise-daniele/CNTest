package com.example.daniele.cntest.rest;

import com.example.daniele.cntest.rest.model.RandomJoke;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by daniele on 20/03/17.
 */

public class RestClient {

    private static final String mBaseUrl = "http://api.icndb.com/jokes/";

    private static RestClient instance;

    private Retrofit retrofit;

    private RestClient() { }

    public static RestClient getInstance( ) {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    public Retrofit getClient() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface ChuckNorrisInterface {

        @GET("random")
        Call<RandomJoke> getRandomJoke();

    }

}
