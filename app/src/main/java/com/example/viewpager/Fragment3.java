package com.example.viewpager;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment3 extends Fragment {

    private Fragment3ViewModel mViewModel;
    private View setting;
    private View shiwu;
    private View diushi;

    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Fragment3ViewModel.class);
        // TODO: Use the ViewModel
        setting=(View) getActivity().findViewById(R.id.settings);
        shiwu=(View) getActivity().findViewById(R.id.record_shiwu);
        diushi=(View) getActivity().findViewById(R.id.diushi);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Csettings.class);
                startActivity(intent);

            }
        });
        shiwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Crecord_shiwu.class);
                startActivity(intent);
            }
        });
        diushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Crecord_diushi.class);
                startActivity(intent);
            }
        });
    }

}