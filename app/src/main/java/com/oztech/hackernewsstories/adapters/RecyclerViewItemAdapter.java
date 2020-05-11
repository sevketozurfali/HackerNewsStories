package com.oztech.hackernewsstories.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oztech.hackernewsstories.R;
import com.oztech.hackernewsstories.models.Story;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewItemAdapter extends RecyclerView.Adapter<RecyclerViewItemAdapter.viewHandler> {

    private static final String TAG = "RecyclerViewItemAdapter";

    private List<Story> stories = new ArrayList<>();
    Context mContext;

    public RecyclerViewItemAdapter(List<Story> stories, Context mContext) {
        this.stories = stories;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_comment_list_item, parent, false);
        viewHandler view_handler = new viewHandler(view);
        return view_handler;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHandler holder, int position) {
        holder.username.setText(stories.get(position).getBy());
        holder.text.setText(stories.get(position).getText());

        Date date = new Date(stories.get(position).getTime()*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yy | HH:mm");
        String time = simpleDateFormat.format(date);

        holder.time.setText(time);
        holder.imageView.setImageResource(R.drawable.ic_account_box_black_75dp);


    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class viewHandler extends RecyclerView.ViewHolder{

        TextView username, time, text;
        ConstraintLayout layout;
        ImageView imageView;

        public viewHandler(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.comment_username);
            time = itemView.findViewById(R.id.comment_time);
            text = itemView.findViewById(R.id.comment_text);
            layout = itemView.findViewById(R.id.comment_recyclerView);
            imageView = itemView.findViewById(R.id.comment_user_image);


        }
    }
}
