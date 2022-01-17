package com.example.monan;

import java.io.Serializable;

public class MonAnGioHang implements Serializable {

    public String tenmon;
    public int dongia;
    public int soluong;
    public int thanhtien;


    public MonAnGioHang(String tenmon, int dongia, int soluong, int thanhtien) {
        this.tenmon = tenmon;
        this.dongia = dongia;
        this.soluong = soluong;
        this.thanhtien = thanhtien;

    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }


}
