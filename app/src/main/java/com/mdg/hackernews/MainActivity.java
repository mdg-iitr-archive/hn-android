package com.mdg.hackernews;


import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    int index;
    private Toolbar mToolbar;
    ModelClass modelClass=new ModelClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           /* Intent intent = getIntent();
            String message = intent.getStringExtra("valueURL");

        try{if(message!=null){
            Intent intent2 = new Intent(MainActivity.this,
                    webView.class);
            intent2.putExtra("webURL", message);
            startActivity(intent2);
                Log.d("DataAbhi2", "Data sent to web");}}
            catch(Exception e){
                throw e;
            }*/

        mToolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        NavDrawerFragment navDrawerFragment = (NavDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        navDrawerFragment.setup(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id
                        .drawer_layout),
                mToolbar);

        NewsItemListFragment newsItemListFragment = new NewsItemListFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,newsItemListFragment).commit();
        //Custom list view


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void goToWebV(String link){
        try{if(link!=null){
            Intent intent = new Intent(MainActivity.this,
                    webView.class);
            intent.putExtra("webURL", link);
            startActivity(intent);
            Log.d("DataAbhi2", "Data sent to web");}}
        catch(Exception e){
            throw e;
        }
    }
    public  void afterNaviClick(int position){
       /* NewsItemListFragment fragment=new NewsItemListFragment();
         fragment = (NewsItemListFragment) getSupportFragmentManager().findFragmentById(R.id.newsFrag);
        fragment.sendJsonRequest(index);*/
        /*Bundle bundle=new Bundle();
        bundle.putInt("index", index);
        //set Fragmentclass Arguments
        NewsItemListFragment fragobj=new NewsItemListFragment();
        fragobj.setArguments(bundle);
        Log.d("DataAbhi5", "Data sent to news List");*/
       /* GlobalConstant.Index=index;
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);*/
       // Fragment fragment=null;
        modelClass.setPosition(position);
        NewsItemListFragment fragment=new NewsItemListFragment();
        if (fragment != null) {
           // FragmentManager fragmentManager = getSupportFragmentManager();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment).commit();
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}
