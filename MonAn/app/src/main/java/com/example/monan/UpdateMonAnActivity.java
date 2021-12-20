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

public class UpdateMonAnActivity extends AppCompatActivity {

    EditText edtTenMonAnCapNhat, edtGiaCapNhat;
    Button btnCapNhat, btnHuy;
    int mamon = 0;
    String urlUpdate = "http://192.168.1.5/WebService/monan/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mon_an);

        AnhXa();

        Intent intent = getIntent();
        MonAn monAn = (MonAn) intent.getSerializableExtra("dataMonAn");

        mamon = monAn.getMaMon();
        edtTenMonAnCapNhat.setText(monAn.getTenMon());
        edtGiaCapNhat.setText(monAn.getGia()+"");

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenmon = edtTenMonAnCapNhat.getText().toString();
                String gia = edtGiaCapNhat.getText().toString();

                if (tenmon.equals("") || gia.equals("")){
                    Toast.makeText(UpdateMonAnActivity.this, "Vui lòng thêm thông tin!", Toast.LENGTH_SHORT).show();
                } else{
                    CapNhatMonAn(urlUpdate);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void CapNhatMonAn(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(UpdateMonAnActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateMonAnActivity.this, MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(UpdateMonAnActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateMonAnActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mamon", String.valueOf(mamon));
                params.put("tenmon", edtTenMonAnCapNhat.getText().toString().trim());
                params.put("gia", edtGiaCapNhat.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa(){
        edtTenMonAnCapNhat = (EditText) findViewById(R.id.editTextTenMonAn);
        edtGiaCapNhat = (EditText) findViewById(R.id.editTextGiaTien);
        btnCapNhat = (Button) findViewById(R.id.buttonCapNhatMonAn);
        btnHuy = (Button) findViewById(R.id.buttonThoat);
    }

}