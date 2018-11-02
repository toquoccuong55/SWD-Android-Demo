package com.shoesshop.groupassignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoesshop.groupassignment.R;
import com.shoesshop.groupassignment.adapter.TabAdapter;
import com.shoesshop.groupassignment.fragment.CartFragment;
import com.shoesshop.groupassignment.fragment.HomeFragment;
import com.shoesshop.groupassignment.fragment.WishlistFragment;
import com.shoesshop.groupassignment.fragment.ProfileFragment;
import com.shoesshop.groupassignment.room.entity.ProductVariant;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TabAdapter mTabAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int[] tabIcons = {
            R.mipmap.ic_home,
            R.mipmap.ic_heart,
            R.mipmap.ic_bag,
            R.mipmap.ic_profile,
            R.mipmap.ic_home2,
            R.mipmap.ic_heart2,
            R.mipmap.ic_bag2,
            R.mipmap.ic_profile2
    };

    public static List<ProductVariant> mShoppingBag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialView();
        initialData();

    }

    private void initialView(){
        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

    }

    private void initialData(){
        if(mShoppingBag == null){
            mShoppingBag = new ArrayList<>();
        }

        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mTabAdapter.addFragment(new HomeFragment());
        mTabAdapter.addFragment(new WishlistFragment());
        mTabAdapter.addFragment(new CartFragment());
        mTabAdapter.addFragment(new ProfileFragment());
        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[4]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(tabIcons[4]);
                        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
                        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
                        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
                        break;
                    case 1:
                        tab.setIcon(tabIcons[5]);
                        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
                        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
                        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
                        break;
                    case 2:
                        tab.setIcon(tabIcons[6]);
                        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
                        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
                        mTabLayout.getTabAt(3).setIcon(tabIcons[3]);
                        break;
                    case 3:
                        tab.setIcon(tabIcons[7]);
                        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
                        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
                        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static void intentToHomeActivitiy(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
}
