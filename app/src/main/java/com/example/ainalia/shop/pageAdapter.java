package com.example.ainalia.shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * Created by Ainalia on 2016/1/22.
 */
public class pageAdapter extends FragmentPagerAdapter {

    private PlusOneFragment plus;
    private BlankFragment   blank;
    private ItemFragment  item;
    private final String[] TITLES = {"最新消息", "商品查詢","特價商品","客戶服務","聯絡我們"};
    public pageAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PlusOneFragment();
            case 1:
                return new BlankFragment();
            case 2:
                return new ItemFragment();
            case 3:
                return new webFragment();
            case 4:
                return new SoapFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
