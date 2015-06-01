package com.mdg.hackernews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class NewsItemListFragment extends Fragment {
    String jsonResponse = "abhi";
    VolleySingleton volleySingleton = VolleySingleton.getInstance();
    RequestQueue mRequestQueue = volleySingleton.getRequestQueue();
    HackerNewsInstance hnInstance = HackerNewsInstance.getInstance();
    TextView mTextView;
    ArrayList<String> TitleList=new ArrayList<>();
    ArrayList<String> URL_list=new ArrayList<>();
    CustomAdapter newsAdapter;
    ListView newsListView;
    String idURL;
    //String []news;
    //String []valueURL;
    int j;
    int n=10;
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
        int index=GlobalConstant.Index;
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
        mTextView = (TextView) view.findViewById(R.id.mTextview);
        sendJsonRequest(index);

        //sendJsonRequest();

        newsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       /*Intent intent = new Intent(getActivity().getBaseContext(),
                                MainActivity.class);
                        intent.putExtra("valueURL",valueURL[position]);
                        getActivity().startActivity(intent);*/
                        ((MainActivity)getActivity()).goToWebV(URL_list.get(position));

                        Log.d("DataAbhi","Data sent to main");
                    }
                }
        );
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

                            URL_list.add(response.getString("url").replace("\\/","/"));
                            TitleList.add(response.getString("title"));
                            //valueURL[k] =response.getString("id");
                            newsAdapter.notifyDataSetChanged();


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
        String newsURl[]={"https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty","https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty",
                "https://hacker-news.firebaseio.com/v0/showtories.json?print=pretty","https://hacker-news.firebaseio.com/v0/askstories.json?print=pretty","https://hacker-news.firebaseio.com/v0/jobstories.json?print=pretty"};
        if(TitleList!=null){
            TitleList.clear();
        }
        if(URL_list!=null){
            URL_list.clear();
        }
        newsAdapter = new CustomAdapter(getActivity(), TitleList);
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
                        for (j = 0; j < n; j++) {


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


}