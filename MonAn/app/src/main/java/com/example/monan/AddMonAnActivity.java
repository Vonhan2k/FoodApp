package com.example.monan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddMonAnActivity extends AppCompatActivity {

    EditText edtTenMonAn, edtGia;
    Button btnThem, btnThoat;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    Spinner spLoaiMon;

    ArrayList<LoaiMon> arrayLoaiMon;
    ArrayList<String> names = new ArrayList<String>();


    String urlGetData = "http://192.168.1.5/food/json/loaimon/getdata.php";
    String urlInsert = "http://192.168.1.5/food/json/monan/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mon_an);

        AnhXa();
        GetData(urlGetData);

        //Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thêm món ăn");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


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

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayLoaiMon = new ArrayList<>();

                for (int i = 0; i < response.length(); i++){
                    try {

                        JSONObject object = response.getJSONObject(i);
                        arrayLoaiMon.add(new LoaiMon(
                                object.getInt("maloai"),
                                object.getString("tenloai"),
                                object.getString("hinhanh")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //Đổ dữ liệu lên spinner
                for (int i = 0; i < arrayLoaiMon.size(); i++){
                    names.add(arrayLoaiMon.get(i).getTenLoai().toString());
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddMonAnActivity.this, android.R.layout.simple_spinner_item, names);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spLoaiMon.setAdapter(spinnerArrayAdapter);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddMonAnActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void ThemMonAn(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(AddMonAnActivity.this, "Thêm món ăn thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddMonAnActivity.this, MonAnActivity.class);
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
        spLoaiMon = (Spinner) findViewById(R.id.spLoaiMonAn);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbarThemMon);
        edtTenMonAn = (EditText) findViewById(R.id.editTextTenMonAn);
        edtGia = (EditText) findViewById(R.id.editTextGiaTien);
        btnThem = (Button) findViewById(R.id.buttonThem);
        btnThoat = (Button) findViewById(R.id.buttonThoat);
    }
}