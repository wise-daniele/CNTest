package com.example.daniele.cntest.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniele on 21/03/17.
 */

public class RandomJoke {

    @SerializedName("type")
    private String type;
    @SerializedName("value")
    private Value value;

    public String getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public class Value {

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
}
