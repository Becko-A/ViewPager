package com.example.viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cimprove_detail extends AppCompatActivity {
    private EditText editSchool;
    private View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cimprove_detail);
        editSchool=(EditText) findViewById(R.id.edit_school);
        back=(View) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //4). 得到intent对象
        Intent intent = getIntent();
        //5). 通过intent读取额外数据
        String message = intent.getStringExtra("MESSAGE");
        //6). 显示到EditText
        editSchool.setText(message);

    }
    public void commit(View v) {

        //保存一个结果
        int resultCode = 9;
        //准备一个带额外数据的intent对象
        Intent data = new Intent();
        String result = editSchool.getText().toString();
        data.putExtra("RESULT", result);
        //设置结果
        setResult(resultCode, data );
        //关闭当前界面
        finish();

    }
}