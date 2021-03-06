package com.example.daniele.cntest.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniele on 21/03/17.
 */

public class RandomJoke {

    @SerializedName("type")
    private String type;
    @SerializedName("value")
    private Joke value;

    public String getType() {
        return type;
    }

    public Joke getValue() {
        return value;
    }

}
