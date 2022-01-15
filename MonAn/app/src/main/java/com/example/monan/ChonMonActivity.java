package com.example.monan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChonMonActivity extends AppCompatActivity {

    ImageView imgHinh;
    TextView txtTenMonAn, txtGia, txtSoLuong, txtTongTien;
    Button btnGiamSL, btnTangSL, btnChonMon;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    int soluong = 1;
    int tongtien, dongia = 0;
    int mamon = 0;
    NguoiDung account;

    String urlInsert = " http://192.168.1.5/food-menu-vhnhan/json/datmon/insert.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_mon);

        AnhXa();

        Intent intent = getIntent();
        MonAn monAn = (MonAn) intent.getSerializableExtra("dataMonAn");

        account = new NguoiDung();
        account = (NguoiDung) intent.getSerializableExtra("login");

        mamon = monAn.getMaMon();
        txtTenMonAn.setText(monAn.getTenMon());
        // tạo 1 NumberFormat để định dạng số theo tiêu chuẩn của nước Anh
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);


        // đối với số có kiểu long được định dạng theo chuẩn của nước Anh
        // thì phần ngàn của số được phân cách bằng dấu phẩy
        txtGia.setText( en.format(monAn.getGia())+" đ");
        Picasso.get().load(monAn.getHinhAnh()).into(imgHinh);
        txtTongTien.setText(en.format(monAn.getGia())+" đ");

        tongtien = monAn.getGia();
        dongia = monAn.getGia();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thực đơn");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnGiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soluong == 1){
                    btnGiamSL.setBackgroundColor(Color.parseColor("#CD8383"));
                    btnGiamSL.setClickable(false);
                }
                else if (soluong > 1){
                    soluong -= 1;
                    tongtien = tongtien - dongia;
                    txtSoLuong.setText(soluong+"");
                    txtTongTien.setText(en.format(tongtien)+" đ");
                }
            }
        });

        btnTangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soluong += 1;
                tongtien = tongtien + dongia;
                txtSoLuong.setText(soluong+"");
                txtTongTien.setText(en.format(tongtien)+" đ");

                if (soluong > 1){
                    btnGiamSL.setBackgroundColor(Color.parseColor("#C61111"));
                    btnGiamSL.setClickable(true);

                }
            }
        });

        btnChonMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemChonMon(urlInsert);
            }
        });
    }

    private void ThemChonMon(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(ChonMonActivity.this, "Chọn món thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChonMonActivity.this, LoaiMonAnActivity.class);
                    intent.putExtra("login", account);
                    startActivity(intent);


                } else{
                    Toast.makeText(ChonMonActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("monan_id",mamon+"");
                params.put("dongia", dongia+"");
                params.put("soluong",txtSoLuong.getText().toString().trim());
                params.put("thanhtien", tongtien+"");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void AnhXa(){
        imgHinh = (ImageView) findViewById(R.id.imageviewHinhMon);
        txtTenMonAn = (TextView) findViewById(R.id.textviewTenMonAn);
        txtGia = (TextView) findViewById(R.id.textviewGia);
        txtSoLuong = (TextView) findViewById(R.id.textviewSoLuong);
        txtTongTien = (TextView) findViewById(R.id.textviewTongTien);
        btnGiamSL = (Button) findViewById(R.id.buttonGiamSoLuong);
        btnTangSL = (Button) findViewById(R.id.buttonTangSoLuong);
        btnChonMon = (Button) findViewById(R.id.buttonChonMonAn);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbarChonMon);
    }
}