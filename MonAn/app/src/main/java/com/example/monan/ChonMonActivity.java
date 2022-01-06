package com.example.monan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChonMonActivity extends AppCompatActivity {

    ImageView imgHinh;
    TextView txtTenMonAn, txtGia, txtSoLuong, txtTongTien;
    Button btnGiamSL, btnTangSL, btnChonMon;

    int soluong = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_mon);

        AnhXa();



        btnGiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soluong == 1){
                    btnGiamSL.setBackgroundColor(Color.parseColor("#CD8383"));
                    btnGiamSL.setClickable(false);
                }
                else if (soluong > 1){
                    soluong -= 1;
                    txtSoLuong.setText(soluong+"");
                }

            }
        });


        btnTangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soluong += 1;
                txtSoLuong.setText(soluong+"");

                if (soluong > 1){
                    btnGiamSL.setBackgroundColor(Color.parseColor("#C61111"));
                    btnGiamSL.setClickable(true);
                }
            }
        });
    }

    private void AnhXa(){
        imgHinh = (ImageView) findViewById(R.id.imageViewHinh);
        txtTenMonAn = (TextView) findViewById(R.id.textviewTenMonAn);
        txtGia = (TextView) findViewById(R.id.textviewGia);
        txtSoLuong = (TextView) findViewById(R.id.textviewSoLuong);
        txtTongTien = (TextView) findViewById(R.id.textviewTongTien);
        btnGiamSL = (Button) findViewById(R.id.buttonGiamSoLuong);
        btnTangSL = (Button) findViewById(R.id.buttonTangSoLuong);
        btnChonMon = (Button) findViewById(R.id.buttonChonMonAn);
    }
}