package com.example.seba.learnenglish;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.seba.learnenglish.Fragments.Fragment1;
import com.example.seba.learnenglish.Fragments.Fragment10;
import com.example.seba.learnenglish.Fragments.Fragment11;
import com.example.seba.learnenglish.Fragments.Fragment12;
import com.example.seba.learnenglish.Fragments.Fragment2;
import com.example.seba.learnenglish.Fragments.Fragment3;
import com.example.seba.learnenglish.Fragments.Fragment4;
import com.example.seba.learnenglish.Fragments.Fragment5;
import com.example.seba.learnenglish.Fragments.Fragment6;
import com.example.seba.learnenglish.Fragments.Fragment7;
import com.example.seba.learnenglish.Fragments.Fragment8;
import com.example.seba.learnenglish.Fragments.Fragment9;

/**
 * Created by Seba on 28.04.2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            case 3:
                return new Fragment4();
            case 4:
                return new Fragment5();
            case 5:
                return new Fragment6();
            case 6:
                return new Fragment7();
            case 7:
                return new Fragment8();
            case 8:
                return new Fragment9();
            case 9:
                return new Fragment10();
            case 10:
                return new Fragment11();
            case 11:
                return new Fragment12();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 12;
    }
}
