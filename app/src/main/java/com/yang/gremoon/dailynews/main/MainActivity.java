package com.yang.gremoon.dailynews.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.yang.gremoon.dailynews.R;
import com.yang.gremoon.dailynews.about.AboutFragment;
import com.yang.gremoon.dailynews.base.BaseFragment;
import com.yang.gremoon.dailynews.images.ImagesFragment;
import com.yang.gremoon.dailynews.main.presenter.MainPresenter;
import com.yang.gremoon.dailynews.main.presenter.MainPresenterImpl;
import com.yang.gremoon.dailynews.main.view.MainView;
import com.yang.gremoon.dailynews.news.widget.newsFragment;
import com.yang.gremoon.dailynews.weather.WeatherFragment;

/**
 * Description:
 * Created by Administrator on 2017/3/21.
 */

public class MainActivity extends AppCompatActivity implements MainView {
    private Toolbar mToolbar;
    private FrameLayout mFrameContent;
    private NavigationView mNavigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mUpDrawerContent;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigation = (NavigationView) findViewById(R.id.navigation);
        setupDrawerContent(mNavigation);
        mFrameContent = (FrameLayout) findViewById(R.id.frame_content);

        mMainPresenter = new MainPresenterImpl(this);
        switch2News();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mMainPresenter.switchNavigation(item.getItemId());
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void switch2News() {
        replaceFrameContent(new newsFragment(), R.string.navigation_news);
    }


    @Override
    public void switch2Images() {
        replaceFrameContent(new ImagesFragment(), R.string.navigation_images);
    }

    @Override
    public void switch2Weather() {
        replaceFrameContent(new WeatherFragment(), R.string.navigation_weather);
    }

    @Override
    public void switch2Setting() {
        replaceFrameContent(new AboutFragment(), R.string.navigation_setting);
    }

    private void replaceFrameContent(BaseFragment fragment, int id) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
        mToolbar.setTitle(getResources().getString(id));
    }
}
