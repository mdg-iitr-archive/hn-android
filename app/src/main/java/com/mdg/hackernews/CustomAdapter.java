package com.mdg.hackernews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, ArrayList<String> foods) {
        super(context, R.layout.custom_row ,foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.custom_row, parent, false);

        String singleNewsItem = getItem(position);
        TextView newsText = (TextView) customView.findViewById(R.id.newsText);

        newsText.setText(singleNewsItem);
        return customView;
    }
    public void setEventList(ArrayList<String> eventList){
        //notifyItemRangeChanged(0,eventList.size());
        notifyDataSetChanged();
    }
}