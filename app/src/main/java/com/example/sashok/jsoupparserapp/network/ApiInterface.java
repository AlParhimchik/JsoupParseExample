package com.example.sashok.jsoupparserapp.network;

import com.example.sashok.jsoupparserapp.model.Answer;
import com.example.sashok.jsoupparserapp.model.photo.FlickrAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sashok on 1.10.17.
 */

public interface ApiInterface {
    @GET("translate")
    Call<Answer> translateText(@Query("lang") String lang_str, @Query("key") String key_str, @Query("text") String text_str);

    @GET("rest?")
    Call<FlickrAnswer> getPhotoById(@Query("method") String method, @Query("api_key") String api_key, @Query("photo_id") String photo_id, @Query("format") String format, @Query("nojsoncallback") int callback);

    @GET("rest?method=flickr.photos.search&per_page=1&page=1&media=photos&api_key=79c3b582a43a9b8a7e76832af1ac06f3&format=json&nojsoncallback=1&sort=relevance")
    Call<FlickrAnswer> searchPhoto(@Query("text") String text);
}
