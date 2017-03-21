package com.example.daniele.cntest.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daniele on 21/03/17.
 */

public class RandomJokes {

    @SerializedName("type")
    private String type;
    @SerializedName("value")
    private List<Joke> value;

    public String getType() {
        return type;
    }

    public List<Joke> getValue() {
        return value;
    }
}
