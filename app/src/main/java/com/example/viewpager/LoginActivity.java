package com.example.viewpager;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView bt_zhuce;
    private Button bt_login;
    private EditText et_account;
    private EditText et_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt_zhuce=(TextView) findViewById(R.id.register);
        bt_login=(Button) findViewById(R.id.login);
        et_account=(EditText) findViewById(R.id.account);
        et_pwd=(EditText) findViewById(R.id.password);

        bt_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                String account=et_account.getText().toString().trim();
                String pwd=et_pwd.getText().toString().trim();
                //login(account,pwd);
            }
        });
    }
    /*private void login(String account,String pwd){
        if (account == null || "null".equals(account) || account.length() == 0){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"账号不能为空",Toast.LENGTH_SHORT);
                }
            });
            return;
        }
        if (pwd == null || "null".equals(pwd) || pwd.length() == 0){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT);
                }
            });
            return;
        }
        OkHttpClient client=new OkHttpClient.Builder()
                .build();
        Map m=new HashMap();
        m.put("name",account);
        m.put("password",pwd);
        JSONObject jsonObject=new JSONObject(m);
        String jsonStr=jsonObject.toString();
        RequestBody requestBodyJson=
                RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonStr);
        Request request=new Request.Builder()
                .url()
                .addHeader("contentType","application/json;charset=UTF-8")
                .post(requestBodyJson)
                .build();
        final Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String result=response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,result,Toast.LENGTH_SHORT);
                    }
                });
            }
        });


    }*/
}