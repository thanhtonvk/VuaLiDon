package com.quangdz.vualidon.Model;

public class YeuThich {
    private String id,idtaikhoan,idphim;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(String idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public String getIdphim() {
        return idphim;
    }

    public void setIdphim(String idphim) {
        this.idphim = idphim;
    }

    public YeuThich(String id, String idtaikhoan, String idphim) {
        this.id = id;
        this.idtaikhoan = idtaikhoan;
        this.idphim = idphim;
    }
    public YeuThich(){

    }
}
