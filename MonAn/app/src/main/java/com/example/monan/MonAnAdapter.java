package com.example.monan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MonAnAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<MonAn> monAnList;

    public MonAnAdapter(MainActivity context, int layout, List<MonAn> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }

    @Override
    public int getCount() {
        return monAnList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTenMonAn, txtGia;
        ImageView imgHinh, imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTenMonAn = (TextView) view.findViewById(R.id.textviewTenMonAnCustom);
            holder.txtGia = (TextView) view.findViewById(R.id.textviewGiaCustom);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imageviewEdit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imageviewDelete);
           view.setTag(holder);
        } else{
            holder = (ViewHolder) view.getTag();
        }

        MonAn monAn = monAnList.get(i);

        holder.txtTenMonAn.setText(monAn.getTenMon());
        holder.txtGia.setText("Năm sinh:" + monAn.getGia());
        Picasso.get().load(monAn.getHinhAnh()).into(holder.imgHinh);

        //bat su kien xóa và sửa
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateMonAnActivity.class);
                intent.putExtra("dataMonAn", monAn);
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(monAn.getTenMon(), monAn.getMaMon());
            }
        });

        return view;
    }

    private void XacNhanXoa(String ten, int mamon){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa món ăn " + ten + " không!");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteMonAn(mamon);
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

