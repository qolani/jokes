package com.example.jokes.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JokeResponse {
    private int total;
    private List<Joke> result;

    public int getTotal() {
        return total;
    }

    public List<Joke> getResult() {
        return result;
    }
}
