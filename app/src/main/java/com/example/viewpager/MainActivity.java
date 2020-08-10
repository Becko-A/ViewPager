package com.example.viewpager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    ViewPager viewPager;
    BottomNavigationView navigation;
    List<Fragment> listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView(){
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        navigation=(BottomNavigationView) findViewById(R.id.navigation);

        listFragment=new ArrayList<>();
        listFragment.add(new Fragment1());
        listFragment.add(new Fragment2());
        listFragment.add(new Fragment3());
        MyFragAdapter myAdapter=new MyFragAdapter(getSupportFragmentManager(),
                this,listFragment);
        viewPager.setAdapter(myAdapter);

        navigation.setOnNavigationItemReselectedListener(
                new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ditu:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.xiaoxi:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.wode:
                        viewPager.setCurrentItem(2);
                        break;

                    default:
                        break;
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position){
                /*if (navigation.getMenu().getItem(position) != null) {
                    navigation.getMenu().getItem(position).setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }*/
                navigation.getMenu().getItem(position).setChecked(true);//让当前页面的按钮点亮
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




}