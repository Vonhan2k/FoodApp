package com.example.monan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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

public class XemGioHang extends AppCompatActivity {
    ListView lvdanhsachmongiohang;
    ArrayList<MonAnGioHang> arrayListMonAnGioHang;
    MonAnGioHangAdapter adapterMonAn;
    String urlGetData =  "http://192.168.1.3/food-menu-vhnhan/json/datmon/getdata.php";
    String urlDelete =  "http://192.168.1.3/food-menu-vhnhan/json/datmon/delete.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_gio_hang);
        lvdanhsachmongiohang = (ListView) findViewById(R.id.listviewGioHang);

        arrayListMonAnGioHang = new ArrayList<MonAnGioHang>();
        adapterMonAn = new MonAnGioHangAdapter(XemGioHang.this, R.layout.dong_mon_gio_hang, arrayListMonAnGioHang);
        lvdanhsachmongiohang.setAdapter(adapterMonAn);

        GetData(urlGetData);
    }
    private void GetData(String urlGetData){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, this.urlGetData, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayListMonAnGioHang.clear();
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayListMonAnGioHang.add(new MonAnGioHang(
                                object.getString("tenmon"),
                                object.getInt("dongia"),
                                object.getInt("soluong"),
                                object.getInt("thanhtien"),
                                object.getInt("id"),
                                object.getInt("mamon")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterMonAn.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(XemGioHang.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
//        {
//            @Nullable
//            @Override
//            //Gửi dữ liệu mã loại lên Server
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id",String.valueOf(id));
//                return params;
//            }
//        };
//        requestQueue.add(jsonArrayRequest);
    }


    public void DeleteMonAn(final int id){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(XemGioHang.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    GetData(urlGetData);
                } else{
                    Toast.makeText(XemGioHang.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(XemGioHang.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}