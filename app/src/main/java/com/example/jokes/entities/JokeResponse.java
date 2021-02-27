package com.example.jokes.entities;
import java.util.List;

public class JokeResponse {
    /****
     * Property declaration
     */
    private int total;
    private List<Joke> result;

    /***Getters
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    public List<Joke> getResult() {
        return result;
    }
}
