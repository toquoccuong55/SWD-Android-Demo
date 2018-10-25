package com.shoesshop.groupassignment.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.TabAdapter;
import com.shoesshop.groupassignment.fragment.HomeFragment;
import com.shoesshop.groupassignment.fragment.Tab2Fragment;
import com.shoesshop.groupassignment.fragment.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    private TabAdapter mTabAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mTabAdapter.addFragment(new HomeFragment(), "Home");
        mTabAdapter.addFragment(new Tab2Fragment(), "Tab 2");
        mTabAdapter.addFragment(new ProfileFragment(), "Profile");
        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
