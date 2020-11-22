package com.example.viewpager;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.sql.BatchUpdateException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Aannounce extends AppCompatActivity {

    private EditText location;
    private EditText phoneNum;
    private EditText date;
    private EditText time;
    private EditText type;
    private EditText size;
    private EditText color;
    private EditText description;
    private Button announce;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aannounce);
        location = (EditText) findViewById(R.id.pick_location_Edtxt);
        phoneNum = (EditText) findViewById(R.id.pick_phonenum_Edtxt);
        date = (EditText) findViewById(R.id.pick_date_Edtxt);
        time = (EditText) findViewById(R.id.pick_time_Edtxt);
        type = (EditText) findViewById(R.id.pick_type_Edtxt);
        size = (EditText) findViewById(R.id.pick_size_Edtxt);
        color = (EditText) findViewById(R.id.pick_color_Edtxt);
        description = (EditText) findViewById(R.id.pick_descri_Edtxt);
        announce = (Button) findViewById(R.id.announce_next);
        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String srLocation = location.getText().toString();
                String srPhoneNum = phoneNum.getText().toString();
                String srDate = date.getText().toString();
                String srTime = time.getText().toString();
                String srType = type.getText().toString();
                String srSize = size.getText().toString();
                String srColor = color.getText().toString();
                String srdescription = description.getText().toString();

                sendRequestWithOkhttp(srLocation,srPhoneNum,srDate,srTime,srType,
                        srSize,srColor,srdescription);
            }
        });
    }
    private void sendRequestWithOkhttp(final String Location,final String PhoneNum,
                                       final String Date,final String Time, final String Type,
                                       final String Size,final String Color,final String Description){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody requestBody = new  FormBody.Builder()
                            .add("pick_location",Location)
                            .add("pick_phoneNum",PhoneNum)
                            .add("pick_data",Date)
                            .add("pick_time",Time)
                            .add("pick_type",Type)
                            .add("pick_size",Size)
                            .add("pick_color",Color)
                            .add("pick_description",Description)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.nanshannan331.com:80/pick.php")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(Aannounce.this,"fail",Toast.LENGTH_SHORT).show();

                }
            }
        }).start();
    }

    private void parseJSONWithGSON(final String jsonDate){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Z_ReType re = gson.fromJson(jsonDate,Z_ReType.class);
                if (re.code == 355){
                    Toast.makeText(Aannounce.this,"信息填写不完整",Toast.LENGTH_SHORT).show();
                }
                else if (re.code == 200){
                    Toast.makeText(Aannounce.this,"上传成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}