package com.example.jokes.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JokeResponse {
    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("result")
    @Expose
    private List<Joke> jokeList;

    public int getTotal() {
        return total;
    }

    public List<Joke> getJokeList() {
        return jokeList;
    }
}
