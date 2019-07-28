package com.oztech.hackernewsstories.util;

import androidx.annotation.NonNull;

import com.oztech.hackernewsstories.models.Story;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPoints {

    @GET("/v0/askstories.json")
    @NonNull
    Call<List<Integer>> getAskStories();

    @GET("/v0/beststories.json")
    @NonNull
    Call<List<Integer>> getBestStories();

    @GET("/v0/jobstories.json")
    @NonNull
    Call<List<Integer>> getJobStories();

    @GET("/v0/topstories.json")
    @NonNull
    Call<List<Integer>> getTopStories();


    @GET("/v0/item/{id}.json")
    Call<Story> getItem(
            @Path("id") Integer id
    );

    @GET("/v0/maxitem.json")
    Call<Integer> getMaxitem();

}
