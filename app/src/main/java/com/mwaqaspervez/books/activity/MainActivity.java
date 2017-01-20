package com.mwaqaspervez.books.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.mwaqaspervez.books.Fragments.BookShelf;
import com.mwaqaspervez.books.Fragments.HomeFragment;
import com.mwaqaspervez.books.Fragments.MarketPlace;
import com.mwaqaspervez.books.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @OnClick({R.id.main_search})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.main_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new SectionPageAdapter(getSupportFragmentManager()));

        // Set up the tabs for ViewPager
        ((TabLayout) findViewById(R.id.tabLayout)).setupWithViewPager(mViewPager);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(Gravity.LEFT);
        else
            super.onBackPressed();
    }

    private class SectionPageAdapter extends FragmentPagerAdapter {

        String fragments[] = {"Home", "Market", "Shelf"};

        SectionPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:
                    return new HomeFragment();
                case 2:
                    return new MarketPlace();
                case 1:
                    return new BookShelf();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}
