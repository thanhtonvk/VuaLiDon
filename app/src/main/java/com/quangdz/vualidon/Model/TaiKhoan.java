package com.quangdz.vualidon.Model;

public class TaiKhoan {
    private String id, email, matkhau, hoten;
    private boolean admin;

    @Override
    public String toString() {
        return String.format(" ID: %s\n Email: %s\n Mật khẩu: %s\n Họ tên: %s", id, email, matkhau, hoten);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public TaiKhoan(String id, String email, String matkhau, String hoten) {
        this.id = id;
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.admin = false;

    }

    public TaiKhoan(String email, String matkhau, String hoten) {
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.admin = false;
    }

    public TaiKhoan() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
