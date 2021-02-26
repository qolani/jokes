package com.example.jokes.entities;

import androidx.annotation.Keep;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;

import com.example.jokes.BR;

@Keep
@Entity(tableName = "joke")
public class Joke extends BaseObservable {
    /****
     * Property declaration
     */
    private String jokeId;
    private String icon_url;
    private String url;
    private String value;

    /****
     * Empty constructor
     */
    public Joke(){}

    /****
     * Overloaded constructor
     * @param jokeId
     * @param icon_url
     * @param url
     * @param value
     */
    public Joke(String jokeId, String icon_url, String url, String value) {
        this.jokeId = jokeId;
        this.icon_url = icon_url;
        this.url = url;
        this.value = value;
    }

    /****
     * Getters and Setters
     * @return
     */
    @Bindable
    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    @Bindable
    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
        notifyPropertyChanged(BR.jokeId);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.jokeId);
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyPropertyChanged(BR.jokeId);
    }
}
