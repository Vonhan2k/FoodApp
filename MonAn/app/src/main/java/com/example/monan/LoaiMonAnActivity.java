package com.example.monan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoaiMonAnActivity extends AppCompatActivity {
    GridView gvDanhSachLoaiMonAn;
    ArrayList<LoaiMonAn> loaiMonAnArrayList;
    LoaiMonAnAdapter loaiMonAnAdapter;
    String url = "http://192.168.1.4/getdata_loaimon.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_mon_an);

        // custom listview
        gvDanhSachLoaiMonAn  = (GridView) findViewById(R.id.gridViewLoaiMonAn);
        loaiMonAnArrayList = new ArrayList<>();
        loaiMonAnAdapter = new LoaiMonAnAdapter(LoaiMonAnActivity.this,R.layout.dong_loai_mon_an,loaiMonAnArrayList);
        gvDanhSachLoaiMonAn.setAdapter(loaiMonAnAdapter);
        // kết thức custom listview

        GetDataLoaiMon(url);
    }

    private void GetDataLoaiMon(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>(){
                @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object =  response.getJSONObject(i);
                                loaiMonAnArrayList.add(new LoaiMonAn(
                                        // phải giống với bên sinvien
                                       object.getInt("maloai"),
                                       object.getString("tenloai"),
                                        object.getString("hinhanh")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        loaiMonAnAdapter.notifyDataSetChanged();
                    }

                    },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoaiMonAnActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                );
                requestQueue.add(jsonArrayRequest);

    }
}