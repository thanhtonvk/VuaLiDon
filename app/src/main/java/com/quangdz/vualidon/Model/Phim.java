package com.quangdz.vualidon.Model;


public class Phim {
    private String id;
    private String tenphimtv;
    private String tenphimta;
    private String theloai;
    private String quocgia;
    private String dienvien;
    private int luotxem;
    private String gioithieu;
    private boolean decu,phimmoi,phimhot;

    public Phim(String id, String tenphimtv, String tenphimta, String theloai, String quocgia, String dienvien, String gioithieu, boolean decu, boolean phimmoi, boolean phimhot) {
        this.id = id;
        this.tenphimtv = tenphimtv;
        this.tenphimta = tenphimta;
        this.theloai = theloai;
        this.quocgia = quocgia;
        this.dienvien = dienvien;
        this.luotxem = 0;
        this.gioithieu = gioithieu;
        this.decu = decu;
        this.phimmoi = phimmoi;
        this.phimhot = phimhot;
    }

    public boolean isDecu() {
        return decu;
    }

    public void setDecu(boolean decu) {
        this.decu = decu;
    }

    public boolean isPhimmoi() {
        return phimmoi;
    }

    public void setPhimmoi(boolean phimmoi) {
        this.phimmoi = phimmoi;
    }

    public boolean isPhimhot() {
        return phimhot;
    }

    public void setPhimhot(boolean phimhot) {
        this.phimhot = phimhot;
    }

    public Phim() {

    }

    public String getDienvien() {
        return dienvien;
    }

    public void setDienvien(String dienvien) {
        this.dienvien = dienvien;
    }


    public String getTenphimtv() {
        return tenphimtv;
    }

    public void setTenphimtv(String tenphimtv) {
        this.tenphimtv = tenphimtv;
    }

    public String getTenphimta() {
        return tenphimta;
    }

    public void setTenphimta(String tenphimta) {
        this.tenphimta = tenphimta;
    }

    public Phim(String id, String tenphimtv, String tenphimta, String theloai, String quocgia, String dienvien,  int luotxem, String gioithieu) {
        this.id = id;
        this.tenphimtv = tenphimtv;
        this.tenphimta = tenphimta;
        this.theloai = theloai;
        this.quocgia = quocgia;
        this.dienvien = dienvien;

        this.luotxem = luotxem;
        this.gioithieu = gioithieu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    public int getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(int luotxem) {
        this.luotxem = luotxem;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }
}
