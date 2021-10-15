package com.quangdz.vualidon.Model;

public class LichSu {
    private String id,idphim,idtaikhoan,ngay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdphim() {
        return idphim;
    }

    public void setIdphim(String idphim) {
        this.idphim = idphim;
    }

    public String getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(String idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public LichSu(String id, String idphim, String idtaikhoan, String ngay) {
        this.id = id;
        this.idphim = idphim;
        this.idtaikhoan = idtaikhoan;
        this.ngay = ngay;
    }
    public LichSu(){

    }
}
