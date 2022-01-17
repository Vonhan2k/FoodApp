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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MonAnGioHangAdapter extends BaseAdapter {
    private XemGioHang context;
    private int layout;
    private List<MonAnGioHang> chonMonList;


    public MonAnGioHangAdapter(XemGioHang context, int layout, List<MonAnGioHang> chonMonList) {
        this.context = context;
        this.layout = layout;
        this.chonMonList = chonMonList;
    }

    @Override
    public int getCount() {
        return chonMonList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    private class ViewHolder{
        TextView txtTenMonAn_giohang, txtGia_giohang, txtSoLuong,txtThanhTien, txtId;

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
            holder.txtId = (TextView) view.findViewById(R.id.textviewId_giohang);
            holder.txtGia_giohang = (TextView) view.findViewById(R.id.textviewGiaCustom_giohang);
            holder.txtSoLuong = (TextView) view.findViewById(R.id.textviewSoLuong_giohang);
            holder.txtThanhTien = (TextView) view.findViewById(R.id.textviewThanhTien_giohang);
            holder.imgDelete_giohang = (ImageView) view.findViewById(R.id.imageviewDelete_giohang);
            view.setTag(holder);
        } else{
            holder = (MonAnGioHangAdapter.ViewHolder) view.getTag();
        }

        MonAnGioHang chonMon = chonMonList.get(i);


        holder.txtTenMonAn_giohang.setText("Tên món:" + chonMon.getTenmon());
        holder.txtId.setText("ID:" + chonMon.getId());
        holder.txtGia_giohang.setText("Giá:" + chonMon.getDongia());
        holder.txtSoLuong.setText("Số Lượng:" +chonMon.getSoluong() +"");
        // tạo 1 NumberFormat để định dạng số theo tiêu chuẩn của nước Anh
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        // đối với số có kiểu long được định dạng theo chuẩn của nước Anh
        // thì phần ngàn của số được phân cách bằng dấu phẩy
        holder.txtThanhTien.setText( "Thành Tiền: "+ en.format(chonMon.getThanhtien()) +" đ");

        holder.imgDelete_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(chonMon.getTenmon(), chonMon.getId());
            }
        });

        return view;
    }
    public void XacNhanXoa(String ten, int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa đơn số " +ten + " không!");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                context.DeleteChonMon(id);
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