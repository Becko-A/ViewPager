package com.example.viewpager;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private int resoueceId;
    public UserAdapter(Context context, int textViewResourceId, List<User> objects){
        super(context,textViewResourceId,objects);
        resoueceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        User user=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resoueceId,parent,false);
        ImageView userImage=(ImageView) view.findViewById(R.id.user_head);
        TextView userName=(TextView) view.findViewById(R.id.user_name);
        TextView userMessage=(TextView) view.findViewById(R.id.user_message);
        userImage.setImageResource(user.getImageId());
        userName.setText(user.getName());
        userMessage.setText(user.getMessage());
        return view;
    }
}
