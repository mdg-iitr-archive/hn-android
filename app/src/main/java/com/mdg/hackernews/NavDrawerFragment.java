package com.mdg.hackernews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abhishek on 24/3/15.
 */
public class NavDrawerFragment extends Fragment {

    private View mContainerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_drawer,container,false);

        return view;
    }

    public void setup(int fragmentId , DrawerLayout drawerLayout ,
                      final Toolbar toolbar){
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,
                toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened (View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed (View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run () {
                mActionBarDrawerToggle.syncState();
            }
        });
    }
}
