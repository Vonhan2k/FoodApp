package com.example.monan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MonAnGioHangAdapter extends BaseAdapter {
    private XemGioHang context;
    private int layout;
    private List<MonAnGioHang> monAnList;


    public MonAnGioHangAdapter(XemGioHang context, int layout, List<MonAnGioHang> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }

    @Override
    public int getCount() {
        return monAnList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    private class ViewHolder{
        TextView txtTenMonAn_giohang, txtGia_giohang, txtSoLuong,txtThanhTien;

        ImageView imgHinh_giohang, imgDelete_giohang;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        MonAnGioHangAdapter.ViewHolder holder;
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTenMonAn_giohang = (TextView) view.findViewById(R.id.textviewTenMonAnCustom_giohang);
            holder.txtGia_giohang = (TextView) view.findViewById(R.id.textviewGiaCustom_giohang);
            holder.txtSoLuong = (TextView) view.findViewById(R.id.textviewSoLuong_giohang);
            holder.txtThanhTien = (TextView) view.findViewById(R.id.textviewThanhTien_giohang);
            holder.imgDelete_giohang = (ImageView) view.findViewById(R.id.imageviewDelete_giohang);
            view.setTag(holder);
        } else{
            holder = (MonAnGioHangAdapter.ViewHolder) view.getTag();
        }

        MonAnGioHang monAn = monAnList.get(i);


        holder.txtTenMonAn_giohang.setText("Tên món:" + monAn.getTenmon());
        holder.txtGia_giohang.setText("Giá:" + monAn.getDongia());
        holder.txtSoLuong.setText("Số Lượng:" +monAn.getSoluong() +"");
        // tạo 1 NumberFormat để định dạng số theo tiêu chuẩn của nước Anh
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        // đối với số có kiểu long được định dạng theo chuẩn của nước Anh
        // thì phần ngàn của số được phân cách bằng dấu phẩy
        holder.txtThanhTien.setText( "Thành Tiền: "+ en.format(monAn.getThanhtien()) +" đ");
//        Picasso.get().load(monAn.getHinhAnh()).into(holder.imgHinh);



        holder.imgDelete_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(monAn.getTenmon(), monAn.getId());
            }
        });
        return view;


    }
    public void XacNhanXoa(String tenmon, int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa món ăn " + tenmon + " không!");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteMonAn(id);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }

}
