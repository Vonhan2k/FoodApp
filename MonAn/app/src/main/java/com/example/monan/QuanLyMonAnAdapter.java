package com.example.monan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class QuanLyMonAnAdapter extends BaseAdapter {


    private QuanLyMonAnActivity context;
    private int layout;
    private List<MonAn> monAnList;

    public QuanLyMonAnAdapter(QuanLyMonAnActivity context, int layout, List<MonAn> monAnList) {
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
        TextView txtTenMonAn, txtGia, txtmaLoai;
        ImageView imgHinh, imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);     holder.txtTenMonAn = (TextView) view.findViewById(R.id.textviewTenMonAnCustom);
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
        // t???o 1 NumberFormat ????? ?????nh d???ng s??? theo ti??u chu???n c???a n?????c Anh
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        // ?????i v???i s??? c?? ki???u long ???????c ?????nh d???ng theo chu???n c???a n?????c Anh
        // th?? ph???n ng??n c???a s??? ???????c ph??n c??ch b???ng d???u ph???y
        holder.txtGia.setText("Gi??:" + en.format(monAn.getGia()) +"??");
        Picasso.get().load(monAn.getHinhAnh()).into(holder.imgHinh);


        //bat su kien x??a v?? s???a
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
    public void XacNhanXoa(String ten, int mamon){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("B???n c?? mu???n x??a m??n ??n " + ten + " kh??ng!");
        dialogXoa.setPositiveButton("C??", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteMonAn(mamon);
            }
        });
        dialogXoa.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }

}

