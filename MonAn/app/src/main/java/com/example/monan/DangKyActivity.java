package com.example.monan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class DangKyActivity extends AppCompatActivity {


    EditText editTenDangNhap_dangky,editMatKhau_dangky,editNgaySinh_dangky,editHoTen_dangky;
    Button btnDongY_dangky,btnThoat_dangky;
    RadioGroup radiogroupGioiTinh;
    String gioitinh_dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        AnhXa();
    }

    private void AnhXa() {
        editTenDangNhap_dangky = (EditText) findViewById(R.id.edit_tendangnhap);
        editMatKhau_dangky = (EditText) findViewById(R.id.edit_matkhau);
        editNgaySinh_dangky = (EditText) findViewById(R.id.edit_ngaysinh);
        editHoTen_dangky = (EditText) findViewById(R.id.edit_hoten);
        btnDongY_dangky = (Button) findViewById(R.id.button_dongy);
        btnThoat_dangky = (Button) findViewById(R.id.button_thoat);
        radiogroupGioiTinh = (RadioGroup) findViewById(R.id.radiogroupd_gioitinh);

    }
}