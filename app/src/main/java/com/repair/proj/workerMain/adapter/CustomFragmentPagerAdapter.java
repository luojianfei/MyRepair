package com.repair.proj.workerMain.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.repair.proj.workerMain.childFragment.CancelFragment;
import com.repair.proj.workerMain.childFragment.CompleteFragment;
import com.repair.proj.workerMain.childFragment.ConductFragment;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class CustomFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"进行中","已完成","已取消"} ;

    public CustomFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null ;
        switch (position){
            case 0:
                fragment = new ConductFragment() ;
                break;
            case 1:
                fragment = new CompleteFragment() ;
                break;
            case 2:
                fragment = new CancelFragment() ;
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
