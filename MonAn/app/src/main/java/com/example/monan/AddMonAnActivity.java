package com.example.monan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddMonAnActivity extends AppCompatActivity {

    EditText edtTenMonAn, edtGia;
    Button btnThem, btnThoat;
    String urlInsert = "http://192.168.1.5/WebService/monan/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mon_an);

        AnhXa();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenmonan = edtTenMonAn.getText().toString();
                String gia = edtGia.getText().toString();

                if (tenmonan.equals("") || gia.equals("")){
                    Toast.makeText(AddMonAnActivity.this, "Vui lòng thêm thông tin!", Toast.LENGTH_SHORT).show();
                } else{
                    ThemMonAn(urlInsert);
                }
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void ThemMonAn(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(AddMonAnActivity.this, "Thêm món ăn thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddMonAnActivity.this, MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(AddMonAnActivity.this, "Lỗi! Không thể thêm món ăn", Toast.LENGTH_SHORT).show();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddMonAnActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tenmon", edtTenMonAn.getText().toString().trim());
                params.put("gia", edtGia.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa(){
        edtTenMonAn = (EditText) findViewById(R.id.editTextTenMonAn);
        edtGia = (EditText) findViewById(R.id.editTextGiaTien);
        btnThem = (Button) findViewById(R.id.buttonThem);
        btnThoat = (Button) findViewById(R.id.buttonThoat);
    }
}