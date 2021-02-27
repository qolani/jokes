package com.example.jokes.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import java.util.List;

public class Joke extends BaseObservable {
    /****
     * Property declaration
     */
    private String jokeId;
    private String icon_url;
    private String url;
    private String value;
    private List<String> categories;

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
    public Joke(String jokeId, String icon_url, String url, String value, List<String> categories) {
        this.jokeId = jokeId;
        this.icon_url = icon_url;
        this.url = url;
        this.value = value;
        this.categories = categories;
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
        notifyPropertyChanged(BR.jokeId);
    }

    @Bindable
    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
        notifyPropertyChanged(BR.icon_url);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyPropertyChanged(BR.value);
    }

    @Bindable
    public List<String> getCategoriesList() {
        return categories;
    }

    public void setCategoriesList(List<String> categories) {
        this.categories = categories;
        notifyPropertyChanged(BR.categoriesList);
    }
}
