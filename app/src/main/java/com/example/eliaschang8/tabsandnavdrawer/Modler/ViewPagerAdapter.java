package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.eliaschang8.tabsandnavdrawer.Presenter.Feature;
import com.example.eliaschang8.tabsandnavdrawer.Presenter.MostPopular;
import com.example.eliaschang8.tabsandnavdrawer.Presenter.MostRecent;
import com.example.eliaschang8.tabsandnavdrawer.Presenter.News;
import com.example.eliaschang8.tabsandnavdrawer.Presenter.Opinion;
import com.example.eliaschang8.tabsandnavdrawer.Presenter.Sports;

/**
 * Created by eliaschang8 on 4/26/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new MostRecent();
        } else if (position == 1) {
            return new News();
        }else if (position == 2) {
            return new Sports();
        }else if (position == 3) {
            return new Feature();
        }else return new Opinion();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
