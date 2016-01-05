package com.mdg.hackernews;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by abhishek on 24/3/15.
 */
public class NewsItemListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    String jsonResponse = "abhi";
    VolleySingleton volleySingleton = VolleySingleton.getInstance();
    RequestQueue mRequestQueue = volleySingleton.getRequestQueue();
    HackerNewsInstance hnInstance = HackerNewsInstance.getInstance();
    ModelClass modelClass=new ModelClass();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<String> TitleList=new ArrayList<>();
    ArrayList<String> URL_list=new ArrayList<>();
    ArrayList<Integer> score_list=new ArrayList<>();
    ArrayList<Integer> comment_list=new ArrayList<>();
    CustomAdapter newsAdapter;
    ListView newsListView;
    String idURL;
    //String []news;
    //String []valueURL;
    int position=0;
    int n=10;
    int k=0;
    // int index=0;
    //String news []=new String[n];
    //String valueURL[]= new String[n];
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        Log.d("onCreateStarts", "onCreateStarts");
        /*try{
            index=getArguments().getInt("index");
            Log.d("DataAbhi6", "index set");

        }catch(Exception e){
        index=0;
        }*/
        /*arrList.add("news1");
        arrList.add("news2");
        news= arrList.toArray(new String[arrList.size()]);*/

        //newsAdapter = new CustomAdapter(getActivity(), news);
         position=modelClass.getPosition();

        if(position==0){
            Log.d("position","zero");
        }
       /*try{ index=getArguments().getInt("index");}
       catch (Exception e){
           index=0;
       }*/
        /*try{index=getArguments().getInt("index");}
        catch (Exception e){
            if(index==null){
                index=0;
            }
        }*/
        newsListView = (ListView) view.findViewById(R.id.newsListView);
        //sendJsonRequest(position);

        //sendJsonRequest();

        newsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       /*Intent intent = new Intent(getActivity().getBaseContext(),
                                MainActivity.class);
                        intent.putExtra("valueURL",valueURL[position]);
                        getActivity().startActivity(intent);*/
                        //((MainActivity)getActivity()).goToWebV(URL_list.get(position));


                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_list.get(position)));

                        startActivity(browserIntent);


                        Log.d("DataAbhi","Data sent to main");
                    }
                }
        );
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id
                .fragment_list_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run () {
                mSwipeRefreshLayout.setRefreshing(true);
                sendJsonRequest(position);
            }
        });
        Log.d("onCreateEns", "onCreateEnds");
        return view;
    }

    public void makeJsonObjectRequest() {

        Log.d("makeRequest","requested data");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,idURL,(String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("hackernewsresp", response.toString());

                        try {
                            // Parsing json object response
                            // response will be a json object
                            /*String by = response.getString("by");
                            int descendants = response.getInt("descendants");
                            int id = response.getInt("id");
                            int score = response.getInt("score");
                            int time = response.getInt("time");
                            String title = response.getString("title");
                            String type = response.getString("type");
                            String url = response.getString("url");*/
                            if(response.isNull("descendants")){
                                comment_list.add(0);
                            }else comment_list.add(response.getInt("descendants"));;
                            URL_list.add(response.getString("url").replace("\\/","/"));
                            TitleList.add(response.getString("title"));
                            score_list.add(response.getInt("score"));

                            //valueURL[k] =response.getString("id");
                            newsAdapter.notifyDataSetChanged();
                            k++;
                            if(k==n){
                                mSwipeRefreshLayout.setRefreshing(false);
                                k=0;
                            }

                            //arrList.add(Title);

    /* jsonResponse += "BY: " + by + "\n\n";
     jsonResponse += "TITLE: " + title + "\n\n";
     jsonResponse += "TYPE:" + type + "\n\n";
     jsonResponse += "URL: " +url + "\n\n";*/





                        } catch (JSONException e) {

                            e.printStackTrace();
                            jsonResponse = "Error";
                        }

                        // mTextView.setText(jsonResponse);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        // Adding request to request queue
        mRequestQueue.add(jsonObjReq);
        jsonResponse += "shek";
        Log.d("addQueue","added to queue");
    }
    public void sendJsonRequest(int ind) {
        String newsURl[]={"https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty","https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty","https://hacker-news.firebaseio.com/v0/showstories.json?print=pretty","https://hacker-news.firebaseio.com/v0/askstories.json?print=pretty","https://hacker-news.firebaseio.com/v0/jobstories.json?print=pretty"};
        /*if(TitleList!=null){
            TitleList.clear();
        }
        if(URL_list!=null){
            URL_list.clear();
        }*/
        newsAdapter = new CustomAdapter(getActivity(), TitleList,score_list,comment_list);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(newsURl[ind],
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Utils.showToast(getActivity(), response.toString());
                        String[] items = response.toString().replaceAll("\\[", "")
                                .replaceAll("\\]", "").replaceAll(" ", "").split(",");
                        int []results = new int[items.length];
                        //n=items.length;

                        for (int i = 0; i < n; i++) {
                            try {
                                results[i] = Integer.parseInt(items[i]);


                            } catch (NumberFormatException nfe) {
                            }

                        }
                        for (int j = 0; j < n; j++) {


                            idURL="https://hacker-news.firebaseio.com/v0/item/"+results[j]+".json?print=pretty";
                            makeJsonObjectRequest();


                            ;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        mRequestQueue.add(jsonArrayRequest);
        newsListView.setAdapter(newsAdapter);
    }
    public void onRefresh () {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run () {
                mSwipeRefreshLayout.setRefreshing(true);
                sendJsonRequest(position);
            }
        });
    }

}