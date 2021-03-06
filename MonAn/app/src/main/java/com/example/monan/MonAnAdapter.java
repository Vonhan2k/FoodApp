package com.example.monan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class MonAnAdapter extends BaseAdapter {


    private MonAnActivity context;
    private int layout;
    private List<MonAn> monAnList;
    NguoiDung account;

    public MonAnAdapter(MonAnActivity context, int layout, List<MonAn> monAnList) {
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
        Button btnChonMon;
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
            holder.btnChonMon = (Button) view.findViewById(R.id.butttonChonMon);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imageviewEdit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imageviewDelete);
           view.setTag(holder);
        } else{
            holder = (ViewHolder) view.getTag();
        }

        MonAn monAn = monAnList.get(i);

        holder.txtTenMonAn.setText(monAn.getTenMon());
        // t???o 1 NumberFormat ????? ?????nh d???ng s??? theo ti??u chu???n c???a n?????c Anh
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        // ?????i v???i s??? c?? ki???u long ???????c ?????nh d???ng theo chu???n c???a n?????c Anh
        // th?? ph???n ng??n c???a s??? ???????c ph??n c??ch b???ng d???u ph???y
        holder.txtGia.setText( en.format(monAn.getGia()) +" ??");
        Picasso.get().load(monAn.getHinhAnh()).into(holder.imgHinh);


        holder.btnChonMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChonMonActivity.class);
                intent.putExtra("dataMonAn", monAn);
                intent.putExtra("login", account);
                context.startActivity(intent);
            }
        });

        return view;
    }


}

