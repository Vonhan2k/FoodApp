package com.example.monan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class thongtinungdung extends AppCompatActivity {
    ListView lvdanhsachchucnang;
    ArrayAdapter<String> adapter = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinungdung);
        lvdanhsachchucnang = (ListView) findViewById(R.id.lvDanhSachChucNang);
        final String arr[] = {"1. Đăng Nhập","2. Gọi Món","3. Xem danh sách món ăn","4. Xem danh sách loại món ăn","5. Thêm món ăn",
        "6. Xóa món ăn","7. Sửa món ăn","8. Xem danh sách món ăn được gọi","9. Thanh Toán"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arr);
        lvdanhsachchucnang.setAdapter(adapter);
    }
}