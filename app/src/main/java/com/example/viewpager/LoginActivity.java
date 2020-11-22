package com.example.viewpager;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
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
                //Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                //startActivity(intent);
                String account=et_account.getText().toString();
                String pwd=et_pwd.getText().toString();
                login(account,pwd);
            }
        });
    }



    private void login(String account, String pwd){
        if (account == null || "null".equals(account) || account.length() == 0){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"账号不能为空",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (pwd == null || "null".equals(pwd) || pwd.length() == 0){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"密码不能为空",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            OkHttpClient client=new OkHttpClient.Builder()
                    .build();

            RequestBody requestBodyJson=new FormBody.Builder()
                    .add("name",account)
                    .add("password",pwd)
                    .build();
            Request request=new Request.Builder()
                    .url("http://www.nanshannan331.com:80/login.php")
                    .addHeader("contentType","application/json;charset=UTF-8")
                    .post(requestBodyJson)
                    .build();
            final Call call=client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    final String result=response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(LoginActivity.this,result,Toast.LENGTH_SHORT).show();
                            Gson gson = new Gson();
                            Z_ReType re = gson.fromJson(result,Z_ReType.class);
                            if (re.code == 200){
                                //Toast.makeText(LoginActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }

                            else if (re.code == 404){
                                Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }


    }

}