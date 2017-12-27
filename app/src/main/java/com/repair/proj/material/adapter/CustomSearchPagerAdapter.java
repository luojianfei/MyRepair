package com.repair.proj.material.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.repair.proj.material.childFragment.MaterialAllFragment;
import com.repair.proj.material.childFragment.MaterialTypeFragment;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class CustomSearchPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"全部","分类"} ;

    public CustomSearchPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null ;
        switch (position){
            case 0:
                fragment = new MaterialAllFragment() ;
                break;
            case 1:
                fragment = new MaterialTypeFragment() ;
                break;
        }
        return fragment ;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
