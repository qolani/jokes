package com.example.jokes.entities;
import java.util.List;

public class JokeResponse {
    /****
     * Property declaration
     */
    private int total;
    private List<Joke> result;

    public JokeResponse(){}

    /***Getters and Setters
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Joke> getResult() {
        return result;
    }

    public void setResult(List<Joke> result) {
        this.result = result;
    }
}
