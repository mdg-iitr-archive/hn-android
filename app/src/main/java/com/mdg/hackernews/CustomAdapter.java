package com.mdg.hackernews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<String> {
    ArrayList<String> foods;
    ArrayList<Integer> scores;
    ArrayList<Integer> comments;

    CustomAdapter(Context context, ArrayList<String> foods,ArrayList<Integer> scores,    ArrayList<Integer> comments) {
        super(context, R.layout.custom_row ,foods);
        this.foods=foods;
        this.scores=scores;
        this.comments=comments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.custom_row, parent, false);

        //String singleNewsItem = getItem(position);
        String title= foods.get(position);
        String score= scores.get(position).toString();
        String comment=comments.get(position).toString();
        TextView newsText = (TextView) customView.findViewById(R.id.newsText);
        TextView scoreText = (TextView) customView.findViewById(R.id.upvotes);
        TextView commentsText = (TextView) customView.findViewById(R.id.comments);
        newsText.setText(title);
        scoreText.setText(score);
        commentsText.setText(comment);
        return customView;
    }
    public void setEventList(ArrayList<String> eventList){
        //notifyItemRangeChanged(0,eventList.size());
        notifyDataSetChanged();
    }
}