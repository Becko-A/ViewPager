package com.example.viewpager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class Cimprove extends AppCompatActivity {
    private View schoolView;
    private TextView final_school;

    private View deptView;
    private TextView final_dept;

    private View educationView;
    private TextView final_education;

    private View stuIDView;
    private TextView final_stuID;

    private View nameView;
    private TextView final_name;

    private View phoneView;
    private TextView final_phone;

    private View weChatView;
    private TextView final_weChat;

    private View qqView;
    private TextView final_qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cimprove);
        schoolView=(View) findViewById(R.id.click_school);
        deptView=(View) findViewById(R.id.click_dept);
        educationView=(View) findViewById(R.id.click_education);
        stuIDView=(View) findViewById(R.id.click_stuID);
        nameView=(View) findViewById(R.id.click_name);
        phoneView=(View) findViewById(R.id.click_phone);
        weChatView=(View) findViewById(R.id.click_weChat);
        qqView=(View) findViewById(R.id.click_qq);
        final_school=(TextView) findViewById(R.id.school);
        final_dept=(TextView) findViewById(R.id.dept);
        final_education=(TextView) findViewById(R.id.education);
        final_stuID=(TextView) findViewById(R.id.stuID);
        final_name=(TextView) findViewById(R.id.name);
        final_phone=(TextView) findViewById(R.id.phone);
        final_weChat=(TextView) findViewById(R.id.weChat);
        final_qq=(TextView) findViewById(R.id.qq);
        schoolView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1). 创建Intent对象(显式)
                Intent intent = new Intent(Cimprove.this, Cimprove_detail.class);
                //2). 通过intent携带额外数据
                String message = final_school.getText().toString();
                intent.putExtra("MESSAGE", message);
                //3). 带回调启动Activity
                int requestCode = 1;
                startActivityForResult(intent, requestCode);
            }
        });
        deptView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cimprove.this,Cimprove_detail.class);
                String message=final_dept.getText().toString();
                intent.putExtra("MESSAGE",message);
                int requestCode=2;
                startActivityForResult(intent,requestCode);
            }
        });
        educationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cimprove.this,Cimprove_detail.class);
                String message=final_education.getText().toString();
                intent.putExtra("MESSAGE",message);
                int requestCode=3;
                startActivityForResult(intent,requestCode);
            }
        });
        stuIDView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cimprove.this,Cimprove_detail.class);
                String message=final_stuID.getText().toString();
                intent.putExtra("MESSAGE",message);
                int requestCode=4;
                startActivityForResult(intent,requestCode);
            }
        });
        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cimprove.this,Cimprove_detail.class);
                String message=final_name.getText().toString();
                intent.putExtra("MESSAGE",message);
                int requestCode=5;
                startActivityForResult(intent,requestCode);
            }
        });
        phoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cimprove.this,Cimprove_detail.class);
                String message=final_phone.getText().toString();
                intent.putExtra("MESSAGE",message);
                int requestCode=6;
                startActivityForResult(intent,requestCode);
            }
        });
        weChatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cimprove.this,Cimprove_detail.class);
                String message=final_weChat.getText().toString();
                intent.putExtra("MESSAGE",message);
                int requestCode=7;
                startActivityForResult(intent,requestCode);
            }
        });
        qqView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cimprove.this,Cimprove_detail.class);
                String message=final_qq.getText().toString();
                intent.putExtra("MESSAGE",message);
                int requestCode=8;
                startActivityForResult(intent,requestCode);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //判断code
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 9) {
            //从data中取出数据
            String result = data.getStringExtra("RESULT");
            //显示
            final_school.setText(result);
        }
        else if (requestCode == 2 && resultCode == 9){
            String result = data.getStringExtra("RESULT");
            final_dept.setText(result);
        }
        else if (requestCode == 3 && resultCode == 9){
            String result = data.getStringExtra("RESULT");
            final_education.setText(result);
        }
        else if (requestCode == 4 && resultCode == 9){
            String result = data.getStringExtra("RESULT");
            final_stuID.setText(result);
        }
        else if (requestCode == 5 && resultCode == 9){
            String result = data.getStringExtra("RESULT");
            final_name.setText(result);
        }
        else if (requestCode == 6 && resultCode == 9){
            String result = data.getStringExtra("RESULT");
            final_phone.setText(result);
        }
        else if (requestCode == 7 && resultCode == 9){
            String result = data.getStringExtra("RESULT");
            final_weChat.setText(result);
        }
        else if (requestCode == 8 && resultCode == 9){
            String result = data.getStringExtra("RESULT");
            final_qq.setText(result);
        }
    }

}