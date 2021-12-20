package com.example.monan;

import java.io.Serializable;

public class MonAn implements Serializable {
    private int maMon;
    private String tenMon;
    private int Gia;
    private String hinhAnh;


    public MonAn(int maMon, String tenMon, int gia, String hinhAnh) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        Gia = gia;
        this.hinhAnh = hinhAnh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }
}
