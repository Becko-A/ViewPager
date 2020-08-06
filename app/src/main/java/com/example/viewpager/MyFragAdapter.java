package com.example.viewpager;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyFragAdapter extends FragmentStatePagerAdapter {
    Context context;
    List<Fragment> listFragment;
    public MyFragAdapter(FragmentManager fm,Context context,List<Fragment> listFragment){
        super(fm);
        this.context=context;
        this.listFragment=listFragment;
    }
    @Override
    public Fragment getItem(int position){
        return listFragment.get(position);
    }
    @Override
    public int getCount(){
        return listFragment.size();
    }
}
