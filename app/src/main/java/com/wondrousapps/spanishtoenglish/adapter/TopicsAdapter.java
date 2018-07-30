package com.wondrousapps.spanishtoenglish.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondrousapps.spanishtoenglish.R;
import com.wondrousapps.spanishtoenglish.model.Topics;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder>{
    Context context;
    List<Topics> topicsList;

    public TopicsAdapter(Context context, List<Topics> topicsList) {
        this.context = context;
        this.topicsList = topicsList;
    }

    @NonNull
    @Override
    public TopicsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.topics_items,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new TopicsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsViewHolder topicsViewHolder, int i) {
        Topics topics=topicsList.get(i);

        topicsViewHolder.tv_position.setText(String.valueOf(topics.getId())+" ");
        Log.d("id",String.valueOf(topics.getId()));
        topicsViewHolder.tv_topics.setText(topics.getTopic());
        Log.d("topic",topics.getTopic());

    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }

    class TopicsViewHolder extends RecyclerView.ViewHolder{
        TextView tv_position,tv_topics;
        public TopicsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_topics=itemView.findViewById(R.id.tv_topics);
            tv_position=itemView.findViewById(R.id.tv_position);
        }
    }
}
