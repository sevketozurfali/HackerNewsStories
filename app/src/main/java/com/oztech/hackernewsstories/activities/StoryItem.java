package com.oztech.hackernewsstories.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oztech.hackernewsstories.R;
import com.oztech.hackernewsstories.adapters.RecyclerViewItemAdapter;
import com.oztech.hackernewsstories.models.Story;
import com.oztech.hackernewsstories.viewmodels.MainActivityViewModel;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoryItem extends AppCompatActivity {

    private static final String TAG = "StoryItem";

    //ui components
    TextView title, time, by, content, comments, score, descendants, type;

    RecyclerView recyclerView;
    RecyclerViewItemAdapter recyclerViewItemAdapter;
    ProgressBar progressBar;

    //Variables
    Story story;
    private List<Integer> kids = new ArrayList<>();
    private List<Integer> first_story_list = new ArrayList<>();
    private List<Integer> second_story_list = new ArrayList<>();
    private List<Integer> third_story_list = new ArrayList<>();
    private List<Story> total_comment_list = new ArrayList<>();

    //View model
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_item);
        Log.i(TAG, "onCreate: started.");

        //ui definitions
        title = findViewById(R.id.textView_story_title);
        time = findViewById(R.id.textView_story_time);
        by = findViewById(R.id.textView_story_by);
        content = findViewById(R.id.textView_story_detail);
        comments = findViewById(R.id.textView_story_comments);
        score = findViewById(R.id.textView_score);
        descendants = findViewById(R.id.textView_descendants);
        type = findViewById(R.id.textView_story_type);

        recyclerView = findViewById(R.id.comment_recyclerView);
        progressBar = findViewById(R.id.progressBar);


        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();


        story = getIntent().getParcelableExtra("selected_story");

        try {
            title.setText(story.getTitle());

            Date date = new Date(story.getTime()*1000L);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy | HH:mm");
            String format_time = simpleDateFormat.format(date);

            time.setText(format_time);
            by.setText("By: " + story.getBy());
            content.setText(story.getText());
            descendants.setText("Seen " + story.getDescendants());
            score.setText("Score : " + story.getScore());
            if (story.getType().equals("story")) {
                type.setText("Story");
            }
            get_kids(story.getId());
            loadRecyclerView();

        }
        catch (Exception ex){
            Log.i(TAG, "onCreate:");
        }
    }

    public void get_kids(Integer temp_story_id)
    {
        Story temp_story = new Story(temp_story_id);

        mainActivityViewModel.requestStoryItem(temp_story);

        mainActivityViewModel.getStoryItem().observe(this, new Observer<Story>() {
            @Override
            public void onChanged(Story story) {
                if(story.getKids() != null && story.getKids().size() > 0){
                    kids = story.getKids();
                    get_kids_kids(kids);
                }
                else
                {
                    hideProgressBar();
                }
            }
        });

    }

    public void get_kids_kids(final List<Integer> temp_story_list){

        first_story_list = temp_story_list;
        try {
            mainActivityViewModel.requestStoryItem(new Story(first_story_list.get(0)));

            mainActivityViewModel.getStoryItem().observe(this, new Observer<Story>() {
                @Override
                public void onChanged(Story story) {
                    try {
                        recyclerViewItemAdapter.notifyDataSetChanged();
                    }
                    catch (Exception ex){
                        Log.i(TAG, "onChanged: notifydatasetChanged ex.");
                    }
                    if (first_story_list.size() > 0) {
                        total_comment_list.add(story);
                        first_story_list.remove(0);

                        if(story.getKids() != null){
                            if(story.getKids().size() > 0){
                                find_second_kids(story.getKids());
                            }
                            else {
                                get_kids_kids(first_story_list);
                            }
                        }
                        else {
                            get_kids_kids(first_story_list);
                        }
                    }
                    else {
//                        loadRecyclerView();
                    }
                }
            });
        }
        catch (Exception ex){
            Log.i(TAG, "get_kids_kids: Exception ex : " + ex.toString());
//            loadRecyclerView();
            hideProgressBar();
        }
    }


    private void find_second_kids(List<Integer> temp_story_list){
        second_story_list = temp_story_list;
        try {
            mainActivityViewModel.requestStoryItem(new Story(second_story_list.get(0)));

            mainActivityViewModel.getStoryItem().observe(this, new Observer<Story>() {
                @Override
                public void onChanged(Story story) {
                    try {
                        recyclerViewItemAdapter.notifyDataSetChanged();
                    }
                    catch (Exception ex){
                        Log.i(TAG, "onChanged: notifydatasetChanged ex.");
                    }
                    if(second_story_list.size() > 0) {
                        total_comment_list.add(story);
                        second_story_list.remove(0);
                        if (story.getKids() != null) {
                            if (story.getKids().size() > 0) {
                                find_third_kids(story.getKids());
                            } else {
                                find_second_kids(second_story_list);
                            }
                        }
                        else {
                            find_second_kids(second_story_list);
                        }
                    }
                    else
                    {
                        get_kids_kids(first_story_list);
                    }
                }
            });
        }
        catch (Exception ex){
            get_kids_kids(first_story_list);
            Log.i(TAG, "find_second_kids: Ex : " + ex.toString());
        }
//        loadRecyclerView();
    }

    private void find_third_kids(List<Integer> temp_story_list){
        third_story_list = temp_story_list;
        try {
            mainActivityViewModel.requestStoryItem(new Story(third_story_list.get(0)));

            mainActivityViewModel.getStoryItem().observe(this, new Observer<Story>() {
                @Override
                public void onChanged(Story story) {
                    try {
                        recyclerViewItemAdapter.notifyDataSetChanged();
                    }
                    catch (Exception ex){
                        Log.i(TAG, "onChanged: notifydatasetChanged ex.");
                    }
                    if(third_story_list.size() > 0){
                        total_comment_list.add(story);
                        third_story_list.remove(0);
                        find_third_kids(third_story_list);
                    }
                    else {
                        find_second_kids(second_story_list);
                    }
                }
            });
        }
        catch (Exception ex){
            find_second_kids(second_story_list);
            Log.i(TAG, "find_third_kids: Ex : " + ex.toString());
//            loadRecyclerView();
        }
    }

    private void loadRecyclerView(){
        recyclerViewItemAdapter = new RecyclerViewItemAdapter(total_comment_list,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewItemAdapter);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }

}
