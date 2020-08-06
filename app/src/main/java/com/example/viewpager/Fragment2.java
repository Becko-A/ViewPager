package com.example.viewpager;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {
    private List<User> userList=new ArrayList<>();


    private Fragment2ViewModel mViewModel;
    public static Fragment2 newInstance() {
        return new Fragment2();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment2_fragment, container, false);
        initUsers();
        UserAdapter adapter=new UserAdapter(getActivity(),R.layout.user_list,userList);
        ListView listView=(ListView) rootview.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                User user=userList.get(position);
                Intent intent=new Intent(getActivity(),Bchat.class);
                startActivity(intent);
            }
        });
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Fragment2ViewModel.class);
        // TODO: Use the ViewModel
    }
    private void initUsers(){
        User user1=new User("用户小A","你好，你的耳机什么颜色？",R.drawable.user1);
        userList.add(user1);
        User user2=new User("用户小B","谢谢！！",R.drawable.user2);
        userList.add(user2);
        User user3=new User("用户小C","hello",R.drawable.user3);
        userList.add(user3);
    }


}