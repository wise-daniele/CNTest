package com.example.daniele.cntest.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniele on 21/03/17.
 */

public class Joke {

    @SerializedName("id")
    private int id;
    @SerializedName("joke")
    private String joke;

    public int getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }
}
