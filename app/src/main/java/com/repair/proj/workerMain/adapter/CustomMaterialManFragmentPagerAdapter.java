package com.repair.proj.workerMain.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.repair.proj.workerMain.childFragment.CancelFragment;
import com.repair.proj.workerMain.childFragment.CompleteFragment;
import com.repair.proj.workerMain.childFragment.ConductFragment;
import com.repair.proj.workerMain.childFragment.HaveMaterialFragment;
import com.repair.proj.workerMain.childFragment.PurchasingFragment;
import com.repair.proj.workerMain.childFragment.SaleMaterialFragment;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class CustomMaterialManFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"采购中","已有材料","售出材料"} ;

    public CustomMaterialManFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null ;
        switch (position){
            case 0:
                fragment = new PurchasingFragment() ;
                break;
            case 1:
                fragment = new HaveMaterialFragment() ;
                break;
            case 2:
                fragment = new SaleMaterialFragment() ;
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
