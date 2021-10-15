package com.quangdz.vualidon.Database;

public class DatabaseDBContext {
    LichSuDB lichSuDB;
    PhimDB phimDB;
    TaiKhoanDB taiKhoanDB;
    YeuThichDB yeuThichDB;
    public DatabaseDBContext(){
        lichSuDB = new LichSuDB();
        phimDB = new PhimDB();
        taiKhoanDB = new TaiKhoanDB();
        yeuThichDB = new YeuThichDB();
    }

    public LichSuDB getLichSuDB() {
        return lichSuDB;
    }

    public void setLichSuDB(LichSuDB lichSuDB) {
        this.lichSuDB = lichSuDB;
    }

    public PhimDB getPhimDB() {
        return phimDB;
    }

    public void setPhimDB(PhimDB phimDB) {
        this.phimDB = phimDB;
    }

    public TaiKhoanDB getTaiKhoanDB() {
        return taiKhoanDB;
    }

    public void setTaiKhoanDB(TaiKhoanDB taiKhoanDB) {
        this.taiKhoanDB = taiKhoanDB;
    }

    public YeuThichDB getYeuThichDB() {
        return yeuThichDB;
    }

    public void setYeuThichDB(YeuThichDB yeuThichDB) {
        this.yeuThichDB = yeuThichDB;
    }
}
