package com.yang.gremoon.dailynews.main.presenter;

import com.yang.gremoon.dailynews.R;
import com.yang.gremoon.dailynews.main.view.MainView;

/**
 * Description:
 * Created by Administrator on 2017/3/21.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }


    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                mMainView.switch2News();
                ;
                break;
            case R.id.navigation_item_images:
                mMainView.switch2Images();
                break;
            case R.id.navigation_item_weather:
                mMainView.switch2Weather();
                break;
            case R.id.navigation_item_setting:
                mMainView.switch2Setting();
                break;

        }
    }
}
