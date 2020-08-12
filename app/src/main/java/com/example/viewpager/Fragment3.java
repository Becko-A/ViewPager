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
import android.widget.EditText;
import android.widget.TextView;

public class Fragment3 extends Fragment {

    private Fragment3ViewModel mViewModel;
    private View setting;
    private View shiwu;
    private View diushi;
    private View wanshan;
    private View ziliao;
    private TextView et_name;
    private TextView et_school;
    private  TextView et_education;

    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ActivityCollectorUtil.addActivity(getActivity());
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
        wanshan=(View) getActivity().findViewById(R.id.wsmessage);
        ziliao=(View) getActivity().findViewById(R.id.frag_ziLiao);
        et_name=(TextView) getActivity().findViewById(R.id.frag_name);
        et_school=(TextView) getActivity().findViewById(R.id.frag_school);
        et_education=(TextView) getActivity().findViewById(R.id.frag_education);
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
        wanshan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(getActivity(), Cimprove.class);
                //startActivity(intent);
                Intent intent=new Intent(getActivity(),Cimprove.class);
                String name=et_name.getText().toString();
                String school=et_school.getText().toString();
                String education=et_education.getText().toString();
                intent.putExtra("NAME",name);
                intent.putExtra("SCHOOL",school);
                intent.putExtra("EDUCATION",education);
                int requestCode=1;
                startActivityForResult(intent,requestCode);
            }
        });
        ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Cimprove.class);
                String name=et_name.getText().toString();
                String school=et_school.getText().toString();
                String education=et_education.getText().toString();
                intent.putExtra("NAME",name);
                intent.putExtra("SCHOOL",school);
                intent.putExtra("EDUCATION",education);
                int requestCode=1;
                startActivityForResult(intent,requestCode);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==9){
            String result_name=data.getStringExtra("RESULT_NAME");
            et_name.setText(result_name);
            String result_school=data.getStringExtra("RESULT_SCHOOL");
            et_school.setText(result_school);
            String result_education=data.getStringExtra("RESULT_EDUCATION");
            et_education.setText(result_education);

        }
    }
}