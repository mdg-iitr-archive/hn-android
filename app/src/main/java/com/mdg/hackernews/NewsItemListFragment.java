package com.mdg.hackernews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by abhishek on 24/3/15.
 */
public class NewsItemListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        String[] news = {"news1","news2","news3","news4","news5","news6","news7","news8"};
        ListAdapter newsAdapter = new CustomAdapter(getActivity(), news);
        ListView newsListView = (ListView) view.findViewById(R.id.newsListView);
        newsListView.setAdapter(newsAdapter);

        newsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        //Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
                    }
                }
        );
        return view;
    }
}
