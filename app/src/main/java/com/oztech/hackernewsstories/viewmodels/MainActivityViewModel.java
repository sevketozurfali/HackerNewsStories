package com.oztech.hackernewsstories.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oztech.hackernewsstories.models.Story;

import com.oztech.hackernewsstories.repositories.StoryRepository;

import java.util.List;



public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    private MutableLiveData<List<Story>> mutableLiveStoryData;
    private MutableLiveData<List<Story>> storyMutableLiveData;
    private MutableLiveData<Integer> maxItem;
    private StoryRepository mRepo;

    private MutableLiveData<Story> unique_data;

    public void init(){
        if(mutableLiveStoryData != null){
            return;
        }
        if(storyMutableLiveData != null){
            return;
        }
        if(unique_data != null){
            return;
        }
        if(maxItem != null){
            return;
        }

        mRepo = StoryRepository.getInstance();
    }

    public void request_max_item(){
        init();
        maxItem = mRepo.getMaxItem();
    }

    public void request_ask_stories(){
        init();
        mutableLiveStoryData = mRepo.getAskStories();
    }

    public void request_best_stories(){
        init();
        mutableLiveStoryData = mRepo.getBestStories();
    }

    public void request_job_stories(){
        init();
        mutableLiveStoryData = mRepo.getJobStories();
    }

    public void request_top_stories(){
        init();
        mutableLiveStoryData = mRepo.getTopStories();
    }

    public void requestStoryItem(Story singleStory){
        init();
        unique_data = mRepo.getStoryUniqueItem(singleStory);
    }

    public MutableLiveData<List<Story>> get_stories(){
        return mutableLiveStoryData;
    }

    public MutableLiveData<Story> getStoryItem(){
        return unique_data;
    }

    public MutableLiveData<Integer> getMaxItemId(){
        return maxItem;
    }
}
