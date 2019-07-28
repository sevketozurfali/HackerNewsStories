package com.oztech.hackernewsstories.repositories;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.oztech.hackernewsstories.models.Story;
import com.oztech.hackernewsstories.util.EndPoints;
import com.oztech.hackernewsstories.util.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StoryRepository {

    private static final String TAG = "StoryRepository";

    private static StoryRepository instance;
    private int max_item = 0;


    public static StoryRepository getInstance()
    {
        if(instance == null){
            instance = new StoryRepository();
        }
        return instance;
    }

    private EndPoints endPoints;

    public StoryRepository() {
        endPoints = RetrofitClientInstance.getRetrofitInstance().create(EndPoints.class);
    }

    public MutableLiveData<List<Story>> getAskStories() {

        final MutableLiveData<List<Story>> storiesData = new MutableLiveData<>();
        final List<Story> temp_story_list = new ArrayList<>();

        storiesData.setValue(temp_story_list);

        endPoints.getAskStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if(response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Story temp_story = new Story(response.body().get(i));
                        temp_story_list.add(temp_story);
                    }
                }
                else {
                    Log.i(TAG, "onResponse: no data received.");
                }
                storiesData.setValue(temp_story_list);
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Log.i(TAG, "onFailure: response failure.");

            }
        });
        return storiesData;
    }

    public MutableLiveData<Story> getStoryUniqueItem(Story singleStory){

        final MutableLiveData<Story> story_mutable_list = new MutableLiveData<>();

        endPoints.getItem(singleStory.getId()).enqueue(new Callback<Story>() {
            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                if(response.body() != null){
                    story_mutable_list.setValue(response.body());
                }
                else{
                    Log.i(TAG, "onResponse of getStoryUniqueItem: item null.");
                }
            }
            @Override
            public void onFailure(Call<Story> call, Throwable t) {
            }
        });
        return story_mutable_list;
    }

    public MutableLiveData<List<Story>> getBestStories() {

        final MutableLiveData<List<Story>> storiesData = new MutableLiveData<>();
        final List<Story> temp_story_list = new ArrayList<>();

        storiesData.setValue(temp_story_list);

        endPoints.getBestStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if(response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Story temp_story = new Story(response.body().get(i));
                        temp_story_list.add(temp_story);
                    }
                }
                else {
                    Log.i(TAG, "onResponse: no data received.");
                }

                storiesData.setValue(temp_story_list);
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Log.i(TAG, "onFailure: response failure.");

            }
        });
        return storiesData;
    }

    public MutableLiveData<List<Story>> getJobStories() {


        final MutableLiveData<List<Story>> storiesData = new MutableLiveData<>();
        final List<Story> temp_story_list = new ArrayList<>();

        storiesData.setValue(temp_story_list);

        endPoints.getJobStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if(response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Story temp_story = new Story(response.body().get(i));
                        temp_story_list.add(temp_story);
                    }
                }
                else {
                    Log.i(TAG, "onResponse: no data received.");
                }
                storiesData.setValue(temp_story_list);
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Log.i(TAG, "onFailure: response failure.");

            }
        });
        return storiesData;
    }

    public MutableLiveData<List<Story>> getTopStories() {

        final MutableLiveData<List<Story>> storiesData = new MutableLiveData<>();
        final List<Story> temp_story_list = new ArrayList<>();

        storiesData.setValue(temp_story_list);

        endPoints.getTopStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                if(response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Story temp_story = new Story(response.body().get(i));
                        temp_story_list.add(temp_story);
                    }
                }
                else
                {
                    Log.i(TAG, "onResponse: no data received.");
                }
                storiesData.setValue(temp_story_list);
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                Log.i(TAG, "onFailure: response failure.");

            }
        });
        return storiesData;
    }

    public MutableLiveData<Integer> getMaxItem(){

        MutableLiveData<Integer> mutmaxitem = new MutableLiveData<>();

        endPoints.getMaxitem().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    max_item = response.body();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        mutmaxitem.setValue(max_item);
        return mutmaxitem;
    }



}
