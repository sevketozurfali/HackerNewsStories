package com.oztech.hackernewsstories.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.oztech.hackernewsstories.R;
import com.oztech.hackernewsstories.activities.StoryItem;
import com.oztech.hackernewsstories.models.Story;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHandler>{

    private static final String TAG = "RecyclerViewAdapter";

    private List<Story> stories = new ArrayList<>();
    Context mContext;

    public RecyclerViewAdapter(List<Story> stories, Context mContext) {
        this.stories = stories;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public viewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories_list_view, parent, false);
        viewHandler view_handler = new viewHandler(view);
        return view_handler;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHandler holder, final int position) {

        if(stories.get(position).getType().equals("story")){
            holder.textStoryType.setText("Story");
        }
        else
        {
            holder.textStoryType.setText(stories.get(position).getType());
        }
        holder.textStoryTitle.setText(stories.get(position).getTitle());

        Date date = new Date(stories.get(position).getTime()*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy | HH:mm");
        String time = simpleDateFormat.format(date);

        holder.textStoryTime.setText(time);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StoryItem.class);
                intent.putExtra("selected_story", stories.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class viewHandler extends RecyclerView.ViewHolder{

        TextView textStoryType, textStoryTitle, textStoryTime;
        ConstraintLayout layout;

        public viewHandler(@NonNull View itemView) {
            super(itemView);

            textStoryType = itemView.findViewById(R.id.textView_story_type);
            textStoryTitle = itemView.findViewById(R.id.textView_story_title);
            textStoryTime = itemView.findViewById(R.id.textView_story_time);
            layout = itemView.findViewById(R.id.story_list_layout);

        }


    }


}
