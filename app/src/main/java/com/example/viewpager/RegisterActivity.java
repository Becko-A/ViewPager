package com.example.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_phoneNum;
    private EditText et_password;
    private Button bt_register;
    private EditText et_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActivityCollectorUtil.addActivity(this);
        et_phoneNum=(EditText) findViewById(R.id.phoneNum);
        et_password=(EditText) findViewById(R.id.password);
        bt_register=(Button) findViewById(R.id.register);
        et_check=(EditText) findViewById(R.id.repeat);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account=et_phoneNum.getText().toString();
                String pwd=et_password.getText().toString();
                String check=et_check.getText().toString();
                register(account,pwd,check);
                //sendRequestWithOkhttp(account,pwd);
            }
        });
    }

    private void register(String account,String pwd,String check){
        if (account == null || "null".equals(account) || account.length() == 0){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegisterActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }
        if (pwd == null || "null".equals(pwd) || pwd.length() == 0){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }
        if(pwd.equals(check)){

            OkHttpClient client=new OkHttpClient.Builder()
                    .build();
            RequestBody requestBodyJson=new FormBody.Builder()
                    .add("name",account)
                    .add("password",pwd)
                    .build();
            Request request=new Request.Builder()
                    .url("http://www.nanshannan331.com:80/register.php")
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
                            //Toast.makeText(RegisterActivity.this,result,Toast.LENGTH_SHORT).show();
                            Gson gson = new Gson();
                            Z_ReType re = gson.fromJson(result,Z_ReType.class);
                            if (re.code == 200){
                                //Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else if (re.code == 355){
                                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
        else {
            Toast.makeText(RegisterActivity.this,"输入密码不一致",Toast.LENGTH_SHORT).show();
        }

    }

}