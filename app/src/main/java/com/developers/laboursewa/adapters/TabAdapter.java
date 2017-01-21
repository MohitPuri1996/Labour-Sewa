package com.developers.laboursewa.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.developers.laboursewa.fragments.Home;
import com.developers.laboursewa.fragments.Jobs;
import com.developers.laboursewa.fragments.Tutorials;

/**
 * Created by Amanjeet Singh on 21-Jan-17.
 */

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Home();
            case 1:
                return new Jobs();
            case 2:
                return new Tutorials();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
