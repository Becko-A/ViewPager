package com.example.viewpager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment{
    private PopupWindow popupwindow;
    private TextView positionText;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    //private double longitude;
    //private double latitude;
    private LocationClient mLocationClient;
    private boolean isFirstLocate = true;

    private ImageButton movebtn;
    private boolean clickormove = true;//点击或拖动，点击为true，拖动为false
    private int downX, downY;//按下时的X，Y坐标
    private boolean hasMeasured = false;//ViewTree是否已被测量过，是为true，否为false
    private View content;//界面的ViewTree
    private int screenWidth,screenHeight;//ViewTree的宽和高

    private Fragment1ViewModel mViewModel;

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ActivityCollectorUtil.addActivity(getActivity());

        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getActivity().getApplicationContext());

        View rootView=inflater.inflate(R.layout.fragment1_fragment,container,false);
        mMapView=(MapView) rootView.findViewById(R.id.bmapView);
        mBaiduMap=mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        positionText = (TextView) rootView.findViewById(R.id.position_text_view);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(), permissions, 1);
        } else {
            requestLocation();
        }



        content = getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT);//获取界面的ViewTree根节点View

        DisplayMetrics dm = getResources().getDisplayMetrics();//获取显示屏属性
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        ViewTreeObserver vto = content.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {

        // TODO Auto-generated method stub
                if(!hasMeasured)
                {
                    screenHeight = content.getMeasuredHeight();//获取ViewTree的高度
                    hasMeasured = true;//设置为true，使其不再被测量。
                }
                return true;//如果返回false，界面将为空。
            }
        });
        movebtn = (ImageButton) rootView.findViewById(R.id.button1);
        movebtn.setOnTouchListener(new View.OnTouchListener() {//设置按钮被触摸的时间
            int lastX, lastY; // 记录移动的最后的位置
            @Override
            public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
                int ea = event.getAction();//获取事件类型
                switch (ea) {
                    case MotionEvent.ACTION_DOWN: // 按下事件
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        downX = lastX;
                        downY = lastY;
                        break;
                    case MotionEvent.ACTION_MOVE: // 拖动事件
        // 移动中动态设置位置
                        int dx = (int) event.getRawX() - lastX;//位移量X
                        int dy = (int) event.getRawY() - lastY;//位移量Y
                        int left = v.getLeft() + dx;
                        int top = v.getTop() + dy;
                        int right = v.getRight() + dx;
                        int bottom = v.getBottom() + dy;
        //++限定按钮被拖动的范围
                        if (left < 0) {
                            left = 0;
                            right = left + v.getWidth();
                        }
                        if (right > screenWidth) {
                            right = screenWidth;
                            left = right - v.getWidth();
                        }
                        if (top < 0) {
                            top = 0;
                            bottom = top + v.getHeight();
                        }
                        if (bottom > screenHeight) {
                            bottom = screenHeight;
                            top = bottom - v.getHeight();
                        }
        //--限定按钮被拖动的范围
                        v.layout(left, top, right, bottom);//按钮重画
        // 记录当前的位置
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP: // 弹起事件
        //判断是单击事件或是拖动事件，位移量大于5则断定为拖动事件
                        if (Math.abs((int) (event.getRawX() - downX)) > 5
                                || Math.abs((int) (event.getRawY() - downY)) > 5)
                            clickormove = false;
                        else
                            clickormove = true;
                        break;
                }
                return false;
            }
        });
        movebtn.setOnClickListener(new View.OnClickListener() {//设置按钮被点击的监听器

            @Override
            public void onClick(View arg0) {
            // TODO Auto-generated method stub
                if (clickormove){
                    if (popupwindow != null&&popupwindow.isShowing()) {
                        popupwindow.dismiss();
                        return;
                    } else {
                        initmPopupWindowView();
                        popupwindow.showAsDropDown(arg0, 0, 5);
                    }
                }

                    //Toast.makeText(getActivity(), "single click",
                      //      Toast.LENGTH_SHORT).show();

            }
        });



        return rootView;
    }

    public void initmPopupWindowView() {

        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.popview_item,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, 250, 280);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }

                return false;
            }
        });

        /** 在这里可以实现自定义视图的功能 */
        Button btton2 = (Button) customView.findViewById(R.id.button2);
        Button btton3 = (Button) customView.findViewById(R.id.button3);
        btton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
                Intent intent=new Intent(getActivity(),Afind.class);
                startActivity(intent);
            }
        });
        btton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
                Intent intent=new Intent(getActivity(),Aannounce.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Fragment1ViewModel.class);
        // TODO: Use the ViewModel

    }


    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
        mMapView.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getActivity(), "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(getActivity(), "发生未知错误", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                break;
            default:
        }
    }
    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            //Toast.makeText(this, "nav to " + location.getAddrStr(), Toast.LENGTH_SHORT).show();
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            mBaiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        mBaiduMap.setMyLocationData(locationData);
    }
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            /*StringBuilder currentPosition=new StringBuilder();
            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
            currentPosition.append("经线：").append(location.getLongitude()).append("\n");
            currentPosition.append("国家：").append(location.getCountry()).append("\n");
            currentPosition.append("省：").append(location.getProvince()).append("\n");
            currentPosition.append("市：").append(location.getCity()).append("\n");
            currentPosition.append("定位方式：");
            if (location.getLocType()==BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
            }else if (location.getLocType()==BDLocation.TypeNetWorkLocation){
                currentPosition.append("网络");
            }
            positionText.setText(currentPosition);*/
            if (location.getLocType()==BDLocation.TypeGpsLocation ||
            location.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(location);
            }
        }

    }


}