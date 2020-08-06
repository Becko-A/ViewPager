package com.example.viewpager;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CdiushiFragment extends Fragment {
    private View mView;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext=getContext();
        mView=inflater.inflate(R.layout.fragment_cdiushi, container, false);
        ListView goods=mView.findViewById(R.id.list_item);
        String[] list=initListData();
        goods.setAdapter(new ArrayAdapter<String>(mContext,R.layout.support_simple_spinner_dropdown_item,list));
        // Inflate the layout for this fragment
        return mView;
    }
    public String[] initListData(){
        String[] list=new String[3];
        for (int i=0;i<3;i++){
            list[i]="  物品"+(i+1);
        }
        return list;
    }
}