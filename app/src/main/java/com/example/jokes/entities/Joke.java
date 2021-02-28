package com.example.jokes.entities;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Joke extends BaseObservable {
    /****
     * Property declaration
     */
    private String jokeId;
    private String icon_url;
    private String url;
    private String value;
    private String created_at;
    private String updated_at;
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
     * @param created_at
     * @param updated_at
     * @param categories
     */
    public Joke(String jokeId, String icon_url, String url, String value, String created_at, String updated_at, List<String> categories) {
        this.jokeId = jokeId;
        this.icon_url = icon_url;
        this.url = url;
        this.value = value;
        this.created_at = created_at;
        this.updated_at = updated_at;
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
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
        notifyPropertyChanged(BR.created_at);
    }

    @Bindable
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
        notifyPropertyChanged(BR.updated_at);
    }

    @Bindable
    public List<String> getCategoriesList() {
        return categories;
    }

    public void setCategoriesList(List<String> categories) {
        this.categories = categories;
        notifyPropertyChanged(BR.categoriesList);
    }

    @BindingAdapter("image")
    public static void OnLoadImage(ImageView imageView, String image_url){
        Glide.with(imageView.getContext())
                .load(image_url).apply(new RequestOptions().centerCrop())
                .into(imageView);
    }
}
