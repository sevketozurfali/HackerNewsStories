package com.oztech.hackernewsstories.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oztech.hackernewsstories.MainActivity;
import com.oztech.hackernewsstories.R;
import com.oztech.hackernewsstories.adapters.RecyclerViewAdapter;
import com.oztech.hackernewsstories.models.Story;
import com.oztech.hackernewsstories.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopStoriesFragment extends Fragment {

    private static final String TAG = "TopStoriesFragment";

    private RecyclerView topStoriesView;
    private RecyclerViewAdapter topStoriesViewAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private List<Story> _stories = new ArrayList<>();
    private List<Story> _storiesList = new ArrayList<>();


    public TopStoriesFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();

        mainActivityViewModel.request_top_stories();

        mainActivityViewModel.get_stories().observe(this, new Observer<List<Story>>() {
            @Override
            public void onChanged(List<Story> stories) {
                _stories = stories;
                get_stories(_stories);
            }
        });


    }

    private void get_stories(final List<Story> storieslist){
        if(storieslist.size() > 0) {
            mainActivityViewModel.requestStoryItem(storieslist.get(0));

            mainActivityViewModel.getStoryItem().observe(this, new Observer<Story>() {
                @Override
                public void onChanged(Story story) {
                    storieslist.remove(0);
                    if(_storiesList.size() == 30 || storieslist.size() == 0){
                        hide_progressBar();
                    }
                    _storiesList.add(story);
                    topStoriesViewAdapter.notifyDataSetChanged();
                    get_stories(storieslist);
                }
            });
        }
        else
        {
            Log.i(TAG, "get_stories: No data fetched.");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_stories_fragment, container, false);

        show_progressBar();
        topStoriesView = view.findViewById(R.id.all_stories_recycler_view);

        topStoriesViewAdapter = new RecyclerViewAdapter(_storiesList,getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        topStoriesView.setLayoutManager(layoutManager);
        topStoriesView.setAdapter(topStoriesViewAdapter);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void show_progressBar(){
        ((MainActivity) getActivity()).show_progressBar();
    }

    private void hide_progressBar(){
        ((MainActivity) getActivity()).hide_progressBar();
    }

    @Override
    public void onStop() {
        hide_progressBar();
        super.onStop();
    }

}
