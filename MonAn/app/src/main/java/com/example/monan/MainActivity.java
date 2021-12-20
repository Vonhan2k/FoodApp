package com.example.monan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_CODE_CAMERA = 456;
    public static int REQUEST_CODE_FOLDER = 789;

    ViewFlipper viewBanner;
    Animation enter, exit;
    ImageView imgIconTrangChu, imgIconNhanVien, imgIconQuanLy, imgIconMonAn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        //Animation chuyen hinh banner
        enter = AnimationUtils.loadAnimation(this, R.anim.anim_enter);
        exit = AnimationUtils.loadAnimation(this, R.anim.anim_exit);

        //Hien thi banner
        viewBanner.setInAnimation(enter);
        viewBanner.setOutAnimation(exit);
        viewBanner.setFlipInterval(3000);
        viewBanner.setAutoStart(true);



        //Su kien click cac imgIcon
        imgIconTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTrangChu = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentTrangChu);
                overridePendingTransition(0,0);
            }
        });
        //Su kien click cac imgIcon
        imgIconNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(MainActivity.this, NhanVienActivity.class);
               // startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        imgIconMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMonAn = new Intent(MainActivity.this, MonAnActivity.class);
                startActivity(intentMonAn);
                overridePendingTransition(0,0);
            }
        });

        imgIconQuanLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentQuanLy = new Intent(MainActivity.this, QuanLyMonAnActivity.class);
                startActivity(intentQuanLy);
                overridePendingTransition(0,0);
            }
        });

    };

    public void AnhXa(){
        viewBanner = (ViewFlipper) findViewById(R.id.viewBanner);
        imgIconTrangChu = (ImageView) findViewById(R.id.imageviewIconTrangChu);
        imgIconMonAn = (ImageView) findViewById(R.id.imageviewIconMonAn);
        imgIconNhanVien = (ImageView) findViewById(R.id.imageviewIconNhanVien);
        imgIconQuanLy = (ImageView) findViewById(R.id.imageviewIconQuanLy);
    }


}